package com.example.stockx_ray;

import android.widget.EditText;

public class User {
    int age;
    String name;
    String expertise;
    int frequency;
    String period;
    int maxtime;

    public User(int age, String name, String expertise, int frequency, String period, int maxtime) {
        this.age = age;
        this.name = name;
        this.expertise = expertise;
        this.frequency = frequency;
        this.period = period;
        this.maxtime = maxtime;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public int getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(int maxtime) {
        this.maxtime = maxtime;
    }
}
