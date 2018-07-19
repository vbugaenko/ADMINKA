package ru.vbugaenko.adminka.service.utility;

import org.springframework.stereotype.Component;
import ru.vbugaenko.adminka.service.enums.Roles;

import static ru.vbugaenko.adminka.service.enums.Roles.*;

@Component
public class RoleFromStringImpl implements RoleFromString
{

    /**
     * При нессоответствии распознаваемой строки условию,
     * метод возвращает значение ALL (отображение в таблице всех ролей).
     */
    @Override
    public Roles recognize(String roleStr)
    {

        if (roleStr != null )
        switch (roleStr) {
            case ("ALL"):
                return ALL;

            case ("ADMIN"):
                return ADMIN;

            case ("TEACHER"):
                return TEACHER;

            case ("STUDENT"):
                return STUDENT;

            case ("H.TEACHER"):
                return HEADTEACHER;
        }
        return def();
    }

    @Override
    public int roleInt(String roleStr)
    {
        return roleInt(recognize(roleStr));
    }

    private int roleInt(Roles role)
    {
        if (role == HEADTEACHER)
            return 4;
        if (role == STUDENT)
            return 3;
        if (role == TEACHER)
            return 2;
        if (role == ADMIN)
            return 1;
        else
            return 0;
    }

    public Roles def()
    {
        return ALL;
    }

    public boolean compare(int i, Roles r)
    {
        if (r==ALL)
            return true;
        if (i==1 && r==ADMIN)
            return true;
        if (i==2 && r==TEACHER)
            return true;
        if (i==3 && r==STUDENT)
            return true;
        if (i==4 && r==HEADTEACHER)
            return true;
        return false;
    }
}
