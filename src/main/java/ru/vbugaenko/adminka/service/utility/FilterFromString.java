package ru.innopolis.stc9.saturn.service.utility;

import ru.innopolis.stc9.saturn.service.enums.Filter;

public interface FilterFromString
{
    Filter recognize(String str);
    Filter def();
}
