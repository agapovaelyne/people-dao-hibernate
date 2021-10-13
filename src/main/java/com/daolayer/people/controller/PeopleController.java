package com.daolayer.people.controller;

import com.daolayer.people.entity.Person;
import com.daolayer.people.service.PeopleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/persons")
public class PeopleController {
    private PeopleService peopleService;

    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @ResponseBody
    @PostMapping()
    public String savePerson(@RequestBody Person person) throws JsonProcessingException {
        return peopleService.savePerson(person);
    }

    @ResponseBody
    @GetMapping("/by-city")
    public String getPersonsByCity(@RequestParam String city) throws JsonProcessingException {
        return peopleService.getPersonsByCity(city);
    }

    @ResponseBody
    @GetMapping("/by-age")
    public String getPersonsByAgeLessThan(@RequestParam int younger) throws JsonProcessingException {
        return peopleService.getPersonsByAgeLessThan(younger);
    }

    @ResponseBody
    @GetMapping("/by-name-and-surname")
    public String getPersonByNameAndSurname(@RequestParam String name, @RequestParam String surname) throws JsonProcessingException {
        return peopleService.getPersonByNameAndSurname(name, surname);
    }

    @ResponseBody
    @GetMapping("/by-id")
    public String getById(@RequestParam String name, @RequestParam String surname, @RequestParam int age) throws JsonProcessingException {
        return peopleService.getById(name, surname, age);
    }

    @ResponseBody
    @DeleteMapping()
    public String removeById(@RequestParam String name, @RequestParam String surname, @RequestParam int age) throws JsonProcessingException {
        return peopleService.removeById(name, surname, age);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<String> handleRunTimeExc(EntityNotFoundException exc) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).contentType(MediaType.APPLICATION_JSON).body(exc.getLocalizedMessage());
    }
}
