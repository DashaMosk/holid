package com.epam.dao;

import com.epam.entity.Users;

import java.util.List;

public interface UsersDao {
	public void save(Users user);
	public void edit(Users user);
	public Users findById(int id);
	public List<Users> getAllUsers();
	public Users authorize(String login, String password);
}
