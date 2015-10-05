package com.bionic.edu;

import javax.faces.bean.SessionScoped;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class PasswordChangeController {
	String oldPassword;
	String newPassword;	
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
	public PasswordChangeController() {
	}
	
	public String savePassword() {
		
		return null;
	}
	

}
