package com.bah.domain.model;

import java.util.UUID;

//@Entity
public class Client {

    private String name;

    private String number;

    public Client(String name) {
        this.name = name;
        this.number = UUID.randomUUID().toString();
    }

    Client() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
