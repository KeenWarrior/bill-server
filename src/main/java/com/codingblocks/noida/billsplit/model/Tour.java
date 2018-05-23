package com.codingblocks.noida.billsplit.model;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.*;

@Entity
public class Tour {
    @Id
    public String id;
    public String name;

    @ElementCollection
    public Set<String> users;

    @OneToMany
    public List<Transaction> transactions;


    public Tour() {
        this.users = new HashSet<>();
        this.transactions = new ArrayList<>();
    }

    public Tour(String id, String name) {
        this.name = name;
        this.id = id;
        this.users = new HashSet<>();
        this.transactions = new ArrayList<>();
    }

    public  void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }
    public List<Owe> balance() {

        List<Owe> owes = new LinkedList<>();

        Map<String, Integer> balances = new HashMap<>();

        int sum = 0;

        users.forEach(user -> balances.put(user, 0));

        for (Transaction transaction: transactions ) {
            String payer = transaction.payer;
            balances.put(payer , balances.get(payer) + transaction.amount);
            sum += transaction.amount;
        }

        balances.forEach((key, val) -> System.out.println(key + " " + val));

        int perPerson = sum/(users.size());

        Queue<Remain> positive = new LinkedList<>();
        Queue<Remain> negative = new LinkedList<>();

        for (Map.Entry<String, Integer> entry : balances.entrySet()){
            int balance = entry.getValue() - perPerson;
            if (balance > 0){
                while (negative.size() > 0 && balance > 0){
                    Remain temp = negative.peek();
                    if (balance >= temp.amount){
                        owes.add(new Owe(temp.id, entry.getKey(), temp.amount));
                        negative.remove();
                        balance -= temp.amount;
                    } else {
                        temp.amount -= balance;
                        owes.add(new Owe(temp.id, entry.getKey(), balance));
                        balance = 0;
                    }
                }

                if (balance > 0){
                    positive.add(new Remain(entry.getKey(), balance));
                }
            }

            if (balance < 0){

                balance = -balance;

                while (positive.size() > 0 && balance > 0){
                    Remain temp = positive.peek();
                    if (balance >= temp.amount){
                        owes.add(new Owe(entry.getKey(), temp.id, temp.amount));
                        positive.remove();
                        balance -= temp.amount;
                    } else {
                        temp.amount -= balance;
                        owes.add(new Owe(entry.getKey(), temp.id, balance));
                        balance = 0;
                    }
                }

                if (balance > 0){
                    negative.add(new Remain(entry.getKey(), balance));
                }
            }
        }

        return owes;
    }

    public static class Owe {
        public String payer;
        public String receiver;
        public int amount;

        public Owe(String payer, String receiver, int amount) {
            this.payer = payer;
            this.receiver = receiver;
            this.amount = amount;
        }
    }

    public static class Remain {
        String id;
        int amount;

        public Remain(String id, int amount) {
            this.id = id;
            this.amount = amount;
        }
    }
}
