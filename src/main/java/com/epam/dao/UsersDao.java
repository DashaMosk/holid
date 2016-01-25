package com.epam.dao;

import java.util.List;

import com.epam.entity.Users;

public interface UsersDao {
	public void save(Users user);
	public void edit(Users user);
	public Users findById(int id);
	public List<Users> getAllUsers();
	public Users authorize(String login, String password);
}
