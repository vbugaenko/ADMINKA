package ru.innopolis.stc9.saturn.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;
import ru.innopolis.stc9.saturn.db.entities.History;
import ru.innopolis.stc9.saturn.db.entities.User;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * This class works with User entity (for usersList.jsp - admin's form).
 *
 * @author Victor Bugaenko
 * @since 01.06.2018
 */

public class UserHistoryJpaDaoImpl implements UserHistoryJpaDao
{
    private Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
    private SessionFactory sessionFactory=cfg.buildSessionFactory();
    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");

    /**
     * Метод добавляет историю активности User'а.
     */
    @Override
    public void add(int userId, Date date, String description)
    {
        try (Session session = sessionFactory.openSession() )
        {
            Transaction trans = session.beginTransaction();
            User user = session.get(User.class, userId);
            session.save(new History(user, description));
            trans.commit();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
    }

    /**
     * Метод возвращает историю активности User'а.
     */
    @Override
    public List<History> get(int userId)
    {
        List<History> history = new ArrayList();
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            NativeQuery query = session.createNativeQuery("SELECT * FROM history WHERE users_id="+userId);
            query.addEntity(History.class);
            history = query.list();
            session.close();
        }
        catch (Exception e)
        {
            loggerFileInf.error(e.getMessage());
        }
        return history;
    }

    @Override
    public List<History> getAll()
    {
        List<History> history = new ArrayList();
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            NativeQuery query = session.createNativeQuery("SELECT * FROM history");
            query.addEntity(History.class);
            history = query.list();
            session.close();
        } catch (Exception e) {
            loggerFileInf.error(e.getMessage());
        }
        return history;
    }

    @Override
    public List<History> clear(int userId)
    {
        try (Session session = cfg.buildSessionFactory().openSession())
        {
            session.createNativeQuery("DELETE FROM history WHERE users_id="+userId);
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return getAll();
    }

    @Override
    public List<History> delete(int id)
    {
        try (Session session = cfg.buildSessionFactory().openSession())
        {
            session.beginTransaction();
            History h = session.get(History.class, id);
            session.delete(h);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return getAll();
    }

    @Override
    public List<History> clearAll()
    {
        try (Session session = cfg.buildSessionFactory().openSession())
        {
            session.createNativeQuery("DELETE * FROM history");
            session.close();
        } catch (Exception e) {
            loggerFileInf.error(e.getMessage());
        }
        return getAll();
    }

}
