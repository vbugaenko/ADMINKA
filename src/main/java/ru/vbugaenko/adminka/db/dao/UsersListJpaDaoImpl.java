package ru.vbugaenko.adminka.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.vbugaenko.adminka.db.entities.History;
import ru.vbugaenko.adminka.db.entities.Role;
import ru.vbugaenko.adminka.db.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class works with User entity (for usersList.jsp - admin's form).
 *
 * @author Victor Bugaenko
 * @since 30.06.2018
 */

@Component
public class UsersListJpaDaoImpl implements UsersListJpaDao
{
    Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");

    @Autowired
    RolesDAOImpl rolesDAO;

    public List<User> getAllUsers()
    {
        List<User> users = new ArrayList();
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            NativeQuery query = session.createNativeQuery("SELECT * FROM users;");
            query.addEntity(User.class);
            users = query.list();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return users;
    }

    public List<User> save(User user)
    {
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            session.save(user);
            for ( History h : user.getHistory() )
                session.save(h);
            session.getTransaction().commit();
            session.close();
        }
        catch(Exception e)
        {
            loggerFileInf.error(e.getMessage());
        }

        return getAllUsers();
    }

    //TODO: 2018-07-17 15:06:23 ERROR consoleinf:81 - deleted object would be re-saved by cascade (remove deleted object from associations): [ru.innopolis.stc9.saturn.db.entities.User#3]
    public List<User> delete(int idForD)
    {
        System.out.println(idForD);
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            User u = session.get(User.class, idForD);
            session.remove(u);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        {
            loggerConsoleInf.error(e.getMessage());
            loggerFileInf.error(e.getMessage());
        }
        return getAllUsers();
    }


    public List<User> updateInfo(int idForUpd, String newInfo)
    {
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            User u = session.get(User.class, idForUpd);
            u.setInfo(newInfo);
            session.update(u);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return getAllUsers();
    }

    public List<User> updateRole(int idForUpd, int newRole)
    {
        Role role = rolesDAO.get(newRole);
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            User u = session.get(User.class, idForUpd);
            u.setRole(role);
            session.update(u);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return getAllUsers();
    }


    public List<User> setEnabledStatus(int id, boolean enabled)
    {
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            User u = session.get(User.class, id);
            u.setEnabled(enabled);
            session.update(u);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        {
            loggerFileInf.error(e.getMessage());
        }
        return getAllUsers();
    }
}