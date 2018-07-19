package ru.innopolis.stc9.saturn.db.dao;

import ru.innopolis.stc9.saturn.db.entities.User;

/**
 * refactored
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

public interface UsersDAO
{
    User get(int id);
    User getByLogin(String login);
    User getById(int id);
    void update(String pwd, int uid);
    String getPwd(int uid);

    /**
     * Used by RegistrationServiceImpl
     */
    boolean checkLogin(String login);

    /**
     * Used by RestUserController
     */
    boolean add(User user);
    void update(int recognize, User user);


}




