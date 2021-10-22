package com.daolayer.people.controller;

import com.daolayer.people.entity.Person;
import com.daolayer.people.service.PeopleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/secure/persons")
public class PeopleSecuredController {

    private final PeopleService peopleService;

    public PeopleSecuredController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping("/by-city")
    @Secured("ROLE_READ")
    public String getPersonsByCity(@RequestParam String city) throws JsonProcessingException {
        return peopleService.getPersonsByCity(city);
    }

    @PostMapping()
    @RolesAllowed("ROLE_WRITE")
    public String savePerson(@RequestBody Person person) throws JsonProcessingException {
        return peopleService.savePerson(person);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ROLE_WRITE') or hasRole('ROLE_DELETE')")
    public String removeById(@RequestParam String name, @RequestParam String surname, @RequestParam int age) throws JsonProcessingException {
        return peopleService.removeById(name, surname, age);
    }

    @GetMapping("/by-id")
    @PostAuthorize("#name == authentication.principal.username")
    public String getById(@RequestParam String name, @RequestParam String surname, @RequestParam int age) throws JsonProcessingException {
        return peopleService.getById(name, surname, age);
    }
}
