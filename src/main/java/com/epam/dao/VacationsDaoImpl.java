package com.epam.dao;

import com.epam.entity.Vacations;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Daria on 17.03.2016.
 */
@Repository
public class VacationsDaoImpl implements VacationsDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Vacations vacation) {
        em.persist(vacation);
    }

    @Override
    public void edit(Vacations vacation) {
        em.merge(vacation);
    }

    @Override
    public void delete(Vacations vacation) {
        Vacations vac = em.find(Vacations.class, vacation.getIdVacations());
        em.remove(vac);
    }

    @Override
    public List<Vacations> getAll() {
        TypedQuery<Vacations> queryU = em.createQuery("SELECT v FROM Vacations v ",
                Vacations.class);
        List<Vacations> vacations = queryU.getResultList();
        return vacations;
    }
}