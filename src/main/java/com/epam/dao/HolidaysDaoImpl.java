package com.epam.dao;

import com.epam.entity.Holidays;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Repository
public class HolidaysDaoImpl implements HolidaysDao {
	
	@PersistenceContext
	private EntityManager em;
	
	final static Logger logger = Logger.getLogger(HolidaysDaoImpl.class);

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
		Holidays hol =  null;
		try{
			hol = queryH.getSingleResult();	 
		}catch (javax.persistence.NoResultException e) {
			logger.info("There is no information in Holidays with date "+ date + ", user "+ user);
			//System.out.println("There is no information in Holidays with date "+ date + ", user "+ user);
		}catch (javax.persistence.NonUniqueResultException e) {
			logger.info("There is not unique data in Holidays with date "+ date + ", user "+ user);
			//System.out.println("There is not unique data in Holidays with date "+ date + ", user "+ user);			
		}
		return hol;
	}

}
