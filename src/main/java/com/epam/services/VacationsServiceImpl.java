package com.epam.services;

import com.epam.dao.VacationsDao;
import com.epam.entity.Vacations;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by Daria on 17.03.2016.
 */
@Named
public class VacationsServiceImpl implements VacationsService {

    @Inject
    VacationsDao vacationsDao;

    @Override
    public long save(Vacations holiday) {
        return vacationsDao.save(holiday);
    }

    @Override
    public void edit(Vacations holiday) {
        vacationsDao.edit(holiday);
    }

    @Override
    public void delete(Vacations holiday) {
        vacationsDao.delete(holiday);
    }

    @Override
    public List<Vacations> getAll() {
        return vacationsDao.getAll();
    }
}
