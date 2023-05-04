package com.dotseven.poc.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Vector;

@Service
public class UserService {


    public List<User> getUsers() {
    return new Vector<User>();
    }

    public boolean dummyLogin(User user) {
        for (Iterator<User> it = getUsers().iterator(); it.hasNext();) {
            if(it.next().equals(user))
                return true;
        }
        return false;
    }

    public boolean login(User user) {
        return true;
    }

}
