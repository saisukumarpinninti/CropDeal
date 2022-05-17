package com.cropdeal.service;

import com.cropdeal.Repository.FarmerRepository;
import com.cropdeal.entity.Farmer;
import com.cropdeal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * Service Class For The Farmer
 */
@Service
public class FarmerService {
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private SequenceGenerator sg;

    @Autowired
    private UserService userService;

    //Returns List Of All The Farmers
    public List<Farmer> getAllFarmers() {
        return farmerRepository.findAll();
    }

    //Returns the data of The Farmer by using id if the farmer exits.
    public Farmer findById(String Id) {
        return farmerRepository.findById(Id).get();
    }

    //Adds the Farmer into the database
    public Farmer AddFarmer(Farmer F) {

        //Generating the id of the farmer
        F.setId(sg.getSequenceNumber("Farmer_Sequence"));

        //Setting the Status of the Farmer to Active.
        F.setStatus(Boolean.TRUE);
        //Encrypting the Password
        F.setPassword(new BCryptPasswordEncoder().encode(F.getPassword()));
        //Creating User
        User u = userService.generateUser(F);
        //Adding User
        userService.addUser(u);
        return farmerRepository.save(F);
    }


    //Updates the Farmer data in the database if the farmer exits and returns the updated the data
    //if the Farmer not exits returns the error
    public Farmer updateFarmer(Farmer F) {
        F.setPassword(new BCryptPasswordEncoder().encode(F.getPassword()));
        User u = userService.generateUser(F);
        userService.UpdateUser(u);
        return farmerRepository.save(F);
    }

    //Deletes the Farmer data in the database if the farmer exits and returns the Result
    //if the Farmer not exits returns the error result
    public String deleteById(String Id) {

        if (Checkexits(Id)) {
            userService.DeleteUser(Id);
            farmerRepository.deleteById(Id);
            return "Deleted SuccessFully";
        }
        else {
            return "FarmerNotFound";
        }
    }

    //checks & Sends if the farmer exits or not
    public boolean Checkexits(String Id) {
        return farmerRepository.existsById(Id);
    }


}