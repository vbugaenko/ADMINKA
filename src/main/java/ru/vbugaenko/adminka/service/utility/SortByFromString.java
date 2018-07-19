package ru.innopolis.stc9.saturn.service.utility;

import ru.innopolis.stc9.saturn.service.enums.SortBy;

public interface SortByFromString
{
    SortBy recognize(String sortBy);
    SortBy def();
}
