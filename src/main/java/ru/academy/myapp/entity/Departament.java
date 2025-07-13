package ru.academy.myapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Departaments")
public class Departament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name = "Financing", nullable = false, length = 100)
    private Integer financing;

    @Column(name = "Name",nullable = false)
    private String name;

}
