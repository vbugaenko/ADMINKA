package ru.innopolis.stc9.saturn.service.utility;

import ru.innopolis.stc9.saturn.service.enums.Roles;

public interface RoleFromString
{
    Roles recognize(String roleStr);
    int roleInt ( String roleStr );
    Roles def();
}
