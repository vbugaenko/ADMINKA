package ru.innopolis.stc9.saturn.db.security;

import org.springframework.stereotype.Component;

@Component
public class FormsCheckImpl implements FormsCheck {
    @Override
    public boolean checkEngLettersAndDigits(String param) {
        String regex = "[a-zA-Z0-9\\s]+";
        if (param.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEngRusLettersDigitsDashTrait(String param) {
        String regex = "[а-яА-Яa-zA-Z0-9-_\\s]+";
        if (param.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAge(int param) {
        if (param > 0 && param < 100) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkName(String param) {
        String regex = "[а-яА-Яa-zA-Z\\s]+";
        if (param.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCity(String param) {
        String regex = "[а-яА-Яa-zA-Z0-9-_,./\\s]+";
        if (param.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkAdress(String param) {
        String regex = "[а-яА-Яa-zA-Z0-9-_,./\\s]+";
        if (param.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPhone(String param) {
        String regex = "[0-9-()+\\s]+";
        if (param.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmail(String param) {
        String regex = "[a-zA-Z0-9-_.@\\s]+";
        if (param.matches(regex)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkPhoto(String param) {
        String regex = "([^\\s]+(\\.(?i)(jpe?g|png|gif|bmp))$)";
        if (param.matches(regex)) {
            return true;
        }
        return false;
    }


}
