package ru.vbugaenko.adminka.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vbugaenko.adminka.db.dao.UsersListJpaDao;
import ru.vbugaenko.adminka.db.entities.User;
import ru.vbugaenko.adminka.service.utility.IntFromString;
import ru.vbugaenko.adminka.service.utility.RoleFromString;

import java.util.List;

/**
 * Service class for UsersListController.
 *
 * @author Victor Bugaenko
 * @update 15.07.2018
 * @since 05.05.2018
 */

@Service
public class UsersListServiceImpl implements UsersListService
{
    private final Logger loggerFileInf = Logger.getLogger("fileinf");

    private List<User> usersList;
    private UsersListJpaDao usersListJpaDao;
    private RoleFromString roleFromString;
    private IntFromString intFromString;

    @Autowired
    public UsersListServiceImpl
            (
                    UsersListJpaDao usersListJpaDao,
                    RoleFromString roleFromString,
                    IntFromString intFromString
            )
    {
        this.usersListJpaDao = usersListJpaDao;
        this.usersList = usersListJpaDao.getAllUsers();
        this.roleFromString = roleFromString;
        this.intFromString = intFromString;
    }

    public List<User> getUsersList() {
        return usersList;
    }


    public void changeEnableStatus(String idStr)
    {
        if (!idStr.equals(""))
            changeEnableStatus ( recognizeId(idStr) );
    }

    /**
     * Переменная answer нужна для тестирования этого метода.
     */
    private void changeEnableStatus(int id)
    {
        User user = getUserById(id);
        if ( user != null )
            usersList = usersListJpaDao.setEnabledStatus( id, !user.getEnabled() );
    }

    /**
     * метод использует уже имеющийся список пользователей, без обращения к БД.
     */
    private User getUserById(int id)
    {
        for (User u : usersList)
            if (u.getID() == id)
                return u;
        return null;
    }

    //Todo переделать метод... куча преобразований... вызов в 2 разных соединения с базой
    public void update(String editID, String newInfo, String newRole)
    {
        int idForUpd = recognizeId(editID);
        int newRoleInt = roleFromString.roleInt( newRole );
        for (User u : usersList)
        {
            if (u.getID() == idForUpd)
            {
                if (!u.getInfo().equals(newInfo))
                    usersList = usersListJpaDao.updateInfo(idForUpd, newInfo);
                if (u.getRole().getId()!=newRoleInt)
                    usersList = usersListJpaDao.updateRole(idForUpd, newRoleInt);
            }
        }
    }

    /**
     * Перед удалением пользователя, обязательно проверяем, что пользователь реально существует в списке;
     */

    public void delete(String strId)
    {
        int id = recognizeId(strId);
        User user = getUserById(id);
        if ( user != null )
            usersList = usersListJpaDao.delete(id);
    }

    private int recognizeId(String strId)
    {
        if ((strId != null)&&(!strId.equals("")))
            return intFromString.recognize(strId);
        return 0;
    }

}