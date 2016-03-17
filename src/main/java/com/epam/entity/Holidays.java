package com.epam.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "holidays")
public class Holidays {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String hUser;
	@Temporal(value = TemporalType.DATE)
	private Date hDate;
	private int idUserSet;
	private Timestamp dateSet;

	public Holidays() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String gethUser() {
		return hUser;
	}

	public void sethUser(String hUser) {
		this.hUser = hUser;
	}

	public Date gethDate() {
		return hDate;
	}

	public void sethDate(Date hDate) {
		this.hDate = hDate;
	}

	public int getIdUserSet() {
		return idUserSet;
	}

	public void setIdUserSet(int idUserSet) {
		this.idUserSet = idUserSet;
	}

	public Timestamp getDateSet() {
		return dateSet;
	}

	public void setDateSet(Timestamp dateSet) {
		this.dateSet = dateSet;
	}


	
}
