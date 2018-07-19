package ru.innopolis.stc9.saturn.db.entities;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;


/**
 * @author Victor Bugaenko
 * @since 01.07.2018
 */

@XmlRootElement
@Entity
@Table(name="history")
public class History
{
    @Id
    @SequenceGenerator(name = "historySeq", sequenceName = "HISTORY_SEQUENCE", allocationSize = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "historySeq")
    int id;
    @ManyToOne(optional=false, cascade= CascadeType.ALL, fetch = FetchType.EAGER)
    User user;
    Date whenHappen;
    String whatHappen;

    public History() { }

    public History(User user, String whatHappen)
    {
        this.user = user;
        this.whatHappen = whatHappen;
        this.whenHappen = new Date();
    }

    public int        getId()   { return id;            }
    public User     getUser()   { return user;          }
    public Date     getWhen()   { return whenHappen;    }
    public String   getWhat()   { return whatHappen;    }

    public void setId(int id)               { this.id = id;     }
    public void setUser(User user)          { this.user = user; }
    public void setWhen(Date whenHappen)    { this.whenHappen = whenHappen; }
    public void setWhat(String whatHappen)  { this.whatHappen = whatHappen; }

    @Override
    public String toString() {
        return whenHappen + " " + whatHappen;
    }
}
