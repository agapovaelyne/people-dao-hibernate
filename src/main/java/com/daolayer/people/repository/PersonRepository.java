package com.daolayer.people.repository;

import com.daolayer.people.entity.Person;
import com.daolayer.people.entity.PersonPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, PersonPK> {

    @Query("select p from Person p where p.cityOfLiving = :city")
    List<Person> findByCityOfLiving(@Param("city") String city);

    @Query(value = "select * from person p where p.age < ?1 order by p.age", nativeQuery = true)
    List<Person> findByAgeLessThan(int age);

    @Query("select p from Person p where p.name = :name and p.surname = :surname")
    Optional<List<Person>> findByNameAndSurname(@Param("name") String name,@Param("surname") String surname);
}
