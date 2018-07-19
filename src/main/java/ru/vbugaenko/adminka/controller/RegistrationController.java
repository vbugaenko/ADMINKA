package ru.vbugaenko.adminka.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vbugaenko.adminka.service.RegistrationService;

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

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @RequestMapping(value = "/registration")
    public String userRegistration()
    {
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String userRegistration(@RequestParam(value = "uRole",       required = true) Integer uRole,
                                   @RequestParam(value = "uLogin",      required = true) String  uLogin,
                                   @RequestParam(value = "uName",       required = true) String  uName,
                                   @RequestParam(value = "uAge",        required = true) String  uAge,
                                   @RequestParam(value = "uCity",       required = true) String  uCity,
                                   @RequestParam(value = "uAdress",     required = true) String  uAdress,
                                   @RequestParam(value = "uPhone",      required = true) String  uPhone,
                                   @RequestParam(value = "uEmail",      required = true) String  uEmail,
                                   @RequestParam(value = "uPassword",   required = true) String  uPassword,
                                   @RequestParam(value = "uPassRepeat", required = true) String  uPasswordRepeat,
                                   @RequestParam(value = "uPhoto",      required = true) String  uPhoto,
                                   Model model)
    {
        Date userBirthdate = new Date();
        try
        {
            SimpleDateFormat format = new SimpleDateFormat();
            format.applyPattern("yyyy-MM-dd");
            userBirthdate = format.parse(uAge);
        }
        catch (ParseException e)
        { loggerFileInf.error(e.getMessage()); }

        String paramsChecking = registrationService.addUser(
                uRole, uLogin, uName, userBirthdate, uCity, uAdress, uPhone, uEmail, uPassword, uPasswordRepeat, uPhoto );

        if(paramsChecking.equals(""))
            return "registration-success";

        return "registration";
    }
}