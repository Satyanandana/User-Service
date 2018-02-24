package com.service.user.controllers;

import com.service.user.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/all")
    private Collection<String> getUsers(){
        List<String> appName = Stream.of("-App").collect(Collectors.toList());
        return userRepository.findAll().stream().map(user -> user.getName()+ appName.toString()).collect(Collectors.toList());
    }
}
