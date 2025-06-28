package ru.academy.myapp.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Lectures")
public class Lecture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "LectureRoom", nullable = false)
    private String lectureRoom;

}
