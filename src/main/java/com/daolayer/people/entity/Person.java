package com.daolayer.people.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(PersonPK.class)
@Entity
public class Person {

    @Id
    private String name;
    @Id
    private String surname;
    @Id
    private int age;

    private String phone_number;

    private String city_of_living;
}
