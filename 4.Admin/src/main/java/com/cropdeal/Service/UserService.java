package com.cropdeal.Service;

import com.cropdeal.entity.Admin;
import com.cropdeal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    //generates user class
    public User generateUser(Admin F) {
        User u = new User("F"+ F.getId(), F.getEmail(), F.getName(), F.getPassword(),"Admin");
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
        u.setRole("Farmer");
        User s =  restTemplate.postForObject("http://Api-Service/user/update/",u,User.class);
        return (u==s);
    }

    //Deletes User
    public void DeleteUser(String id){
          restTemplate.delete("http://Api-Service/user/delete/"+id);
    }


}
