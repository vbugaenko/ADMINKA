package ru.vbugaenko.adminka.db.dao;


import ru.vbugaenko.adminka.db.entities.User;

/**
 * @author Victor Bugaenko
 * @since 12.07.2018
 */

public interface RegistrationDAO
{
    boolean add(User user, String passwordHash);
}
