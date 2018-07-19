package ru.innopolis.stc9.saturn.db.dao;

import ru.innopolis.stc9.saturn.db.entities.User;

/**
 * @author Victor Bugaenko
 * @since 12.07.2018
 */

public interface RegistrationDAO
{
    boolean add(User user, String passwordHash);
}
