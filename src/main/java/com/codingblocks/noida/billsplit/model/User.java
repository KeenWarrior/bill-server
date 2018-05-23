package com.codingblocks.noida.billsplit.model;


import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    public String id;
    public String name;
    @ElementCollection
    public Set<String> tours;
    // we have to made no arguement constructor in model
    //

    public User() {

    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
        this.tours = new HashSet<>();
    }

}
