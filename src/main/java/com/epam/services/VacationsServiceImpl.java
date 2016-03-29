package com.epam.services;

import com.epam.dao.VacationsDao;
import com.epam.entity.Vacations;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named
public class VacationsServiceImpl implements VacationsService {

    @Inject
    VacationsDao vacationsDao;

    @Override
    @Transactional
    public long save(Vacations holiday) {
        return vacationsDao.save(holiday);
    }

    @Override
    @Transactional
    public void edit(Vacations holiday) {
        vacationsDao.edit(holiday);
    }

    @Override
    @Transactional
    public void delete(long id) {
        vacationsDao.delete(id);
    }

    @Override
    public List<Vacations> getAll() {
        return vacationsDao.getAll();
    }
}
