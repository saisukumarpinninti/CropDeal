package com.cropdeal.service;

import java.util.List;
import com.cropdeal.Repository.FarmerRepository;
import com.cropdeal.entity.Farmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
* Service Class For The Farmer
*/
@Service
public class FarmerService  {
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private SequenceGenerator sg ;

    //Returns List Of All The Farmers
    public List<Farmer> getAllFarmers(){
        return  farmerRepository.findAll();
    }

    //Returns the data of The Farmer by using id if the farmer exits or returns farmer with no data .
    public Farmer findById(String Id){

        if(Checkexits(Id)){
            return farmerRepository.findById(Id).get();
        }
        else {
            return new Farmer();
        }
    }

    //Adds the Farmer into the database
    public Farmer AddFarmer(Farmer F){

        //Generating the id of the farmer
        F.setId(sg.getSequenceNumber("Farmer_Sequence"));

        //Setting the Status of the Farmer to Active.
        F.setStatus(Boolean.TRUE);
        return  farmerRepository.save(F);
    }

    //Updates the Farmer data in the database if the farmer exits and returns the updated the data
    //if the Farmer not exits returns the error
    public Farmer updateFarmer(Farmer F){

        if(Checkexits(F.getId())){
            return  farmerRepository.save(F);
        }

        else {
            F.setFirstName("UserNotFound");
            return F;
        }
    }

    //Deletes the Farmer data in the database if the farmer exits and returns the Result
    //if the Farmer not exits returns the error result
    public String deleteById(String Id) {

        if(Checkexits(Id)){
            farmerRepository.deleteById(Id);
            return "Deleted SuccessFully";
        }
        else {
            return "FarmerNotFound";
        }
    }

    //checks & Sends if the farmer exits or not
    public boolean Checkexits(String Id){
        return farmerRepository.existsById(Id);
    }


}
