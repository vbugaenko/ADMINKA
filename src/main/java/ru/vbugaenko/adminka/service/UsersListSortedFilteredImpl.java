package ru.vbugaenko.adminka.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.vbugaenko.adminka.db.entities.User;
import ru.vbugaenko.adminka.service.enums.Filter;
import ru.vbugaenko.adminka.service.enums.Roles;
import ru.vbugaenko.adminka.service.enums.SortBy;
import ru.vbugaenko.adminka.service.utility.FilterFromStringImpl;
import ru.vbugaenko.adminka.service.utility.RoleFromStringImpl;


import java.util.ArrayList;
import java.util.List;

import static ru.vbugaenko.adminka.service.enums.Filter.NONE;
import static ru.vbugaenko.adminka.service.enums.Roles.ALL;
import static ru.vbugaenko.adminka.service.enums.SortBy.REGDATE_tL;


/**
 * Holder usersList with Meta-Data;
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

@Component
public class UsersListSortedFilteredImpl implements UsersListSortedFiltered
{
    private final Logger loggerFileInf = Logger.getLogger("fileinf");
    private List<User> usersList;
    private SortBy sortBy   = REGDATE_tL;
    private Filter filter   = NONE;
    private Roles role      = ALL;
    private int page        = 1;
    private int limit       = 10;

    private boolean recognizeRole(int i, Roles r)
    {
        return new RoleFromStringImpl().compare(i, r);
    }

    private boolean recognizeFilter(boolean b, Filter f)
    {
        return new FilterFromStringImpl().compare(b, f);
    }

    public int begin() { return (page-1)*limit;  }

    public int end()   { return begin()+limit-1; }

    public List<User> getUsersList()
    {
        sorting();
        filtering();
        return usersList;
    }

    public SortBy getSortBy()               { return sortBy;    }
    public Filter getFilter()               { return filter;    }
    public Roles  getRole()                 { return role;      }
    public int    getPage()                 { return page;      }

    private void filtering()
    {
        ArrayList usersListtmp = new ArrayList();
        for (User u : usersList)
            try
            {
                if (recognizeRole(u.getRole().getId(), role) && recognizeFilter(u.getEnabled(), filter))
                    usersListtmp.add(u);
            }
            catch (Exception e)
            { loggerFileInf.error( e.getMessage()); }
        this.usersList = usersListtmp;
    }

    private void sorting()
    {
        if (sortBy == SortBy.REGDATE_tL)
            usersList.sort((u1, u2) -> u1.getRegdate().compareTo(u2.getRegdate()));

        if (sortBy == SortBy.REGDATE_tU)
            usersList.sort((u1, u2) -> u2.getRegdate().compareTo(u1.getRegdate()));

        if (sortBy == SortBy.ADDRESS_tL)
            usersList.sort((u1, u2) -> u1.getFullAddress().compareTo(u2.getFullAddress()));

        if (sortBy == SortBy.ADDRESS_tU)
            usersList.sort((u1, u2) -> u2.getFullAddress().compareTo(u1.getFullAddress()));

        if (sortBy == SortBy.SURNAME_tL)
            usersList.sort((u1, u2) -> u1.getName().compareTo(u2.getName()));

        if (sortBy == SortBy.SURNAME_tU)
            usersList.sort((u1, u2) -> u2.getName().compareTo(u1.getName()));

        if (sortBy == SortBy.AGE_tL)
            usersList.sort((u1, u2) -> u1.getBirthdate().compareTo(u2.getBirthdate()));

        if (sortBy == SortBy.AGE_tU)
            usersList.sort((u1, u2) -> u2.getBirthdate().compareTo(u1.getBirthdate()));
    }

    public void setUsersList(List<User> usersList)      { this.usersList=usersList; }
    public void setSortBy   (SortBy sortBy)             { this.sortBy = sortBy;     }
    public void setFilter   (Filter filter)             { this.filter = filter;     }
    public void setRole     (Roles role)                { this.role = role;         }
    public void setPage     (int page)                  { this.page = page;         }
}
