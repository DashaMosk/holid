package com.bionic.edu;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class LoginController {
	@Autowired
	private PasswordService passwdService;

	@Autowired
	private UsersService usersService;

	private Users user;

	private String username;
	private String password;

	public LoginController() {
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return user != null;
	}
	
	public boolean isAdmin() {
		if (isLoggedIn()) {
			return (user.getRights() == 1);
		}
		else {
			return false;
		}
	}

	public String login() throws IOException {
		FacesMessage message;
		user = usersService.authorize(username, passwdService.encrypt(password));
		if (user == null) {
	        message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. The login or password is wrong", "The login or password is wrong" );        
	        addMessage(message);
	        return null;
			//return redirectTo("loginPage.xhtml");
		}

		if (user.getBlockDate() != null) {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error. The user is bloked", "The user is bloked" );        
	        addMessage(message);
	        return null;
			//return redirectTo("loginPage.xhtml");
		}

		if (user.getRights() == 1) {
			user.setLoggedIn(true);
			return redirectTo("/admin/users.xhtml");
		} else if (user.getRights() == 2) {
			user.setLoggedIn(true);
			return redirectTo("schedule.xhtml");
		} else {
			return redirectTo("loginPage.xhtml");
		}
	}
	
	public String signIn() {
		username = null;
		password = null;
		return redirectTo("loginPage.xhtml");
	}

	public void logout() throws IOException {
		ExternalContext ec = FacesContext.getCurrentInstance()
				.getExternalContext();
		ec.invalidateSession();
		user =  null;
		ec.redirect(ec.getRequestContextPath() + "/schedule.xhtml");
	}
	
	public String currentUser() {
		if (user == null) {
			return "You are not logged in";
		} else {
			return "You are logged in as "+ user.getLogin();
		}
	}

	private static String redirectTo(String url) {
		return url + "?faces-redirect=true";
	}
	
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
