package com.copdeal.service;


import com.copdeal.entity.Crop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
* Service Class For The Crops
*/

@Service
public class CropService  {
    @Autowired
    private com.copdeal.Repository.CropRepository CropRepository;
    @Autowired
    private SequenceGenerator sg ;

    @Autowired
    private FarmerService farmerService;

    //Returns List Of All The Crops
    public List<Crop> getAllCrops(){
        return  CropRepository.findAll();
    }

    //Returns the data of The Crop by using id if the Crop exits or returns Crop with no data .
    public Crop findById(String Id){

        if(Checkexits(Id)){
            return CropRepository.findById(Id).get();
        }
        else {
            return new Crop();
        }
    }

    //Adds the Crop into the database
    public Crop AddCrop(Crop F){
        if(farmerService.checkIsFarmer(F.getFarmerid())){
        //Generating the id of the Crop
        F.setId(sg.getSequenceNumber("Crop_Sequence"));
            System.out.println(F.toString());
        return  CropRepository.save(F);}
        else{
            F.setName("Farmer DoesNot Exits");
            return F;
        }
    }

    //Updates the Crop data in the database if the Crop exits and returns the updated the data
    //if the Crop not exits returns the error
    public Crop updateCrop(Crop F){

        if(Checkexits(F.getId())){

            return  CropRepository.save(F);
        }

        else {
            F.setName("CropFound");
            return F;
        }
    }

    //Deletes the Crop data in the database if the Crop exits and returns the Result
    //if the Crop not exits returns the error result
    public String deleteById(String Id) {

        if(Checkexits(Id)){
            CropRepository.deleteById(Id);
            return "Deleted SuccessFully";
        }
        else {
            return "CropNotFound";
        }
    }

    //checks & Sends if the Crop exits or not
    public boolean Checkexits(String Id){
        return CropRepository.existsById(Id);
    }


}
