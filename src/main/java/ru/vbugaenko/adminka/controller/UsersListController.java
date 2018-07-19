package ru.innopolis.stc9.saturn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.saturn.service.UsersListService;
import ru.innopolis.stc9.saturn.service.UsersListSortedFiltered;
import ru.innopolis.stc9.saturn.service.utility.*;


@Controller
public class UsersListController
{
    private UsersListSortedFiltered usersListSortedFiltered;
    private UsersListService usersListService;
    private SortByFromString sortByFromString;
    private RoleFromString   roleFromString;
    private FilterFromString filterFromString;
    private PageFromString   pageFromString;
    private IntFromString    intFromString;

    @Autowired
    public UsersListController
            (
                    UsersListService usersListService,
                    UsersListSortedFiltered usersListSortedFiltered,
                    SortByFromString sortByFromString,
                    RoleFromString roleFromString,
                    FilterFromString filterFromString,
                    PageFromString pageFromString,
                    IntFromString intFromString
            )
    {
        this.usersListService        = usersListService;
        this.usersListSortedFiltered = usersListSortedFiltered;
        usersListSortedFiltered.setUsersList( usersListService.getUsersList() );
        this.sortByFromString        = sortByFromString;
        this.roleFromString          = roleFromString;
        this.filterFromString        = filterFromString;
        this.pageFromString          = pageFromString;
        this.intFromString           = intFromString;
    }

    @RequestMapping("/admin/usersList")
    public String showUsersList(Model model)
    {
        model.addAttribute("users",     usersListSortedFiltered.getUsersList()  );
        model.addAttribute("sortBy",    usersListSortedFiltered.getSortBy()     );
        model.addAttribute("filter",    usersListSortedFiltered.getFilter()     );
        model.addAttribute("usersRole", usersListSortedFiltered.getRole()       );
        model.addAttribute("page",      usersListSortedFiltered.getPage()       );
        model.addAttribute("beginInt",  usersListSortedFiltered.begin()         );
        model.addAttribute("endInt",    usersListSortedFiltered.end()           );
        model.addAttribute("total",     usersListSortedFiltered.end()           );
        return "usersList";
    }

    @RequestMapping(value="/admin/usersList", method = RequestMethod.GET)
    public String showUsersList(
            @RequestParam(value = "sortBy",      required = false) String sortBy,
            @RequestParam(value = "filter",      required = false) String filter,
            @RequestParam(value = "role",        required = false) String role,
            @RequestParam(value = "page",        required = false) String page,
            @RequestParam(value = "idForUpdate", required = false) String idForUpdate,
            @RequestParam(value = "idForDelete", required = false) String idForDelete,
            @RequestParam(value = "activateID",  required = false) String activateID,
            @RequestParam(value = "editID",      required = false) String editID,
            @RequestParam(value = "editInfo",    required = false) String editInfo,
            @RequestParam(value = "editRole",    required = false) String editRole,
            Model model )
    {

        if (sortBy != null)
            usersListSortedFiltered.setSortBy(sortByFromString.recognize(sortBy));

        if (filter != null)
            usersListSortedFiltered.setFilter(filterFromString.recognize(filter.trim()));

        if (role != null)
            usersListSortedFiltered.setRole(roleFromString.recognize(role));

        if (page != null)
            usersListSortedFiltered.setPage(pageFromString.recognize(page));

        int idEdttUser = 0;
        if (editID != null)
            idEdttUser = intFromString.recognize(idForUpdate);

        if (idForDelete != null)
            usersListService.delete(idForDelete);

        if (activateID != null)
            usersListService.changeEnableStatus(activateID);

        if (idForUpdate != null)
            usersListService.update(editID, editInfo, editRole);


        usersListSortedFiltered.setUsersList(usersListService.getUsersList());

        model.addAttribute("users",     usersListSortedFiltered.getUsersList());
        model.addAttribute("sortBy",    usersListSortedFiltered.getSortBy());
        model.addAttribute("filter",    usersListSortedFiltered.getFilter());
        model.addAttribute("usersRole", usersListSortedFiltered.getRole());
        model.addAttribute("page",      usersListSortedFiltered.getPage());
        model.addAttribute("beginInt",  usersListSortedFiltered.begin());
        model.addAttribute("endInt",    usersListSortedFiltered.end());
        model.addAttribute("total",     usersListSortedFiltered.getUsersList().size());
        model.addAttribute("editID", idEdttUser);

        return "usersList";
    }
}
