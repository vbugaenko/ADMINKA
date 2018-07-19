package ru.innopolis.stc9.saturn.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.saturn.db.entities.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * refactored
 * @author Victor Bugaenko
 * @since 10.07.2018
 */

@Configuration
@Repository
public class UsersDAOImpl implements UsersDAO
{
    @Autowired
    private SessionFactory sessionFactory;

    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");

    @Autowired
    public UsersDAOImpl(SessionFactory factory)
    {
        this.sessionFactory = factory;
    }

    private Session openSession() {
        try
        {
            //Step-2: Implementation
            return sessionFactory.getCurrentSession();
        }
        catch (HibernateException e)
        {
            //Step-3: Implementation
            return  sessionFactory.openSession();
        }
    }

    public User getByLogin(String login) {
        if (login.isEmpty()) return null;
        Session session = openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> root = criteria.from(User.class);
        criteria.select(root).
                where(builder.equal(root.get("login"), login));
        return session.createQuery(criteria).uniqueResult();
    }

    public User getById(int id) {
        Session session = openSession();
        User users = session.get(User.class, id);
        session.close();
        return users;
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

    public void updateUser(User user) {
        Session session = openSession();
        session.beginTransaction();
        session.update(user);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * setRegdate и setLastactivity не должны в ручную меняться.
     * для них нужно создать отдельныые методы.
     */
    @Override
    public void update(int id, User user) {
        try(Session session = openSession()) {
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
