package com.epam.entity;

import javax.persistence.*;
import java.sql.Timestamp;


@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idUser;
	private String login;
	private String password;
	private int rights;
	private Timestamp regDate;
	private Timestamp blockDate;

	@Transient
	private boolean loggedIn;

	@Transient
	public boolean isLoggedIn() {
		return loggedIn;
	}

	@Transient
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Users() {
	}
		
	public int getIdUser() {
		return idUser;
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Timestamp getBlockDate() {
		return blockDate;
	}

	public void setBlockDate(Timestamp blockDate) {
		this.blockDate = blockDate;
	}

}
