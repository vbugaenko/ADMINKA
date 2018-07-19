package ru.innopolis.stc9.saturn.db.entities;


import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

@XmlRootElement(name="users")
public class UsersListEntity
{
    private static final long serialVersionUID = 1L;
    private List<User> employees = new ArrayList();

    public List<User> getEmployees() {
        return employees;
    }

    public void setEmployees(List<User> employees) {
        this.employees = employees;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        for (User u: employees)
        {
            builder.append(u);
            builder.append("\n");
        }
        return builder.toString();
    }
}
