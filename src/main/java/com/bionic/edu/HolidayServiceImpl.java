package com.bionic.edu;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;


@Named
public class HolidayServiceImpl implements HolidayService {
	
	@Inject
	HolidaysDao holidaysDao;

	@Transactional
	public void save(Holidays holiday) {
		holidaysDao.save(holiday);
	}

	@Transactional
	public void edit(Holidays holiday) {
		holidaysDao.edit(holiday);
	}

	public Holidays findById(int id) {
		Holidays holiday = holidaysDao.findById(id);
		return holiday;
	}

	@Override
	public List<Holidays> getAll() {
		List<Holidays> arrHol =  holidaysDao.getAll();
		return arrHol;
	}

	@Override
	@Transactional
	public void delete(Holidays holiday) {
		holidaysDao.delete(holiday);		
	}

}
