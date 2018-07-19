package ru.vbugaenko.adminka.service.utility;

import ru.vbugaenko.adminka.service.enums.Filter;

public interface FilterFromString
{
    Filter recognize(String str);
    Filter def();
}
