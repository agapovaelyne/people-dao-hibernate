package com.daolayer.people.repository;

import com.daolayer.people.entity.Person;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class PeopleRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Person> getPersonsByCity(String city) {
        Query query = entityManager.createQuery("select p from Person p where p.city_of_living = :city", Person.class);
        query.setParameter("city", city);
        var result = query.getResultList();
        return result;
    }

}
