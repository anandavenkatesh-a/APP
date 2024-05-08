package com.example.auth;

public class User {
    String name;
    String address;
    String dose1;
    String dose2;
    String dose3;

    int pic;

    public User(String name, String address, String dose1, String dose2, String dose3, int pic) {
        this.name = name;
        this.address = address;
        this.dose1 = dose1;
        this.dose2 = dose2;
        this.dose3 = dose3;
        this.pic = pic;
    }
}
