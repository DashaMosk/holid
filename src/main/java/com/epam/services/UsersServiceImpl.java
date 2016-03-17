package com.epam.services;

import com.epam.dao.UsersDao;
import com.epam.entity.Users;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

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
	
	@Override
	public Users authorize(String login, String password) {
		Users user = usersDao.authorize(login, password);
		return user;
	}


}
