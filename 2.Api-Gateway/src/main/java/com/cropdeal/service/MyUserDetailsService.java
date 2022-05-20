package com.cropdeal.service;


import com.cropdeal.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        String g = String.valueOf(s.charAt(0));
        org.springframework.security.core.userdetails.User u1 ;
        com.cropdeal.Entity.User u;
        ArrayList<GrantedAuthority> a = new ArrayList();
        if (g.equals("F")) {

             u = restTemplate.getForObject("http://FarmerService/farmer/getdetails/" + s, User.class);
            a.add(new SimpleGrantedAuthority("ROLE_" + u.getRole()));
        }
        else if (g.equals("D")) {
           u = restTemplate.getForObject("http://DealerService/Dealer/getdetails/" + s, User.class);
            a.add(new SimpleGrantedAuthority("ROLE_" + u.getRole()));

        }
        else if (g.equals("A")) {
            u = restTemplate.getForObject("http://AdminService/Admin/getdetails/" + s, User.class);
            a.add(new SimpleGrantedAuthority("ROLE_" + u.getRole()));
        }
        else return null;
        u1=new org.springframework.security.core.userdetails.User(u.getId(), u.getPassword(), a);
        System.out.println(u.toString());
        return u1;
    }
}