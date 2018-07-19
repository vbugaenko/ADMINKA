package ru.vbugaenko.adminka.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.vbugaenko.adminka.db.entities.Password;
import ru.vbugaenko.adminka.db.entities.User;


/**
 * @author Victor Bugaenko
 * @since 12.07.2018
 */

@Repository
public class RegistrationDAOImpl implements RegistrationDAO
{

    Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");


    /**
     * Очень неудобно сохранять по отдельности пользователя и пароль,
     * лучше это делать в одной транзакции.
     * Метода используется:
     * - RegistrationService;
     */
    public boolean add(User user, String passwordHash)
    {
        if (user == null) return false;
        Password password = new Password();
        password.setPasswordhash( passwordHash );

        //Todo выяснить этот вопрос, поскольку логику переусложняет!
        /**
         * Код немного громоздкий, но лишь потому,
         * что User лиген возможности содержать passwordHash в себе.
         * Поэтому приходится связь между user и password прописывать вручную
         */
        /**
         * Например хакер запросил всех студентов
         * и получил список user'ов и их хешированных паролей,
         * которые (зная криптор/декриптор легко восстановил)
         * /
         /**
         * С другой стороны там же стоит фильтр из jsp ...
         * может быть он ничего и не пропустит... но не слишком ли тонкая преграда?
         * Фаил лежит отдельно на сервере, а код проекта выложен в открытй репо
         * и если фаил подменить, то вся информация окажется снаружи).
         */
        //Todo: регистрация - добавить проверку ролей
        //Todo: регистрация - добавить историю
        try ( Session session = cfg.buildSessionFactory().openSession() )
        {
            session.beginTransaction();
            session.save( user );
            password.setUser( user );
            session.save( password );
            user.setPassword_id( password.getId() );
            session.getTransaction().commit();
            session.close();
        }
        catch(Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return true;
    }
}