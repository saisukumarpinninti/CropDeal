package com.cropdeal.service;

import com.cropdeal.Repository.DealerRepository;
import com.cropdeal.entity.Dealer;
import com.cropdeal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


/*
 * Service Class For The Dealer
 */
@Service
public class DealerService {
    @Autowired
    private DealerRepository DealerRepository;
    @Autowired
    private SequenceGenerator sg;



    public DealerService() {
    }

    //Returns List Of All The Dealers
    public List<Dealer> getAllDealers() {
        return DealerRepository.findAll();
    }

    //Returns the data of The Dealer by using id if the Dealer exits or returns Dealer with no data .
    public Dealer findById(String Id) {

        return DealerRepository.findById(Id).get();

    }

    //Adds the Dealer into the database
    public Dealer AddDealer(Dealer F) {

        //Generating the id of the Dealer
        F.setId("D"+sg.getSequenceNumber("Dealer_Sequence"));

        //Setting the Status of the Dealer to Active.
        F.setStatus(Boolean.TRUE);
        //Encrypting the Password
        F.setPassword(new BCryptPasswordEncoder().encode(F.getPassword()));

        return DealerRepository.save(F);
    }

    //Updates the Dealer data in the database if the Dealer exits and returns the updated the data

    public Dealer updateDealer(Dealer F) {
        F.setPassword(new BCryptPasswordEncoder().encode(F.getPassword()));

        return DealerRepository.save(F);
    }

    //Deletes the Dealer data in the database if the Dealer exits and returns the Result
    public String deleteById(String Id) {
        DealerRepository.deleteById(Id);
        return "Deleted SuccessFully";

    }


    public User getdetails(String Id){
        Dealer f = findById(Id);
        return new User(f.getId(),f.getPassword(),"Dealer");
    }

    //checks & Sends if the Dealer exits or not
    public boolean Checkexits(String Id) {
        return DealerRepository.existsById(Id);
    }


}
