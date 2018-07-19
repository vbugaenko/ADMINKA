package ru.vbugaenko.adminka.service.utility;


import ru.vbugaenko.adminka.service.enums.SortBy;

public interface SortByFromString
{
    SortBy recognize(String sortBy);
    SortBy def();
}
