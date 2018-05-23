package com.codingblocks.noida.billsplit.service;

import com.codingblocks.noida.billsplit.model.Tour;
import com.codingblocks.noida.billsplit.model.Transaction;
import com.codingblocks.noida.billsplit.model.User;
import com.codingblocks.noida.billsplit.repository.TourRepository;
import com.codingblocks.noida.billsplit.repository.TransactionRepository;
import com.codingblocks.noida.billsplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TourService {

    @Autowired
    TourRepository tourRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TransactionRepository transactionRepository;


    @Autowired
    UserService userService;

    public Tour getTour(String id) {
        return tourRepository.findById(id).get();

    }

    public void createTour(Tour tour) {
        tourRepository.save(tour);

        for (String id: tour.users) {
            User user = userRepository.findById(id).get();
            user.tours.add(tour.id);
            userRepository.save(user);
        }

    }

    public void updateTour(String id, Tour tour) {
        tourRepository.save(tour);

        for (String userId: tour.users) {
            User user = userRepository.findById(userId).get();
            user.tours.add(tour.id);
            userRepository.save(user);
        }
    }

    public void deleteTour(String id) {
        tourRepository.deleteById(id);
    }

    public List<Tour.Owe> getBalance(String id){
        return tourRepository.findById(id).get().balance();
    }

    public void createTransaction(String id, Transaction transaction) {
        Tour tour = tourRepository.findById(id).get();
        transactionRepository.save(transaction);
        tour.transactions.add(transaction);
        tourRepository.save(tour);

    }

    public void addUser(String tour, String user) {
        Tour temp = tourRepository.findById(tour).get();
        temp.users.add(user);
        tourRepository.save(temp);

        User u = userRepository.findById(user).get();
        u.tours.add(tour);
        userRepository.save(u);
    }

    public List<Transaction> getTransactions(String id) {
        Tour tour = tourRepository.findById(id).get();
        return tour.transactions;
    }

    public List<User> getUsers(String tour) {
        Tour t = tourRepository.findById(tour).get();

        List<User> users = new ArrayList<>();

        for (String user : t.users){
            users.add(userRepository.findById(user).get());
        }

        return users;
    }
}

