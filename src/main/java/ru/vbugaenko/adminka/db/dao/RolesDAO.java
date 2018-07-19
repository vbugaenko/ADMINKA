package ru.innopolis.stc9.saturn.db.dao;

import ru.innopolis.stc9.saturn.db.entities.Role;

/**
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

public interface RolesDAO
{
    void check  ();
    void add    (Role role);
    Role get    (int id);

    Role getByName(String roleName);
}
