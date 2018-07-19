package ru.vbugaenko.adminka.service.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageFromStringImpl implements  PageFromString
{

    @Autowired
    IntFromString intFromString;
    /**
     * страница со списком пользователей не может быть 0.
     */

    public int recognize(String str)
    {
        int i = intFromString.recognize(str);
        if (i <= 0)
            return 1;
        else
            return i;
    }


}
