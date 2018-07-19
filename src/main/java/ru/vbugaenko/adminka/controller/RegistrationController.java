package ru.innopolis.stc9.saturn.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.saturn.service.RegistrationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * refactored
 * @author Victor Bugaenko
 * @since 10.07.2018
 */

@Controller
public class RegistrationController
{
    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");

    private Integer userRole       = 3;
    private String  userLogin      = "";
    private String  userName       = "";
    private Date    userAge        = new Date();
    private String  userCity       = "";
    private String  userAdress     = "";
    private String  userPhone      = "";
    private String  userEmail      = "";
    private String  userPassword;
    private String  userPasswordRepeat;
    private String  paramsChecking = "";
    private String  userPhoto      = "";

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MM yyyy");

    @RequestMapping(value = "/registration")
    public String userRegistration(Model model) {
        model.addAttribute("userRole", userRole);
        model.addAttribute("userLogin", userLogin);
        model.addAttribute("userName", userName);
        model.addAttribute("userAge", dateFormat.format(new Date()) );
        model.addAttribute("userCity", userCity);
        model.addAttribute("userAdress", userAdress);
        model.addAttribute("userPhone", userPhone);
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("userPassword", "");
        model.addAttribute("userPasswordRepeat", "");
        model.addAttribute("paramsChecking", paramsChecking);
        return "registration";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userRegistration(@RequestParam(value = "userRole",    required = true) Integer uRole,
                                   @RequestParam(value = "userLogin",   required = true) String uLogin,
                                   @RequestParam(value = "userName",    required = true) String uName,
                                   @RequestParam(value = "userAge",     required = true) String uAge,
                                   @RequestParam(value = "userCity",    required = true) String uCity,
                                   @RequestParam(value = "userAdress",  required = true) String uAdress,
                                   @RequestParam(value = "userPhone",   required = true) String uPhone,
                                   @RequestParam(value = "userEmail",   required = true) String uEmail,
                                   @RequestParam(value = "userPassword", required = true) String uPassword,
                                   @RequestParam(value = "userPasswordRepeat", required = true) String uPasswordRepeat,
                                   @RequestParam(value = "userPhoto",   required = true) String uPhoto,
                                   Model model)
    {
        userRole = uRole;
        userLogin = uLogin;
        userName = uName;
        try
        {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            userAge = format.parse(uAge);
        }
        catch (ParseException e)
        { loggerFileInf.error(e.getMessage()); }

        userCity = uCity;
        userAdress = uAdress;
        userPhone = uPhone;
        userEmail = uEmail;
        userPassword = uPassword;
        userPasswordRepeat = uPasswordRepeat;
        userPhoto = uPhoto;

        paramsChecking = registrationService.addUser(
                userRole, userLogin, userName, userAge, userCity, userAdress,
                userPhone, userEmail, userPassword, userPasswordRepeat, userPhoto
        );

        if(paramsChecking.equals(""))
            return "registration-success";
        else
            return "registration";
    }


}