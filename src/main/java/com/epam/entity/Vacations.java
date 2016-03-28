package com.epam.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Daria on 17.03.2016.
 */
@Entity
@Table(name = "vacations")
public class Vacations implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVacations;
    private String userName;
    @Temporal(value = TemporalType.DATE)
    private Date dateStart;
    @Temporal(value = TemporalType.DATE)
    private Date dateEnd;
    private Timestamp dateSet;
    private long idUserSet;

    public Vacations() {}

    public Vacations(String userName, Date dateStart, Date dateEnd, Timestamp dateSet, long idUserSet) {
        this.userName = userName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateSet = dateSet;
        this.idUserSet = idUserSet;
    }

    public Vacations(long idVacations, String userName, Date dateStart, Date dateEnd, Timestamp dateSet, long idUserSet) {
        this.idVacations = idVacations;
        this.userName = userName;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.dateSet = dateSet;
        this.idUserSet = idUserSet;
    }

    public long getIdVacations() {
        return idVacations;
    }

    public void setIdVacations(long idVacations) {
        this.idVacations = idVacations;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Timestamp getDateSet() {
        return dateSet;
    }

    public void setDateSet(Timestamp dateSet) {
        this.dateSet = dateSet;
    }

    public long getIdUserSet() {
        return idUserSet;
    }

    public void setIdUserSet(long idUserSet) {
        this.idUserSet = idUserSet;
    }
}
