package ru.vbugaenko.adminka.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import ru.vbugaenko.adminka.db.entities.Role;


import java.util.ArrayList;
import java.util.List;


/**
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

@Repository
public class RolesDAOImpl implements RolesDAO
{
    Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");

    @Override
    public void add(Role role)
    {
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            session.save(role);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
    }

    @Override
    public Role get(int id)
    {
        Role role = null;
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            role = session.get(Role.class, id);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return role;
    }

    /**
     * Присваивать user.setRole("ADMIN") очень наглядно.
     */
    @Override
    public Role getByName(String roleName)
    {
        List<Role> roles = new ArrayList();
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            NativeQuery query = session.createNativeQuery("SELECT * FROM roles;");
            query.addEntity(Role.class);
            roles = query.list();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }

        for(Role r : roles)
            if (r.getShortname().equals(roleName))
                return r;
        return null;
    }
}