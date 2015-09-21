package com.bionic.edu;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class UserCardView {
	private int idUser;
	private String login;
	private String rights;
	private Timestamp regDate;
	private Timestamp blockDate;
	
	
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
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	
	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	public void setBlockDate(Timestamp blockDate) {
		this.blockDate = blockDate;
	}
	public Timestamp getBlockDate() {
		return blockDate;
	}
	
	public String getRight(int num){
		for (Rights r: Rights.values()) {
			if(r.getNum() == num) {
				return r.toString();
			}
		}
		return "";
	}

	public String showUserCard(Users user) {
		setIdUser(user.getIdUser());
		setLogin(user.getLogin());
		setRights(getRight(user.getRights()));
		setRegDate(user.getRegDate());
		setBlockDate(user.getBlockDate());
		return "usercard.xhtml?faces-redirect=true";
	}
	
	public String addEditUser() {
		System.out.println("ID of user = " +getIdUser());
		return "users.xhtml?faces-redirect=true";
	}
	
	public String cancel() {
		System.out.println("ID of user = " +getIdUser());
		return "users.xhtml?faces-redirect=true";
	}
	
	public String passwordReset() {
		System.out.println("ID of user = " +getIdUser());
		return "usercard.xhtml?faces-redirect=true";
	}
	
	public List<Rights> getListRights() {
		return Arrays.asList(Rights.values());
	}

}
