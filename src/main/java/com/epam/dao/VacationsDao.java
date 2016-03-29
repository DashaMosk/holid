package com.epam.dao;

import com.epam.entity.Vacations;

import java.util.List;

/**
 * Created by Daria on 17.03.2016.
 */
public interface VacationsDao {
    public long save(Vacations holiday);
    public void edit(Vacations holiday);
    public void delete(long id);
    public List<Vacations> getAll();
}
