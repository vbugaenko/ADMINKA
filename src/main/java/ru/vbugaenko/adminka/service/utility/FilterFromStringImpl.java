package ru.vbugaenko.adminka.service.utility;

import org.springframework.stereotype.Component;
import ru.vbugaenko.adminka.service.enums.Filter;

import static ru.vbugaenko.adminka.service.enums.Filter.ACTIVE;
import static ru.vbugaenko.adminka.service.enums.Filter.DISABLED;
import static ru.vbugaenko.adminka.service.enums.Filter.NONE;

@Component
public class FilterFromStringImpl implements FilterFromString
{

    /**
     * Метод не просто распознает текущий фильтр, но и сдвигает на следующий:
     * был A станет D ... и т.п.
     */

    public Filter recognize(String str)
    {
        if ((str != null)&&(!str.equals("")))
        {
            if (str.equals("N"))
                return ACTIVE;

            if (str.equals("A"))
                return DISABLED;

            if (str.equals("D"))
                return NONE;
        }
        return def();
    }

    public Filter def()
    {
        return NONE;
    }

    public boolean compare(boolean b, Filter f)
    {
        if ((f == ACTIVE)&&(b))
            return true;
        if ((f == DISABLED)&&(!b))
            return true;
        if (f == NONE)
            return true;
        return false;
    }
}
