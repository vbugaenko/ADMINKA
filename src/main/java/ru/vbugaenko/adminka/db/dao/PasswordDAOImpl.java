package ru.innopolis.stc9.saturn.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.saturn.db.entities.Password;
import ru.innopolis.stc9.saturn.db.entities.Teacher;
import ru.innopolis.stc9.saturn.db.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

@Repository
public class PasswordDAOImpl implements PasswordDAO
{
    Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");


    @Override
    public void upt(int id, String passwordHash)
    {
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            Password password = session.get(Password.class, id);
            password.setPasswordhash( passwordHash );
            session.update( password );
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
    }

    @Override
    public void del(int id)
    {
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            Password password = session.get(Password.class, id);
            session.remove( password );
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
    }

    @Override
    public String get(int userId)
    {
        Password password=null;
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Password> criteria = builder.createQuery(Password.class);
        Root<Password> root = criteria.from(Password.class);
        criteria.select(root).
                where(builder.equal(root.get("user"), userId));
        password =  session.createQuery(criteria).uniqueResult();
            session.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            e.printStackTrace();
            loggerFileInf.error(e.getMessage()); }
        return password.getPasswordhash();
    }
}
