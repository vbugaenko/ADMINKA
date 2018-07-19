package ru.innopolis.stc9.saturn.db.security;

public interface FormsCheck {
    public boolean checkEngLettersAndDigits(String param);

    public boolean checkEngRusLettersDigitsDashTrait(String param);

    public boolean checkAge(int param);

    public boolean checkName(String param);

    boolean checkCity(String param);

    public boolean checkAdress(String param);

    public boolean checkPhone(String param);

    public boolean checkEmail(String param);

    boolean checkPhoto(String param);
}
