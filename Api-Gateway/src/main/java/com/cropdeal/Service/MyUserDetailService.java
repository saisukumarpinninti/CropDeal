package com.cropdeal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      //  User u = userService.getbyUsername(username);
        //return new org.springframework.security.core.userdetails.User(u.getName(),u.getPassword(),new ArrayList<>());
        return new org.springframework.security.core.userdetails.User("foo","foo",new ArrayList<>());

    }
}
