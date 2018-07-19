package ru.innopolis.stc9.saturn.service;

import java.util.Date;

/**
 * Refactored
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

public interface RegistrationService {

    String pwdHashWithSalt(String password);

    String addUser(
                        Integer userRole,
                        String userLogin,
                        String userName,
                        Date userAge,
                        String userCity,
                        String userAdress,
                        String userPhone,
                        String userEmail,
                        String userPassword,
                        String userPasswordRepeat,
                        String userPhoto
    );

}
