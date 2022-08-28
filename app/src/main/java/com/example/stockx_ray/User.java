package com.example.stockx_ray;

import java.sql.Array;
import java.util.ArrayList;

public class User {
    String age;
    String email;
    String expertise;
    String term;
    String timelimit;
    ArrayList<Criteria> criterias;

    public User(String age, String email, String expertise, String term, String timelimit) {
        this.age = age;
        this.email = email;
        this.expertise = expertise;
        this.term = term;
        this.timelimit = timelimit;
        criterias = new ArrayList<>();
        Criteria two = new Criteria("Criteria 2");
        criterias.add(two);
        Criteria three = new Criteria("Criteria 3");
        criterias.add(three);
        Criteria four = new Criteria("Criteria 4");
        criterias.add(four);
        Criteria five = new Criteria("Criteria 5");
        criterias.add(five);
    }

    public User() { }

    public void addCriteria(Criteria crit) {
        criterias.add(0, crit);
    }

    public ArrayList<Criteria> getCriterias() {
        return criterias;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String email) {
        this.email = email;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTimelimit() {
        return timelimit;
    }

    public void setTimelimit(String timelimit) {
        this.timelimit = timelimit;
    }
}
