package ru.vbugaenko.adminka.db.dao;


import ru.vbugaenko.adminka.db.entities.Role;

/**
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

public interface RolesDAO
{
    void add(Role role);
    Role get(int id);

    Role getByName(String roleName);
}
