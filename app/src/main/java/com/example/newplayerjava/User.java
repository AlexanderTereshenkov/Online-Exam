package com.example.newplayerjava;

public class User {
    public String name, email, password;
    public Boolean isTeacher;
    public User(){

    }
    public User(String name, String email, String password, Boolean isTeacher){
        this.name = name;
        this.email = email;
        this.password = password;
        this.isTeacher = isTeacher;
    }

}
