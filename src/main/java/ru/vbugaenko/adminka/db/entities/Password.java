package ru.innopolis.stc9.saturn.db.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

@XmlRootElement
@Entity
public class Password
{
    @Id
    @SequenceGenerator(name = "passSeq", sequenceName = "PASS_SEQUENCE", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passSeq")
    private int id;
    @OneToOne
    private User user;
    private String passwordhash;

    public Password() { }

    public Password(User user, String passwordhash)
    {
        this.user = user;
        this.passwordhash = passwordhash;
    }

    public Password(String newPassword)
    {
        passwordhash = new BCryptPasswordEncoder().encode(newPassword);
    }

    public int getId()              { return id;            }
    public User getUser()           { return user;          }
    public String getPasswordhash() { return passwordhash;  }

    public void setId(int id)                           { this.id = id;                     }
    public void setUser(User user)                      { this.user = user;                 }
    public void setPasswordhash(String passwordhash)    { this.passwordhash = passwordhash; }

    public boolean checkPass(String passwordhash)
    {
        if (this.passwordhash.equals(passwordhash))
            return true;
        return false;
    }

}
