package com.example.firsebaseproject;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserDetail")
public class UserDetail {

    @PrimaryKey (autoGenerate = true)
    public int id;

    public String email;
    public int countOfHappy;
    public int CountOfSimple;
    public int CountOfSad;

    public UserDetail() {
    }

    public UserDetail(String email, int countOfHappy, int countOfSimple, int countOfSad) {
        this.email = email;
        this.countOfHappy = countOfHappy;
        CountOfSimple = countOfSimple;
        CountOfSad = countOfSad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCountOfHappy() {
        return countOfHappy;
    }

    public void setCountOfHappy(int countOfHappy) {
        this.countOfHappy = countOfHappy;
    }

    public int getCountOfSimple() {
        return CountOfSimple;
    }

    public void setCountOfSimple(int countOfSimple) {
        CountOfSimple = countOfSimple;
    }

    public int getCountOfSad() {
        return CountOfSad;
    }

    public void setCountOfSad(int countOfSad) {
        CountOfSad = countOfSad;
    }
}
