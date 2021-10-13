package com.daolayer.people.repository;

import com.daolayer.people.entity.Person;
import com.daolayer.people.entity.PersonPK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Repository
public class PeopleRepository {

    @Autowired
    private PersonRepository personRepository;

    public Person savePerson(Person person) {
        try {
            Person entity = personRepository.findById(new PersonPK(person.getName(), person.getSurname(), person.getAge())).get();
            if (person.getCityOfLiving() != null) {
                entity.setCityOfLiving(person.getCityOfLiving());
            }

            if (person.getPhoneNumber() != null) {
                entity.setPhoneNumber(person.getPhoneNumber());
            }
            return personRepository.save(entity);
        } catch (NoSuchElementException ex) {
            return personRepository.save(person);
        }
    }

    public List<Person> getPersonsByCity(String city) {
        return personRepository.findByCityOfLiving(city);
    }

    public List<Person> getPersonsByAgeLessThan(int age) {
        return personRepository.findByAgeLessThan(age, Sort.by("age"));
    }

    public Optional<List<Person>> getPersonByNameAndSurname(String name, String surname) {
        return personRepository.findByNameAndSurname(name, surname);
    }

    public Optional<Person> getById(PersonPK personPK) {
        try {
            Optional<Person> entity = personRepository.findById(personPK);
            return entity;
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }

    public Optional<Person> removeById(PersonPK personPK) {
        try {
            Optional<Person> entity = personRepository.findById(personPK);
            personRepository.delete(entity.get()); //in real life mark as deleted in db
            return entity;
        } catch (NoSuchElementException ex) {
            return Optional.empty();
        }
    }
}
