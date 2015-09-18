package com.bionic.edu;

import java.util.List;

public interface UsersDao {
	public void save(Users user);
	public void edit(Users user);
	public Users findById(int id);
	public List<Users> getAllUsers();
}