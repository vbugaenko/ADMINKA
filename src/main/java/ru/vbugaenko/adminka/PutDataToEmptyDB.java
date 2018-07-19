package ru.vbugaenko.adminka;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import ru.vbugaenko.adminka.db.entities.History;
import ru.vbugaenko.adminka.db.entities.Password;
import ru.vbugaenko.adminka.db.entities.Role;
import ru.vbugaenko.adminka.db.entities.User;


import java.util.ArrayList;
import java.util.List;


/**
 * Заполнить (пустую) базу (первичными, тестовыми) данными.
 *
 * @author Victor Bugaenko
 * @since 30.06.2018
 */
public class PutDataToEmptyDB
{
    private static final Logger loggerConsoleInf = Logger.getLogger("consoleinf");
    private static Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
    private static List<User> users;

    public static void main(String[] args)
    {
        users = createUsers();
        setHistoryForUsers();
        addUsersToDB();
    }

    private static List<User> createUsers()
    {
        List<User> users = new ArrayList();
        User user1 = new User();
        User user2 = new User();
        User user3 = new User();
        User user4 = new User();

        user1.setLogin("admin");
        user2.setLogin("teacher");
        user3.setLogin("student");
        user4.setLogin("h.teacher");

        user1.setRole(new Role(1, "ROLE_ADMIN", "ADMIN")    );
        user2.setRole(new Role(2, "ROLE_TEACHER", "TEACHER"));
        user3.setRole(new Role(3, "ROLE_STUDENT", "STUDENT"));
        user4.setRole(new Role(4, "ROLE_HEADTEACHER", "H.TEACHER"));

        user1.setEmail("admin@email.ru");
        user2.setEmail("teacher@email.ru");
        user3.setEmail("student@email.ru");
        user4.setEmail("h.teacher@email.ru");

        user1.setCity("Москва");
        user2.setCity("Петропавловск-Камчатский");
        user3.setCity("Комсомольск-на-Амуре");
        user4.setCity("Александровск-Сахалинский");

        user1.setFullAddress("103073, г.Москва, Кремль");
        user2.setFullAddress("020202, г.Петропавловск-Камчатский, пр-т.Красного знамени, д.108а, кв.34");
        user3.setFullAddress("030303, г.Комсомольск-на-Амуре, пр-т.им.Ленина, д.15, кв.190");
        user4.setFullAddress("040404, г.Александровск-Сахалинский, ул.Коммунаров, д.167, кв.11");

        user1.setEnabled(true);
        user2.setEnabled(true);
        user3.setEnabled(true);
        user4.setEnabled(true);

        user1.setName("Путин Владимир");
        user2.setName("Абдурахмангаджи Константин");
        user3.setName("Христорождественская Вильгельмина");
        user4.setName("Дэнни ДеВито");

        user1.setInfo("Президент РФ");
        user2.setInfo("Актер");
        user3.setInfo("Актриса");
        user4.setInfo("Актер");

        user1.setPhone("+70000000001");
        user2.setPhone("+70000000002");
        user3.setPhone("+70000000003");
        user4.setPhone("+70000000004");

        user1.setPhoto("https://24smi.org/public/media/235x307/celebrity/2017/02/14/VTbS2hRAEwfe_vladimir-putin.jpg");
        user2.setPhoto("http://www.orthedu.ru/uploads/posts/2009-08/1250560980_789.jpg");
        user3.setPhoto("https://i.pinimg.com/originals/da/e5/3c/dae53c03688c75acc2b113e95cd287e1.jpg");
        user4.setPhoto("https://www.film.ru/sites/default/files/persones/_imported/0000362.jpg");

        user1.setAge(65);
        user2.setAge(64);
        user3.setAge(72);
        user4.setAge(73);

        users.add( user1 );
        users.add( user2 );
        users.add( user3 );
        users.add( user4 );

        return users;
    }

    private static void setHistoryForUsers()
    {
        for(User u : users)
        {
            u.addHistory("Регистрация");
            u.addHistory("Активация");
        }
    }

    /**
     * Дополнительные "ручные" операции по сохранению пароля пользователя обусловлены тем,
     * что user в себе хешпароля не содержит (сделано специально ддя большей безопасности).
     */
    private static void addUsersToDB()
    {
        try (Session session = cfg.buildSessionFactory().openSession())
        {
            session.beginTransaction();
            Integer i = 0;
            for(User u : users)
            {
                i++;
                session.save(u);
                session.save(u.getRole());
                for (History h : u.getHistory())
                    session.save(h);
                Password password = u.setPassword( i.toString() );
                password.setUser(u);
                session.save( password );
                u.setPassword_id( password.getId() );
            }
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e)
        {
            loggerConsoleInf.error( e.getMessage() );
        }

    }


}
