package com.copdeal.service;


import com.copdeal.entity.Crop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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
        F.setActive(Boolean.TRUE);
        return  CropRepository.save(F);}
        else{
            F.setName("Farmer DoesNot Exits");
            return F;
        }
    }

    //Updates the Crop data in the database if the Crop exits and returns the updated the data
    //if the Crop not exits returns the error
    public Crop updateCrop(Crop F){

            return  CropRepository.save(F);



    }

    //Deletes the Crop data in the database if the Crop exits and returns the Result
    //if the Crop not exits returns the error result
    public String deleteById(String Id) {


            CropRepository.deleteById(Id);
            return "Deleted SuccessFully";

    }

    //get the list of crops by a specific farmerid
    public  List<Crop> getListByFarmer(String farmerId){
        return CropRepository.getListByfarmerid(farmerId);
    }

    public List<Crop> getListByname(String name){
         return CropRepository.getListByname(name);
}

    public List<Crop> getListByActive(String Active){
        List<Crop> s = CropRepository.findAll();
        return s.stream().filter(f -> Boolean.TRUE.equals(f.getActive())).collect(Collectors.toList());
    }

    //checks & Sends if the Crop exits or not
    public boolean Checkexits(String Id){
        return CropRepository.existsById(Id);
    }


}
