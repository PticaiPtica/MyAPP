package ru.academy.myapp.model;


import java.util.ArrayList;

public class FactApiResponse {
    public String fact;
    public int length;



    @Override
    public String toString() {
        return "FactApiResponse{" +
                "fact='" + fact + '\'' +
                ", length=" + length +
                '}';
    }
}
