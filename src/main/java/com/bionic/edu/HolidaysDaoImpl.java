package com.bionic.edu;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

@Repository
public class HolidaysDaoImpl implements HolidaysDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public void save(Holidays holiday) {
		em.persist(holiday);
	}

	@Override
	public void edit(Holidays holiday) {
		em.merge(holiday);

	}

	@Override
	public Holidays findById(int id) {
		Holidays holiday = em.find(Holidays.class,id);
		return holiday;
	}

	@Override
	public List<Holidays> getAll() {
		TypedQuery<Holidays> queryH = em.createQuery("SELECT h FROM Holidays h ",
				Holidays.class);
		List<Holidays> hol = null;
		hol = queryH.getResultList();
		return hol;

	}

	@Override
	public void delete(Holidays holiday) {
		Holidays holid = em.find(Holidays.class, holiday.getId());
		em.remove(holid);		
	}

	@Override
	public Holidays findByUserDate(String user, Date date) {
		TypedQuery<Holidays> queryH = em.createQuery("SELECT h FROM Holidays h WHERE h.hUser = :huser AND h.hDate = :hdate", Holidays.class);
		queryH.setParameter("huser", user);
		queryH.setParameter("hdate", date);
		Holidays hol = queryH.getSingleResult();	 
		return hol;
	}

}
