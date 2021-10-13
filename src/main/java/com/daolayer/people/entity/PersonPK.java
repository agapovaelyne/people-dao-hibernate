package com.daolayer.people.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonPK implements Serializable {

    @Id
    private String name;
    @Id
    private String surname;
    @Id
    private int age;
}
