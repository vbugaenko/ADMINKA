package ru.vbugaenko.adminka.db.security;

import org.springframework.stereotype.Component;

@Component
public class FormsCheckImpl implements FormsCheck
{
    @Override
    public boolean checkEngLettersAndDigits(String param)
    {
        String regex = "[a-zA-Z0-9\\s]+";
        return param.matches(regex);
    }

    @Override
    public boolean checkEngRusLettersDigitsDashTrait(String param)
    {
        String regex = "[а-яА-Яa-zA-Z0-9-_\\s]+";
        return param.matches(regex);
    }

    @Override
    public boolean checkAge(int param)
    {
        return (param > 0 && param < 100);
    }

    @Override
    public boolean checkName(String param)
    {
        String regex = "[а-яА-Яa-zA-Z\\s]+";
        return param.matches(regex);
    }

    @Override
    public boolean checkCity(String param)
    {
        String regex = "[а-яА-Яa-zA-Z0-9-_,./\\s]+";
        return param.matches(regex);
    }

    @Override
    public boolean checkAdress(String param)
    {
        String regex = "[а-яА-Яa-zA-Z0-9-_,./\\s]+";
        return param.matches(regex);
    }

    @Override
    public boolean checkPhone(String param)
    {
        String regex = "[0-9-()+\\s]+";
        return param.matches(regex);
    }

    @Override
    public boolean checkEmail(String param) {
        String regex = "[a-zA-Z0-9-_.@\\s]+";
        return param.matches(regex);
    }

    @Override
    public boolean checkPhoto(String param)
    {
        String regex = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";
        return param.matches(regex);
    }


}
