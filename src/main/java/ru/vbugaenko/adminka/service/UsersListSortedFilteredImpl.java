package ru.innopolis.stc9.saturn.service;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import ru.innopolis.stc9.saturn.db.entities.User;
import ru.innopolis.stc9.saturn.service.enums.Filter;
import ru.innopolis.stc9.saturn.service.enums.Roles;
import ru.innopolis.stc9.saturn.service.enums.SortBy;
import ru.innopolis.stc9.saturn.service.utility.FilterFromStringImpl;
import ru.innopolis.stc9.saturn.service.utility.RoleFromStringImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static ru.innopolis.stc9.saturn.service.enums.Filter.NONE;
import static ru.innopolis.stc9.saturn.service.enums.Roles.ALL;
import static ru.innopolis.stc9.saturn.service.enums.SortBy.REGDATE_tL;

/**
 * Holder usersList with Meta-Data;
 *
 * @author Victor Bugaenko
 * @since 25.04.2018
 */

@Component
public class UsersListSortedFilteredImpl implements UsersListSortedFiltered
{
    final Logger logger = Logger.getLogger(UsersListSortedFilteredImpl.class);
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
            {
                logger.error("проблема с формирование списка пользователей при фильтрации");
            }
        this.usersList = usersListtmp;
    }

    private void sorting()
    {
        try {
            if (sortBy == SortBy.REGDATE_tL) Collections.sort(this.usersList, User.COMPARE_BY_RegDATE_tL);
            if (sortBy == SortBy.REGDATE_tU) Collections.sort(this.usersList, User.COMPARE_BY_RegDATE_tU);
            if (sortBy == SortBy.ADDRESS_tL) Collections.sort(this.usersList, User.COMPARE_BY_ADDRESS_tL);
            if (sortBy == SortBy.ADDRESS_tU) Collections.sort(this.usersList, User.COMPARE_BY_ADDRESS_tU);
            if (sortBy == SortBy.SURNAME_tL) Collections.sort(this.usersList, User.COMPARE_BY_SURNAME_toLESS);
            if (sortBy == SortBy.SURNAME_tU) Collections.sort(this.usersList, User.COMPARE_BY_SURNAME_toUPPER);
            if (sortBy == SortBy.AGE_tL)     Collections.sort(this.usersList, User.COMPARE_BY_AGE_toLESS);
            if (sortBy == SortBy.AGE_tU)     Collections.sort(this.usersList, User.COMPARE_BY_AGE_toUPPER);
        }
        catch (Exception e)
        {
            logger.error("проблема с сортировкой пользователей");
        }
    }

    public void setUsersList(List<User> usersList)      { this.usersList=usersList; }
    public void setSortBy   (SortBy sortBy)             { this.sortBy = sortBy;     }
    public void setFilter   (Filter filter)             { this.filter = filter;     }
    public void setRole     (Roles role)                { this.role = role;         }
    public void setPage     (int page)                  { this.page = page;         }
}
