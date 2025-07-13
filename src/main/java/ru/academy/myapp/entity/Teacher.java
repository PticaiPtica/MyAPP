package ru.academy.myapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Name", nullable = false)
    private String name;
    @Column(name = "Salary", nullable = false,columnDefinition = "money")
    private double salary;
    @Column(name = "Surname", nullable = false)
    private String surname;
}
