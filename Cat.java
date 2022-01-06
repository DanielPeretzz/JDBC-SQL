package com.homework.daniel.model;

import lombok.*;

@Data // Getters and setters
//@AllArgsConstructor // A constructor with all properties
@NoArgsConstructor // Empty constructor
@ToString // implements toString
@EqualsAndHashCode // Implements equals and hashcode methods
public class Cat {
    private  Long id = null;
    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
