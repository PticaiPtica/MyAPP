package ru.academy.myapp.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Faculties")
public class Facultet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "Financing", nullable = false, columnDefinition = "money")
    private Integer financing = 0;
    @Column(name = "Name", nullable = false, length = 100, unique = true)
    private String Name;

}
