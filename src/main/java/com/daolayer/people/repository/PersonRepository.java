package com.daolayer.people.repository;

import com.daolayer.people.entity.Person;
import com.daolayer.people.entity.PersonPK;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonPK> {

    List<Person> findByCityOfLiving(String city);

    List<Person> findByAgeLessThan(int age, Sort sort);

    Optional<List<Person>> findByNameAndSurname(String name, String surname);
}
