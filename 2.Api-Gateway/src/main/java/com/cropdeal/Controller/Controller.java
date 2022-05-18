package com.cropdeal.Controller;

import com.cropdeal.Entity.AuthenticationRequest;
import com.cropdeal.Entity.AuthenticationResponse;
import com.cropdeal.Entity.User;
import com.cropdeal.Util.JwtUtil;
import com.cropdeal.service.MyUserDetailsService;
import com.cropdeal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController
public class Controller {

    @Autowired
    private com.cropdeal.service.UserService UserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/hello")
    public String Hello() {
        String s = "<h1>HelloWorld</h1>";
        return (s);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        }
        catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e,HttpStatus.BAD_GATEWAY);
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @GetMapping("/all")
    public List<User> getUsers(){
        return UserService.getall();
    }
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