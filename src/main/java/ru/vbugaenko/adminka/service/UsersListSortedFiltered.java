package ru.innopolis.stc9.saturn.service;

import ru.innopolis.stc9.saturn.db.entities.User;
import ru.innopolis.stc9.saturn.service.enums.Filter;
import ru.innopolis.stc9.saturn.service.enums.Roles;
import ru.innopolis.stc9.saturn.service.enums.SortBy;

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
