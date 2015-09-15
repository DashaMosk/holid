package com.bionic.edu;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;
@Named
public class UsersServiceImpl implements UsersService {

	@Inject
	UsersDao usersDao;

	@Transactional
	public void save(Users user) {
		usersDao.save(user);
	}

	@Transactional
	public void edit(Users user) {
		usersDao.edit(user);		
	}

	public Users findById(int id) {
		Users user = usersDao.findById(id);
		return user;
	}

	@Override
	public List<Users> getAllUsers() {
		List<Users> users = usersDao.getAllUsers();
		return users;
	}

}
