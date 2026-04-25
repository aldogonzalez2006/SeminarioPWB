package com.product.api.service;

import com.product.api.entity.User;
import com.product.api.repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SvcUserImp implements SvcUser {

    @Autowired
    private RepoUser repoUser;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        // NUEVO — encriptamos antes de guardar
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );

        return repoUser.save(user);
    }

    @Override
    public List<User> getUsers(){
        return repoUser.findAll();
    }

}