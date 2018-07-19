package ru.vbugaenko.adminka.service;


import ru.vbugaenko.adminka.db.entities.User;
import ru.vbugaenko.adminka.service.enums.Filter;
import ru.vbugaenko.adminka.service.enums.Roles;
import ru.vbugaenko.adminka.service.enums.SortBy;

import java.util.List;

public interface UsersListSortedFiltered
{
    int         begin();
    int         end();
    List<User>  getUsersList();
    SortBy      getSortBy();
    Filter      getFilter();
    Roles       getRole();
    int         getPage();

    void setUsersList(List<User> usersList);
    void setSortBy(SortBy sortBy);
    void setFilter(Filter filter);
    void setRole(Roles role);
    void setPage(int page);
}
