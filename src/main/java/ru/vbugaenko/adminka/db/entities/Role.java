package ru.innopolis.stc9.saturn.db.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Victor Bugaenko
 * @since 11.07.2018
 */

@XmlRootElement
@Entity
@Table(name = "roles")
public class Role
{
    @Id
    @Column(name = "id")
    private int id;
    private String name;
    private String shortname;

    public Role() { }

    public Role(int id, String name, String shortname)
    {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
    }

    public int getId()           { return id;           }
    public String getName()      { return name;         }
    public String getShortname() { return shortname;    }

    public void setId(int id)                   { this.id = id;                 }
    public void setName(String name)            { this.name = name;             }
    public void setShortname(String shortname)  { this.shortname = shortname;   }

    @Override
    public String toString() {
        return "RolesEntity{" +
                "id=" + id +
                ", role='" + name + '\'' +
                ", shortname='" + shortname + '\'' +
                '}';
    }
}