package ru.academy.myapp.entity;


import jakarta.persistence.*;

@Entity
@Table (name = "Curators")
public class Curator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column (name = "Name", nullable = false)
    private String name;

    @Column (name = "Surname",nullable = false)
    private String surname;

}
