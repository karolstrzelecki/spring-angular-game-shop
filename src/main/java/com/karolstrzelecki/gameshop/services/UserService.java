package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.models.User;

import java.nio.file.AccessDeniedException;

public interface UserService {

    User getUser() throws AccessDeniedException;

    void saveUser(User user);
}
