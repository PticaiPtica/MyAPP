package ru.academy.myapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Subjects")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name", nullable = false, length = 100, unique = true)
    private String name;
}
