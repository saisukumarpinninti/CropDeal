package com.cropdeal.Controller;

import com.cropdeal.Entity.User;
import com.cropdeal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController

@RequestMapping("/user")

public class UserController {

    @Autowired
    private UserService UserService;

    

    //Adds the User into the database
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User s) {
        try {
            return new ResponseEntity<>(UserService.addUser(s), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //Updates the User data
    @PostMapping("/update")
    public ResponseEntity<User> updateUser(@RequestBody User s) {
        try {
            return new ResponseEntity<>(UserService.UpdateUser(s), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //Deletes the User data by using id
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteUser(@PathVariable String Id) {
        try {
            return new ResponseEntity<String>(UserService.deleteById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    

}
