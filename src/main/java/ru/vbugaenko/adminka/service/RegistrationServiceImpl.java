package ru.vbugaenko.adminka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vbugaenko.adminka.db.dao.RegistrationDAO;
import ru.vbugaenko.adminka.db.dao.RolesDAO;
import ru.vbugaenko.adminka.db.dao.UsersDAO;
import ru.vbugaenko.adminka.db.entities.User;
import ru.vbugaenko.adminka.db.security.FormsCheckImpl;


import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Refactored
 * @author Victor Bugaenko
 * @since 12.07.2018
 */

@Service
@Transactional
public class RegistrationServiceImpl implements RegistrationService {

    private final FormsCheckImpl formsCheck;
    private final RegistrationDAO regDAO;
    private final UsersDAO usersDAO;
    private final RolesDAO rolesDAO;


    @Autowired
    public RegistrationServiceImpl(FormsCheckImpl formsCheck,
                                   RegistrationDAO regDAO,
                                   UsersDAO usersDAO,
                                   RolesDAO rolesDAO) {
        this.formsCheck = formsCheck;
        this.regDAO = regDAO;
        this.usersDAO = usersDAO;
        this.rolesDAO = rolesDAO;
    }

    //@Autowired
    //private BCryptPasswordEncoder bcryptEncoder;

    private LocalDate convertToLocalDate(Date dateToConvert)
    {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    @Override
    public String addUser(
            Integer userRole, String userLogin, String userName, Date userBirthdate, String userCity,
            String userAdress, String userPhone, String userEmail, String userPassword,
            String userPasswordRepeat, String userPhoto)
    {

        String paramsChecking ="";

        if (userRole <= 0 && userRole > 4)
            paramsChecking += "error: Тип пользователя выбран неверно!</br>";

        int age = Period.between(convertToLocalDate(userBirthdate), convertToLocalDate(new Date())).getYears();

        if (age<18)
            paramsChecking += "error: Сервис доступен только для совершеннолетних!</br>";

        if (age>140)
            paramsChecking += "error: Вероятно указан не верный год рождения!</br>";

        if (!formsCheck.checkEngLettersAndDigits(userLogin))
            paramsChecking += "error: Только латинские буквы и цифры в логине!</br>";

        if (!formsCheck.checkName(userName))
            paramsChecking += "error: Запрещенные символы в ФИО!</br>";

        if (!formsCheck.checkCity(userCity))
            paramsChecking += "error: Город введен неверно!</br>";

        if (!formsCheck.checkAdress(userAdress))
            paramsChecking += "error: Адрес введен неверно!</br>";

        if (!formsCheck.checkPhone(userPhone))
            paramsChecking += "error: Телефон введен неверно!</br>";

        if (!formsCheck.checkEmail(userEmail))
            paramsChecking += "error: Email введен неверно!</br>";

        if (!userPassword.equals(userPasswordRepeat))
            paramsChecking += "error: Пароли не совпадают!</br>";

        //Todo: отдельная транзакция на проверку логина... перенести в единую при регистрации!
        if (usersDAO.checkLogin(userLogin))
            paramsChecking += "error: Такой логин уже занят!</br>";

        if (!formsCheck.checkPhoto(userPhoto))
            paramsChecking += "error: Адрес фото некорректный!</br>";

        if (paramsChecking.equals("")) {
            User user = new User();
            user.setRole             ( rolesDAO.get(userRole) ); //Todo: потом разобраться почему Int приходит
            user.setLogin            ( userLogin        );
            user.setName             ( userName         );
            user.setBirthdate        ( userBirthdate    );
            user.setCity             ( userCity         );
            user.setFullAddress      ( userAdress       );
            user.setPhone            ( userPhone        );
            user.setEmail            ( userEmail        );
            user.setPhoto            ( userPhoto        );
            user.setRegdate          ( new Date()       );
            user.setLastActivity     ( new Date()       );

            regDAO.add( user, pwdHashWithSalt(userPassword) ); //Todo: а что, если вернулось false?
        }
        return paramsChecking;
    }

    //TODO: вернуть шифрование
    @Override
    public String pwdHashWithSalt(String password)
    {
        return password; //bcryptEncoder.encode(password);
    }
}