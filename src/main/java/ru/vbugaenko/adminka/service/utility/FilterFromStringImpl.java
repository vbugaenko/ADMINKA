package ru.innopolis.stc9.saturn.service.utility;

import org.springframework.stereotype.Component;
import ru.innopolis.stc9.saturn.service.enums.Filter;

import static ru.innopolis.stc9.saturn.service.enums.Filter.*;

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
                return Filter.ACTIVE;

            if (str.equals("A"))
                return Filter.DISABLED;

            if (str.equals("D"))
                return Filter.NONE;
        }
        return def();
    }

    public Filter def()
    {
        return Filter.NONE;
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
