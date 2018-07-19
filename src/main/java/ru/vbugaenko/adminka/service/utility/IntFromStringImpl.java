package ru.vbugaenko.adminka.service.utility;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class IntFromStringImpl implements IntFromString
{
    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");

    public int recognize(String str)
    {
        if ((str != null)&&(!str.equals("")))
        {
            try
            {
                return Integer.parseInt(str);
            }
            catch(Exception e)
            {
                loggerFileInf.warn("not valid data " + e.getMessage());
            }
        }
        return 0;
    }
}
