package com.epam.services;

import com.epam.entity.Vacations;

import java.util.List;

/**
 * Created by Daria on 17.03.2016.
 */
public interface VacationsService {
    public void save(Vacations holiday);
    public void edit(Vacations holiday);
    public void delete(Vacations holiday);
    public List<Vacations> getAll();
}
