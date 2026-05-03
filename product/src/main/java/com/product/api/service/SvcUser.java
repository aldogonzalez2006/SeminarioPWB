package com.product.api.service;

import com.product.api.entity.User;

import java.util.List;

public interface SvcUser {

    User createUser(User user);

    List<User> getUsers();

}