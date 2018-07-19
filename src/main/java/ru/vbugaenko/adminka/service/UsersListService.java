package ru.vbugaenko.adminka.service;

import ru.vbugaenko.adminka.db.entities.User;
import java.util.List;

public interface UsersListService
{
    List<User> getUsersList();
    void changeEnableStatus(String str);
    void update(String editID, String editInfo, String editRole);
    void delete(String str);
}
