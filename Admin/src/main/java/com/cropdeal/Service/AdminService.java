package com.cropdeal.Service;


import com.cropdeal.Repository.AdminRepository;
import com.cropdeal.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    //Returns the data of The Admin by using id if the Admin exits or returns Admin with no data .
    public Admin findById(String Id){
        if (Checkexits(Id)){
            return adminRepository.findById(Id).get();
        }
        else {
            return new Admin();
        }
    }

    //Adds the Admin into the database
    public Admin addAdmin(Admin a){
        return adminRepository.save(a);
    }

    //Updates the Admin data in the database if the Admin exits and returns the updated the data
    //if the Admin not exits returns the error
    public Admin updateAdmin(Admin a){
        if (Checkexits(a.getId())){
            return adminRepository.save(a);
        }
        else {
            a.setName("admin not available");
            return a;
        }
    }

    //Deletes the Admin data in the database if the Admin exits and returns the Result
    //if the Admin not exits returns the error result
    public String deleteById(String Id) {
        if (Checkexits(Id)){
                adminRepository.deleteById(Id);
                return "deleted SuccessFully";
        }
        else{ return "Admin Not Available";}
    }

    //checks & Sends if the Admin exits or not
    public boolean Checkexits(String Id){return adminRepository.existsById(Id);};
   
    
    
}
