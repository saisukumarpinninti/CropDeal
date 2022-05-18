package com.cropdeal.service;

import com.cropdeal.Entity.User;
import com.cropdeal.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getall(){
        return userRepository.findAll();
    }

    //Returns the data of The User by using id if the User exits.
    public User findById(String Id) {
        return userRepository.findById(Id).get() ;
    }

    //Adds the user into the database
    public User addUser(User u){
        return userRepository.save(u);
    }

    //Updates the user data in the database if the user exits & checks that role is not changes
    //and returns the updated the data
    public User UpdateUser(User u){
        if(checkRole(u)){
            return userRepository.save(u);}
        else {
            return u;
        }
    }

    //Deletes the user data in the database if the user exits and returns the Result
    public String deleteById(String Id) {

        if (Checkexits(Id)) {
            userRepository.deleteById(Id);
            return "Deleted SuccessFully";
        }
        else {
            return "userNotFound";
        }
    }

   // checks and sends the boolean if the role is changed or not
    public boolean checkRole(User u){
        return  findById(u.getId()).getRole().equals(u.getRole());
    }

    //checks & Sends if the user exits or not
    public boolean Checkexits(String Id) {
        return userRepository.existsById(Id);
    }
}
