package com.cropdeal.Controller;

import com.cropdeal.entity.Farmer;
import com.cropdeal.service.FarmerService;
import java.util.List;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController

@RequestMapping("/farmer")

public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    //Returns List Of All The Farmers
    @GetMapping("/all")
    @ApiOperation(value = "Get all the Farmers List")
    public List<Farmer> getFarmers() {
        return farmerService.getAllFarmers();
    }


    //Returns the data of The Farmer by using id
    @GetMapping("/{Id}")
    @ApiOperation(value = "Get the Farmers Particular Farmer Details By Id")
    public Farmer findById(@PathVariable String Id) {
        return farmerService.findById(Id);
    }

    //Adds the Farmer into the database
    @PostMapping("/add")
    @ApiOperation(value = "Add a New Farmer")
    public Farmer addFarmer(@RequestBody Farmer s) {
        return farmerService.AddFarmer(s);
    }

    //Updates the Farmer data
    @PutMapping("/update")
    @ApiOperation(value = "Update an Existing Farmer")
    public Farmer updateFarmer(@RequestBody Farmer s) {
        return farmerService.updateFarmer(s);
    }

    //Deletes the Farmer data by using id
    @ApiOperation(value = "Delete an existing Farmer By id ")
    @DeleteMapping("/delete/{Id}")
    public String deleteFarmer(@PathVariable String Id){
        return farmerService.deleteById(Id);
    }


    @GetMapping("/check/{Id}")
    @ApiOperation(value = "To check the farmerid is Available or not")
    public Boolean FarmerExits(@PathVariable String Id){return farmerService.Checkexits(Id);}

}
