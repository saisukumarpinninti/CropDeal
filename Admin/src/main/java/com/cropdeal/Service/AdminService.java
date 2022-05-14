package com.cropdeal.Service;


import com.cropdeal.Repository.AdminRepository;
import com.cropdeal.entity.Admin;
import com.cropdeal.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserService userService;

    //Returns the data of The Admin by using id if the Admin exits
    public Admin findById(String Id) {
        return adminRepository.findById(Id).get();

    }

    //Adds the Admin into the databases of admin & User
    public Admin addAdmin(Admin a) {
        //Encrypting the Password
        a.setPassword(new BCryptPasswordEncoder().encode(a.getPassword()));
        //Creating User
        User u = userService.generateUser(a);
        //Adding User
        userService.addUser(u);
        return adminRepository.save(a);
    }

    //Updates the Admin data in the databases of admin & User  if the Admin exits
    public Admin updateAdmin(Admin a) {
        a.setPassword(new BCryptPasswordEncoder().encode(a.getPassword()));
        User u = userService.generateUser(a);
        userService.UpdateUser(u);
        return adminRepository.save(a);
    }

    //Deletes the Admin data from the databases of admin & User if the Admin exits
    public String deleteById(String Id) {
        userService.DeleteUser(Id);
        adminRepository.deleteById(Id);
        return "deleted SuccessFully";
    }

}
