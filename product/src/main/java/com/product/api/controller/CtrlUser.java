package com.product.api.controller;


import com.product.api.entity.User;
import com.product.api.service.SvcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CtrlUser {

    @Autowired
    private SvcUser svcUser;

    @PostMapping
    public User create(@RequestBody User user) {
        return svcUser.createUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return svcUser.getUsers();
    }

}