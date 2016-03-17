package com.epam;

import com.epam.entity.Users;
import com.epam.services.PasswordService;
import com.epam.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Named
@ViewScoped
public class UserCardView {
	private int idUser;
	private String login;
	private int rights;
	private String password;
	private Timestamp regDate;
	private Timestamp blockDate;
	
	@Autowired
	UsersService usersService;

	@Autowired
	private PasswordService passService;

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
	public int getRights() {
		return rights;
	}
	public void setRights(int rights) {
		this.rights = rights;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	private void clearRecords() {
		setIdUser(0);
		setLogin(null);
		setPassword(null);
		setRights(0);
		setRegDate(null);
		setBlockDate(null);		
	}	
	
	private Users createUser() {
		Users user = new Users();
		user.setLogin(login);
		user.setPassword(passwordEncrypt("123"));
		user.setRights(rights);
		user.setRegDate(Timestamp.valueOf(LocalDateTime.now()));
		user.setBlockDate(null);
		return user;		
	}
	
	private Users getUser() {
		Users user = new Users();
		user.setIdUser(idUser);
		user.setLogin(login);
		user.setPassword(password);
		user.setRights(rights);
		user.setRegDate(regDate);
		user.setBlockDate(blockDate);
		return user;		
	}
	
	public String getRight(String right){
		int num = Integer.valueOf(right);
		for (Rights r: Rights.values()) {
			if(r.getNum() == num) {
				return r.toString();
			}
		}
		return "";
	}

	public String showUserCard(Users user) {
		clearRecords();
		setIdUser(user.getIdUser());
		setLogin(user.getLogin());
		setPassword(user.getPassword());
		setRights(user.getRights());
		setRegDate(user.getRegDate());
		setBlockDate(user.getBlockDate());
		return "usercard.xhtml?faces-redirect=true";
	}
	
	public String showNewUserCard() {
		clearRecords();
		return "usercard.xhtml?faces-redirect=true";
	}

	public String addEditUser() {
		if (idUser == 0) {
			usersService.save(createUser());
		} else {
			usersService.edit(getUser());
		}
		return "users.xhtml?faces-redirect=true";
	}
	
	public String cancel() {
		clearRecords();
		return "users.xhtml?faces-redirect=true";
	}
	
	public String passwordReset() {
		if (idUser != 0) {
			password = passwordEncrypt("123");
			usersService.edit(getUser());
		}
		return "usercard.xhtml?faces-redirect=true";
	}
	
	public String blockUnblock(Users user) {
		if (user.getBlockDate() != null) {
			user.setBlockDate(null);
		} else {
			user.setBlockDate(Timestamp.valueOf(LocalDateTime.now()));
		}		
		usersService.edit(user);
		return "users.xhtml?faces-redirect=true";
	}
	
	public List<Rights> getListRights() {
		return Arrays.asList(Rights.values());
	}
	
	public String passwordEncrypt(String password) {
		String encryptedPassword = passService.encrypt(password);
		return encryptedPassword;
	}


}
