package ru.academy.myapp.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String Name;
    @Column(nullable = false)
    private String Email;
    @Column(nullable = false)
    private String country;
    @Column(nullable = false)
    private int stars;
    @Column(nullable = false)
    private int year;

    public Account(String name, String email, String country, int stars, int year) {
        Name = name;
        Email = email;
        this.country = country;
        this.stars = stars;
        this.year = year;
    }

    public Account() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}


