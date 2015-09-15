package com.bionic.edu;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
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

}
