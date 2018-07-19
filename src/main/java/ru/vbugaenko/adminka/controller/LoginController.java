package ru.innopolis.stc9.saturn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController
{
    @RequestMapping(value="/login", method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model)
    {
        if(error!=null)
            model.addAttribute("msg", "authErr");

        if(logout!=null)
            model.addAttribute("msg", "logout");

        return "login";
    }
}
