package ru.innopolis.stc9.saturn.db.entities;

import ru.innopolis.stc9.saturn.db.dao.RolesDAOImpl;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * base Entity.
 * @author Victor Bugaenko
 * @since 30.06.2018
 */

@XmlRootElement
@Entity
@Table(name="users")
public class User
{
    @Id
    @SequenceGenerator(name = "userSeq", sequenceName = "USER_SEQUENCE", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    private int      id;
    private String   login;
    /**
     * User не должен хранить хеш пароля у себя.
     */
    private int      password_id;
    @ManyToOne
    private Role     role;
    private Date     age;   //Todo: birthdayDate;
    private String   name;
    private String   info;
    private boolean  enabled;
    private String   city;
    private String   phone;
    private Date     regdate = new Date();
    private String   email;
    private String   fullAddress;
    private String   photo;
    private Date     lastActivity  = new Date();
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<History> history=new ArrayList();
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Specific specific;

    public User() { }

    public void setId(int id)                       { this.id = id;                     }
    public void setLogin(String login)              { this.login = login;               }
    public void setPassword_id(int password_id)     { this.password_id = password_id;   }
    public void setRole(Role role)                  { this.role = role;                 }
    public void setAge(Date age)                    { this.age = age;                   }
    public void setName(String name)                { this.name = name;                 }
    public void setInfo(String info)                { this.info = info;                 }
    public void setEnabled(boolean enabled)         { this.enabled = enabled;           }
    public void setCity(String city)                { this.city = city;                 }
    public void setFullAddress(String fullAddress)  { this.fullAddress = fullAddress;   }
    public void setPhone(String phone)              { this.phone = phone;               }
    public void setRegdate(Date regdate)            { this.regdate = regdate;           }
    public void setEmail(String email)              { this.email = email;               }
    public void setPhoto(String photo)              { this.photo = photo;               }
    public void setLastActivity(Date lastActivity)  { this.lastActivity = lastActivity; }
    public void setHistory(List<History> history)   { this.history = history;           }
    public void setSpecific(Specific specific)      { this.specific = specific;         }

    public int      getID()               { return this.id;               }
    public String   getLogin()            { return this.login;            }
    public int      getPassword_id()      { return password_id;           }
    public Role     getRole()             { return this.role;             }
    public Date     getAge()              { return this.age;              }
    public String   getName()             { return this.name;             }
    public String   getInfo()             { return this.info;             }
    public boolean  getEnabled()          { return this.enabled;          }
    public String   getCity()             { return this.city;             }
    public String   getFullAddress()      { return this.fullAddress;      }
    public String   getPhone()            { return this.phone;            }
    public Date     getRegdate()          { return this.regdate;          }
    public String   getEmail()            { return this.email;            }
    public String   getPhoto()            { return photo;                 }
    public Date     getLastActivity()     { return this.lastActivity;     }
    public List<History> getHistory()     { return history;               }
    public Specific getSpecific()         { return specific;              }

    /**
     * Автоматом выводит сколько пользователю полных лет.
     */
    public int getAgeYears()
    {
        if ((age != null))
            return Period.between(convertToLocalDate(age), convertToLocalDate(new Date())).getYears();
        else
            return 0;
    }

    public void setRole(String roleName)
    {
        this.role = new RolesDAOImpl().getByName(roleName);
    }


/*
    /**
     * Автоматом выводит сколько дней пользователь не проявлял активности.
     *
    public int getDaysFromLastActivity()
    {
        if ((age != null))
            return Period.between(convertToLocalDate(lastActivity), convertToLocalDate(new Date())).getDays();
        else
            return 0;
    }
*/

    private LocalDate convertToLocalDate(Date dateToConvert)
    {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public static final Comparator<User> COMPARE_BY_SURNAME_toLESS = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.name.compareTo(other.name);
        }
    };

    public static final Comparator<User> COMPARE_BY_SURNAME_toUPPER = new Comparator<User>() {
        public int compare(User one, User other) {
            return other.name.compareTo(one.name);
        }
    };

    public static final Comparator<User> COMPARE_BY_AGE_toLESS = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.age.compareTo(other.age);
        }
    };

    public static final Comparator<User> COMPARE_BY_AGE_toUPPER = new Comparator<User>() {
        public int compare(User one, User other) {
            return other.age.compareTo(one.age);
        }
    };

    public static final Comparator<User> COMPARE_BY_ADDRESS_tL = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.city.compareTo(other.city);
        }
    };

    public static final Comparator<User> COMPARE_BY_ADDRESS_tU = new Comparator<User>() {
        public int compare(User one, User other) {
            return other.city.compareTo(one.city);
        }
    };

    public static final Comparator<User> COMPARE_BY_RegDATE_tL = new Comparator<User>() {
        public int compare(User one, User other) {
            return one.regdate.compareTo(other.regdate);
        }
    };

    public static final Comparator<User> COMPARE_BY_RegDATE_tU = new Comparator<User>() {
        public int compare(User one, User other) {
            return other.regdate.compareTo(one.regdate);
        }
    };

    public void addHistory(History h)
    {
        history.add(h);
    }

    public void addHistory(String whatHappen)
    {
        history.add(new History(this, whatHappen));
    }

    @Override
    public String toString()
    {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", role_id=" + role +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", enabled=" + enabled +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", regdate=" + regdate +
                ", email='" + email + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                ", photo='" + photo + '\'' +
                ", lastActivity=" + lastActivity +
                ", history=" + history +
                '}';
    }

    /**
     * Очень удобно ставить новый пароль user.setPassword("newPass").
     *
     * Метод возвращает ссылку на объект Password, но этот объект еще не сохранен в базе,
     * это нужно сделать принудительно в той же транзакции, что и сохранение user'а.
     * Таким образом сокращается количество транзакций к базе.
     */
    public Password setPassword( String newPassword )
    {
        return new Password( newPassword );
    }

    public void setAge(int i)
    {
        this.age = new Date(31536000000l*i);
    }
}
