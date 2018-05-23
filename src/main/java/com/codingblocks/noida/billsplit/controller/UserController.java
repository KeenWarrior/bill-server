package com.codingblocks.noida.billsplit.controller;

import com.codingblocks.noida.billsplit.model.Tour;
import com.codingblocks.noida.billsplit.model.User;
import com.codingblocks.noida.billsplit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    // extra properties
    // @Rest controller add some extra properties or additional benefits to the
    // class where this notation is
    @Autowired
    UserService userService;

    @PostMapping("/users/")
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable("id") String id) {
        return userService.getUser(id);
    }

    @PostMapping("/users/{id}/tours/")
    public void addTour(@PathVariable("id") String user, @RequestBody String tour) {
        userService.addTour(user, tour);
    }

    @PutMapping("/users/{id}")
    public void updateUser(@PathVariable("id") String id, @RequestBody User user) {
        userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") String id) {
        userService.deleteUser(id);
    }

    @GetMapping("/users/{id}/tours/")
    public List<Tour> getTours(@PathVariable("id") String id) {
        return userService.getTours(id);
    }


}
