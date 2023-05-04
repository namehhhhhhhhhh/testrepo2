package com.dotseven.poc.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public boolean dummyLogin(User user) {
        for (Iterator<User> it = getUsers().iterator(); it.hasNext();) {
            if(it.next().equals(user))
                return true;
        }
        return false;
    }

    public boolean login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).size() > 0;
    }

}
