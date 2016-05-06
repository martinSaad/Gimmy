package com.example.martinsaad.hackidc;

import com.google.gson.annotations.SerializedName;

/**
 * Created by reabar on 6.5.2016.
 */
public class User {
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("last_name")
    String lastName;
    String email;
    String password;
    String gender;
    @SerializedName("birthday")
    String birthDate;

    public User(String firstName, String lastName, String email, String password, String gender, String birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
