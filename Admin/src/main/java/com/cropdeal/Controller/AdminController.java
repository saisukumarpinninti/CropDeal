package com.cropdeal.Controller;

import com.cropdeal.Service.AdminService;
import com.cropdeal.entity.Admin;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminService AdminService;

    //Returns the data of The Admin by using id
    @GetMapping("/{Id}")
    @ApiOperation(value = "Get the data of The Admin by using id")
    public Admin findById(@PathVariable String Id) {
        return AdminService.findById(Id);
    }

    //Adds the Admin into the database
    @PostMapping("/add")
    @ApiOperation(value = "Add a New Admin")
    public Admin addAdmin(@RequestBody Admin s) {
        return AdminService.addAdmin(s);
    }

    //Updates the Admin data
    @PutMapping("/update")
    @ApiOperation(value = "Update an Existing Admin")
    public Admin updateAdmin(@RequestBody Admin s) {
        return AdminService.updateAdmin(s);
    }

    //Deletes the Admin data by using id
    @DeleteMapping("/delete/{Id}")
    @ApiOperation(value = "Delete an Admin")
    public String deleteAdmin(@PathVariable String Id){
        return AdminService.deleteById(Id);
    }
}