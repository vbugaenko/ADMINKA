package ru.vbugaenko.adminka.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import ru.vbugaenko.adminka.db.entities.User;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 * refactored
 * @author Victor Bugaenko
 * @since 10.07.2018
 */

@Repository
public class UsersDAOImpl implements UsersDAO
{
    private Configuration cfg = new org.hibernate.cfg.Configuration().configure("hibernate.cfg.xml");

    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");


    public User getByLogin(String login) {
        if (login.isEmpty()) return null;
        User user = null;
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).where(builder.equal(root.get("login"), login));
        user = session.createQuery(criteria).uniqueResult();
        }
        catch(Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return user;
    }

    public User getById(int id)
    {
        User user = null;
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
        user = session.get(User.class, id);
        session.close();
        }
        catch(Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return user;
    }

    //TODO: пустые методы!
    @Override
    public boolean add(User user) {
        return false;
    }

    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public void update(String pwd, int uid) {

    }

    @Override
    public String getPwd(int uid) {
        return null;
    }

    @Override
    public boolean checkLogin(String login) {
        return false;
    }

    public void updateUser(User user)
    {
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
        }
        catch(Exception e)
        { loggerFileInf.error(e.getMessage()); }
    }

    /**
     * setRegdate и setLastactivity не должны в ручную меняться.
     * для них нужно создать отдельныые методы.
     */
    @Override
    public void update(int id, User user)
    {
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            User u = session.get(User.class, id);
            u.setName(user.getName());
            session.update(u);
            session.getTransaction().commit();
            session.close();
        }
        catch(Exception e)
        { loggerFileInf.error(e.getMessage()); }
    }

    //TODO: Удалить метод
    public boolean checkUserLogin(String userLogin)
    {
        if(getByLogin(userLogin) == null)
            return true;
        return false;
    }
}
