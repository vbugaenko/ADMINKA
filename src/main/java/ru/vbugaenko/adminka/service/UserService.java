package ru.innopolis.stc9.saturn.service;

public interface UserService
{
    boolean checkAuth(String login, String password);
    Integer getRole(String login);
    Integer getId(String login);
    boolean checkOldPwd(String oldPassword, int uid);
    void updatePwd(String newPassword, int uid);
}
