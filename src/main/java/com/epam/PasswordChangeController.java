package com.epam;

import com.epam.entity.Users;
import com.epam.services.PasswordService;
import com.epam.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@Component
@SessionScoped
public class PasswordChangeController {
	
	@Autowired
	private LoginController loginController;
	
	@Autowired
	private UsersService userServise;
	
	@Autowired
	private PasswordService passwordService;
	
	String oldPassword;
	String newPassword;	

	
	public PasswordChangeController() {
	}	
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String savePassword() {
		Users user = loginController.getUser();
		FacesMessage message;
		if (user != null) {
			Users autUser = userServise.authorize(user.getLogin(), passwordService.encrypt(oldPassword));
			if (autUser != null) {
				user.setPassword(passwordService.encrypt(newPassword));
				userServise.edit(user);
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "INFO. The password was updated successfully", "The password was updated" );   
				FacesContext.getCurrentInstance().addMessage(null, message);
				return "schedule.xhtml?faces-redirect=true";				
			} else {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR. Current password is wrong", "Current password is wrong" );   
				FacesContext.getCurrentInstance().addMessage(null, message);
				return null;				
			}
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR. Can't update the password", "The password wasn't updated" );   
			FacesContext.getCurrentInstance().addMessage(null, message);
			return null;
		}
	}
}
