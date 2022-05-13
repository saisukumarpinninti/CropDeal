package com.cropdeal.Service;


import com.cropdeal.Repository.AdminRepository;
import com.cropdeal.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    //Returns the data of The Admin by using id if the Admin exits
    public Admin findById(String Id) {
        return adminRepository.findById(Id).get();

    }

    //Adds the Admin into the database
    public Admin addAdmin(Admin a) {
        return adminRepository.save(a);
    }

    //Updates the Admin data in the database if the Admin exits
    public Admin updateAdmin(Admin a) {
        return adminRepository.save(a);
    }

    //Deletes the Admin data in the database if the Admin exits
    public String deleteById(String Id) {
        adminRepository.deleteById(Id);
        return "deleted SuccessFully";
    }

    //checks & Sends if the Admin exits or not
    public boolean Checkexits(String Id) {
        return adminRepository.existsById(Id);
    }


}
