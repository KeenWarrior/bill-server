package com.codingblocks.noida.billsplit.controller;

import com.codingblocks.noida.billsplit.model.Tour;
import com.codingblocks.noida.billsplit.model.Transaction;
import com.codingblocks.noida.billsplit.model.User;
import com.codingblocks.noida.billsplit.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TourController {
    @Autowired
    TourService tourService;

    @GetMapping("/tours/{id}/")
    public Tour getTour(@PathVariable("id") String id){
        return tourService.getTour(id);
    }

    @PostMapping("/tours/")
    public void createTour(@RequestBody Tour tour){
        tourService.createTour(tour);
    }

    @PostMapping("/tours/{id}/users/")
    public void addUser(@PathVariable("id") String tour, @RequestBody String user){
        tourService.addUser(tour, user);
    }

    @GetMapping("/tours/{id}/users/")
    public List<User> getUsers(@PathVariable("id") String tour){
        return tourService.getUsers(tour);
    }

    @PutMapping("/tours/{id}/")
    public void updateTour(@PathVariable("id") String id , @RequestBody Tour tour) {
        tourService.updateTour(id, tour);
    }

    @DeleteMapping("/tours/{id}/")
    public void deleteTour(@PathVariable("id") String id) {
        tourService.deleteTour(id);
    }

    @PostMapping("/tours/{id}/transactions/")
    public void createTransaction(@PathVariable("id") String id, @RequestBody Transaction transaction){
        tourService.createTransaction(id, transaction);
    }

    @GetMapping("/tours/{id}/transactions/")
    public List<Transaction> getTransactions(@PathVariable("id") String id){
        return tourService.getTransactions(id);
    }

    @GetMapping("/tours/{id}/balance/")
    public List<Tour.Owe> getBalance(@PathVariable("id") String id){
        return tourService.getBalance(id);
    }

}
