package com.codingblocks.noida.billsplit.service;

import com.codingblocks.noida.billsplit.model.Tour;
import com.codingblocks.noida.billsplit.model.User;
import com.codingblocks.noida.billsplit.repository.TourRepository;
import com.codingblocks.noida.billsplit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired     // when it get the object it will map it automatically..
    UserRepository userRepository;

    @Autowired
    TourRepository tourRepository;

    @Autowired
    TourService tourService;

    public User getUser(String id) {
        return userRepository.findById(id).get();

    }

    public void createUser(User user) {
        userRepository.save(user);

        for (String tourid: user.tours) {
            Tour tour = tourRepository.findById(tourid).get();
            tour.users.add(user.id);
            tourRepository.save(tour);
        }


    }

    public void updateUser(String id,  User user) {
        userRepository.save(user);

        for (String tourid: user.tours) {
            Tour tour = tourRepository.findById(tourid).get();
            tour.users.add(user.id);
            tourRepository.save(tour);
        }

    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    public List<Tour> getTours(String id){

        List<Tour> tours = new ArrayList<>();

        for (String tour_id : userRepository.findById(id).get().tours) {
            tours.add(tourRepository.findById(tour_id).get());
        }

        return tours;
    }


    public void addTour(String user, String tour) {
        User temp = userRepository.findById(user).get();
        temp.tours.add(tour);
        userRepository.save(temp);

        Tour t = tourRepository.findById(tour).get();
        t.users.add(user);
        tourRepository.save(t);
    }
}
