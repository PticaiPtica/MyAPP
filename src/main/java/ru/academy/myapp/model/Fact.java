package ru.academy.myapp.model;

public class Fact {
    public int id;
    public String fact;
    public int length;

    public Fact(int id, String fact, int length) {
        this.id = id;
        this.fact = fact;
        this.length = length;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Fact{" +
                "fact='" + fact + '\'' +
                ", length=" + length +
                '}';
    }
}
