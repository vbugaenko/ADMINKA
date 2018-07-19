package ru.innopolis.stc9.saturn.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.innopolis.stc9.saturn.db.dao.*;
import ru.innopolis.stc9.saturn.db.entities.Role;

import java.util.ArrayList;
import java.util.List;

/**
 * refactored
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

@Service("customUserDetailsService")
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService
{
    @Autowired
    private UsersDAO usersdao;
    //Todo сделать нормальные @Autowired, для это заменить конфиг в дао на sessionфабрику
    private RolesDAOImpl rolesDAO = new RolesDAOImpl();
    private PasswordDAOImpl passDAO = new PasswordDAOImpl();

    /*
        public void setUsersdao()
        {
            this.usersdao = new UsersDAOImpl();
            this.rolesDAO = new RolesDAOImpl();
            this.passDAO = new PasswordDAOImpl();
        }
    */
    public UserDetails loadUserByUsername(String login)
            throws UsernameNotFoundException
    {

        System.out.println("Проверка подключения к базе");
        System.out.println(usersdao.get(1));

        System.out.println("Проверка по логину");
        //Todo: Удалить все транзитные sout после полной отладки
        ru.innopolis.stc9.saturn.db.entities.User domainUser = usersdao.getByLogin( login );
        System.out.println(">>>>>> Имя: "+domainUser.getName());
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        List<GrantedAuthority> authorities = new ArrayList();
        rolesDAO.check();
        System.out.println(">>>>>> RolID: " + domainUser.getRole().getId() );

        Role role = rolesDAO.get( domainUser.getRole().getId() );

        System.out.println(">>>>>> Rol: " + role.getName() );

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        System.out.println(">>>>>> Uid: " + domainUser.getID() );

        String passwordHash = passDAO.get( domainUser.getID() );

        System.out.println(">>>>>> Хеш: " + passwordHash );

        UserDetails userDetails = new User(
                domainUser.getLogin(),
                passwordHash,
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities
        );

        return userDetails;
    }

    public void setUsersdao(UsersDAOImpl usersdao) {
        this.usersdao = usersdao;
    }

    public UsersDAO getUsersdao() {
        return usersdao;
    }
}
