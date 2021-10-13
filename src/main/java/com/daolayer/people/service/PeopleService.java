package com.daolayer.people.service;

import com.daolayer.people.entity.Person;
import com.daolayer.people.entity.PersonPK;
import com.daolayer.people.repository.PeopleRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class PeopleService {
    private PeopleRepository peopleRepository;
    public ObjectMapper objectMapper = new ObjectMapper();

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public String savePerson(Person person) throws JsonProcessingException {
        return objectMapper.writeValueAsString(peopleRepository.savePerson(person));
    }

    public String getPersonsByCity(String city) throws JsonProcessingException {
        List<Person> entities = peopleRepository.getPersonsByCity(city);
        return objectMapper.writeValueAsString(entities);
    }

    public String getPersonsByAgeLessThan(int age) throws JsonProcessingException {
        List<Person> entities = peopleRepository.getPersonsByAgeLessThan(age);
        return objectMapper.writeValueAsString(entities);
    }

    public String getPersonByNameAndSurname(String name, String surname) throws JsonProcessingException {
        List<Person> entities = peopleRepository.getPersonByNameAndSurname(name, surname).orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return objectMapper.writeValueAsString(entities);
    }

    public String getById(String name, String surname, int age) throws JsonProcessingException {
        Person entity = peopleRepository.getById(new PersonPK(name, surname, age)).orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return objectMapper.writeValueAsString(entity);
    }

    public String removeById(String name, String surname, int age) throws JsonProcessingException {
        Person entity = peopleRepository.removeById(new PersonPK(name, surname, age)).orElseThrow(() -> new EntityNotFoundException("Person not found"));
        return objectMapper.writeValueAsString(entity);
    }
}
