package com.example.Genesis.Project.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "newgenesis")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column(nullable = false, unique = true, length = 12)
    private String personID;

    @Column(nullable = false, unique = true)
    private String uuid;

    public User() {}

    public User(Long id, String name, String surname, String personID) {
        this.id = id;
        this.personID = personID;
        this.uuid = UUID.randomUUID().toString();
        this.name = name;
        this.surname = surname;
    }

    public String getPersonID() {
        return personID;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public void setId(Long id) {
    }
}