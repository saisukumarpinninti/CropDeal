package com.cropdeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.cropdeal.Entity.User u = userService.findById(s);
        ArrayList<GrantedAuthority> a = new ArrayList();
        a.add(new SimpleGrantedAuthority("ROLE_" + u.getRole()));
        System.out.println(s+" - username"+a+" - Role");
        return new User(u.getId(), u.getPassword(), a);
    }
}
