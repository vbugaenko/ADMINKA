package ru.vbugaenko.adminka.db.security;

public interface FormsCheck {
    boolean checkEngLettersAndDigits(String param);
    boolean checkEngRusLettersDigitsDashTrait(String param);
    boolean checkAge(int param);
    boolean checkName(String param);
    boolean checkCity(String param);
    boolean checkAdress(String param);
    boolean checkPhone(String param);
    boolean checkEmail(String param);
    boolean checkPhoto(String param);
}
