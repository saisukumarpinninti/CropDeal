package com.cropdeal.service;

import com.cropdeal.entity.Dealer;
import com.cropdeal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    //generates user class
    public User generateUser(Dealer F) {
        User u = new User("F"+ F.getId(), F.getEmail(), F.getFirstName()+" "+ F.getLastName(), F.getPassword(),"Dealer");
        return u;
    }

    //addsUser
    public Boolean addUser(User u){
        User s =  restTemplate.postForObject("http://Api-Service/user/add/",u,User.class);
        return (u==s);
    }

    //UpdatesUser
    public Boolean UpdateUser(User u){
        //Resetting the User Role
        u.setRole("Dealer");
        User s =  restTemplate.postForObject("http://Api-Service/user/update/",u,User.class);
        return (u==s);
    }

    //Deletes User
    public void DeleteUser(String id){
          restTemplate.delete("http://Api-Service/user/delete/"+id);
    }


}
