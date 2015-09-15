package com.bionic.edu;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

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
}
