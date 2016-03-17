package com.epam.services;

import com.epam.entity.Users;

import java.util.List;

public interface UsersService {
	public void save(Users user);
	public void edit(Users user);
	public Users findById(int id);
	public List<Users> getAllUsers();
	public Users authorize(String login, String password);
}
