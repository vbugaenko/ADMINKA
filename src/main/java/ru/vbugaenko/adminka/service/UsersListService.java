package ru.innopolis.stc9.saturn.service;

import ru.innopolis.stc9.saturn.db.entities.User;

import java.util.List;

public interface UsersListService
{
    List<User> getUsersList();
    void changeEnableStatus(String str);
    boolean update(String editID, String editInfo, String editRole);
    void delete(String str);
}
