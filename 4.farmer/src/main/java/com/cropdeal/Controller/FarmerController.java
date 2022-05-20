package com.cropdeal.Controller;

import com.cropdeal.entity.Farmer;
import com.cropdeal.entity.User;
import com.cropdeal.service.FarmerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController

@RequestMapping("/farmer")

public class FarmerController {

    @Autowired
    private FarmerService farmerService;

    //Returns List Of All The Farmers
    @GetMapping("/all")
    @ApiOperation(value = "Get all the Farmers List")
    public ResponseEntity<List<Farmer>> getFarmers() {
        try {
            return new ResponseEntity<>(farmerService.getAllFarmers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //Returns the data of The Farmer by using id
    @GetMapping("/{Id}")
    @ApiOperation(value = "Get the Farmers Particular Farmer Details By Id")
    public ResponseEntity<Farmer> findById(@PathVariable String Id) {
        try {
            return new ResponseEntity<>(farmerService.findById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //Adds the Farmer into the database
    @PostMapping("/add")
    @ApiOperation(value = "Add a New Farmer")
    public ResponseEntity<Farmer> addFarmer(@RequestBody Farmer s) {
        try {
            return new ResponseEntity<>(farmerService.AddFarmer(s), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            s.setFirstName(e.toString());
            return new ResponseEntity<>(s,HttpStatus.CONFLICT);
        }
    }

    //Updates the Farmer data
    @PutMapping("/update")
    @ApiOperation(value = "Update an Existing Farmer")

    public ResponseEntity<Farmer> updateFarmer(@RequestBody Farmer s) {
        try {
            return new ResponseEntity<>(farmerService.updateFarmer(s), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    //Deletes the Farmer data by using id
    @ApiOperation(value = "Delete an existing Farmer By id ")
    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<String> deleteFarmer(@PathVariable String Id) {
        try {
            return new ResponseEntity<String>(farmerService.deleteById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getdetails/{Id}")

    public ResponseEntity<User> Farmerdetails(@PathVariable String Id) {
        try {
            return new ResponseEntity<>(farmerService.getdetails(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/check/{Id}")
    @ApiOperation(value = "To check the farmerid is Available or not")
    public Boolean FarmerExits(@PathVariable String Id) {
        return farmerService.Checkexits(Id);
    }

}
