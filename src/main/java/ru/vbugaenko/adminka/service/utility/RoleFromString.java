package ru.vbugaenko.adminka.service.utility;

import ru.vbugaenko.adminka.service.enums.Roles;

public interface RoleFromString
{
    Roles recognize(String roleStr);
    int roleInt(String roleStr);
    Roles def();
}
