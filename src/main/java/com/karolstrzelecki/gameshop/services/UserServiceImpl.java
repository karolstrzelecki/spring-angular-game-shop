package com.karolstrzelecki.gameshop.services;

import com.karolstrzelecki.gameshop.models.User;
import com.karolstrzelecki.gameshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User getUser() throws AccessDeniedException {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println(userName);
        if (Objects.isNull(userName)) {
            throw new AccessDeniedException("Invalid access");
        }

        Optional<User> user = userRepository.findByUsername(userName);

        if(!user.isPresent()){
            throw new AccessDeniedException("Db Error");
        }
        System.out.println(user.get());


        return user.get();



    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
