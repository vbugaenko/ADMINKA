package ru.vbugaenko.adminka.db.dao;


import ru.vbugaenko.adminka.db.entities.History;

import java.util.Date;
import java.util.List;

/**
 * refactored
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

public interface UserHistoryJpaDao
{
    void            add(int userId, Date date, String description);
    List<History>   delete(int id);
    List<History>   clear(int userId);
    List<History>   clearAll();
    List<History>   get(int userId);
    List<History>   getAll();
}
