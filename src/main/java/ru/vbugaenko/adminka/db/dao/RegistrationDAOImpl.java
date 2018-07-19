package ru.innopolis.stc9.saturn.db.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc9.saturn.db.entities.Password;
import ru.innopolis.stc9.saturn.db.entities.User;

/**
 * @author Victor Bugaenko
 * @since 12.07.2018
 */

@Repository
public class RegistrationDAOImpl implements RegistrationDAO
{
    @Autowired
    private SessionFactory sessionFactory;

    final Logger loggerFileInf = Logger.getLogger("fileinf");
    final Logger loggerConsoleInf = Logger.getLogger("consoleinf");

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
        //Todo: добавить из регистрации проверку ролей
        //Todo: добавить сохранение ролевой специфики
        try( Session session = openSession() )
        {
            session.beginTransaction();
            session.save( user );
            password.setUser( user );
            session.save( password );
            user.setPassword_id( password.getId() );
            //session.update( user ); итак в транзитивном
            session.getTransaction().commit();
            session.close();
        }
        catch(Exception e)
        { loggerFileInf.error(e.getMessage()); }
        return true;
    }
}
