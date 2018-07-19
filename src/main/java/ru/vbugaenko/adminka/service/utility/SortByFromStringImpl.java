package ru.vbugaenko.adminka.service.utility;

import org.springframework.stereotype.Component;
import ru.vbugaenko.adminka.service.enums.SortBy;


@Component
public class SortByFromStringImpl implements SortByFromString
{

    /**
     * При нессоответствии распознаваемой строки условию,
     * метод возвращает используемую сортировку по умолчанию.
     */
    public SortBy recognize(String sortBy)
    {

        if ((sortBy != null)||(!sortBy.equals("")))
        {
            sortBy = sortBy.trim();
            if (sortBy.equals("Address↓"))
                return SortBy.ADDRESS_tU;
            if (sortBy.equals("Address↑"))
                return SortBy.ADDRESS_tL;
            if (sortBy.equals("Address"))
                return SortBy.ADDRESS_tL;

            if (sortBy.equals("Date↓"))
                return SortBy.REGDATE_tU;
            if (sortBy.equals("Date↑"))
                return SortBy.REGDATE_tL;
            if (sortBy.equals("Date"))
                return SortBy.REGDATE_tL;

            if (sortBy.equals("Name↓"))
                return SortBy.SURNAME_tU;
            if (sortBy.equals("Name↑"))
                return SortBy.SURNAME_tL;
            if (sortBy.equals("Name"))
                return SortBy.SURNAME_tL;

            if (sortBy.equals("Age↓"))
                return SortBy.AGE_tU;
            if (sortBy.equals("Age↑"))
                return SortBy.AGE_tL;
            if (sortBy.equals("Age"))
                return SortBy.AGE_tL;
        }

        return def();
    }

    public SortBy def()
    {
        return SortBy.REGDATE_tL;
    }
}
