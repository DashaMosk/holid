package com.epam.services;

import java.util.List;

import com.epam.entity.Users;

public interface UsersService {
	public void save(Users user);
	public void edit(Users user);
	public Users findById(int id);
	public List<Users> getAllUsers();
	public Users authorize(String login, String password);
}
