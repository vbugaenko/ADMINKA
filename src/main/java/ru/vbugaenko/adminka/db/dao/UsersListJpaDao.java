package ru.vbugaenko.adminka.db.dao;


import ru.vbugaenko.adminka.db.entities.User;

import java.util.List;

/**
 * refactored
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

public interface UsersListJpaDao
{
    List<User> getAllUsers();
    List<User> delete(int idForD);
    List<User> updateInfo(int idForUpd, String newInfo);
    List<User> updateRole(int idForUpd, int newRole);
    List<User> setEnabledStatus(int id, boolean b);
}
