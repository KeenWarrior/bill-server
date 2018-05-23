package com.codingblocks.noida.billsplit.model;

import org.hibernate.annotations.GenerationTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    public String payer;
    public int amount;
    public String note;

    public Transaction() {
    }

    public Transaction(int id, String payer, int amount) {
        this.id = id;
        this.payer = payer;
        this.amount = amount;
    }
}
