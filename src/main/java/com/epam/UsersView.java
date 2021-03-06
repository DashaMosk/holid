package com.epam;

import com.epam.entity.Users;
import com.epam.services.UsersService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.util.List;

@Component
@ViewScoped
public class UsersView {
	private List<Users> users;
	
	@Inject
	private UsersService uservice;

    @PostConstruct
    public void init() {
    	users = uservice.getAllUsers();
    }
     
    public List<Users> getUsers() {
        return users;
    }
    
	public String showUsers() {
		return "/admin/users.xhtml?faces-redirect=true";
	}
	
	public String showPasswordChange() {
		return "/changePassword.xhtml?faces-redirect=true";
	}

}
