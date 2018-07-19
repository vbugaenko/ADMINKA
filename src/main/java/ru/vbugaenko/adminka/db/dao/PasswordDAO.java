package ru.innopolis.stc9.saturn.db.dao;

import ru.innopolis.stc9.saturn.db.entities.Password;
import ru.innopolis.stc9.saturn.db.entities.User;

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
