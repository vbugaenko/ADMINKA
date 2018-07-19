package ru.vbugaenko.adminka.db.dao;

/**
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

public interface PasswordDAO
{
    void    upt(int id, String passwordHash);
    void    del(int id);
    String  get(int userId);
}
