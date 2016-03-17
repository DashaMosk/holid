package com.epam.dao;

import com.epam.entity.Users;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UsersDaoImpl implements UsersDao {
	@PersistenceContext
	private EntityManager em;

	public void save(Users user) {
		em.persist(user);		
	}

	public void edit(Users user) {
		em.merge(user);		
	}

	public Users findById(int id) {
		Users user = em.find(Users.class, id);
		return user;
	}

	@Override
	public List<Users> getAllUsers() {
		TypedQuery<Users> queryU = em.createQuery("SELECT u FROM Users u ",
				Users.class);
		List<Users> users = null;
		users = queryU.getResultList();
		return users;		
	}
	
	@Override
	public Users authorize(String login, String password) { // authorization
		// for staff
		TypedQuery<Users> query = em.createQuery(
				"SELECT u FROM Users u WHERE u.login = ?1 AND u.password = ?2",
				Users.class);

		List<Users> u = null;
		query.setParameter(1, login);
		query.setParameter(2, password);
		u = query.getResultList();
		return CollectionUtils.isEmpty(u) ? null : u.get(0);
	}

}
