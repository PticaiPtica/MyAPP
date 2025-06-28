package ru.academy.myapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name", nullable = false, length = 10, unique = true)
    private String name;
    @Column(name = "Year", nullable = false)
    private int year;
}
