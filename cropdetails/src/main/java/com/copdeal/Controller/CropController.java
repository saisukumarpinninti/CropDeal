package com.copdeal.Controller;

import com.copdeal.entity.Crop;
import com.copdeal.service.CropService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Crop")
public class CropController {

    @Autowired
    private CropService cropService;

    //Returns List Of All The crops
    @GetMapping("/all")
    @ApiOperation(value = "Get All Crops ")
    public ResponseEntity<List<Crop>> getCrops() {
        try {
            return new ResponseEntity<>(cropService.getAllCrops(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //returns  the list of crops by a specific farmerid
    @GetMapping("/{farmerid}/all")
    @ApiOperation(value = "Get all Crops of a Specific farmer")
    public ResponseEntity<List<Crop>> getListByFarmerId(@PathVariable String farmerid) {
        try {
            return new ResponseEntity<>(cropService.getListByFarmer(farmerid), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //returns  the list of crops by a specific name
    @GetMapping("/{name}/all")
    @ApiOperation(value = "Get all Crops of a Specific name")
    public ResponseEntity<List<Crop>> getListByname(@PathVariable String name) {
        try {
            return new ResponseEntity<>(cropService.getListByname(name), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //returns the list of crops by a Boolean Active or Inactive
    @GetMapping("/{Status}/all")
    @ApiOperation(value = "Get all Crops of Active or All Crops inactive")
    public ResponseEntity<List<Crop>> getListByFarmerId(@PathVariable Boolean Status) {
        try {
            return new ResponseEntity<>(cropService.getListByActive(Status), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Returns the data of The Crop by using id
    @GetMapping("/{Id}")
    @ApiOperation(value = "Get The Crop Data ")
    public ResponseEntity<Crop> findById(@PathVariable String Id) {
        try {
            return new ResponseEntity<>(cropService.findById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Adds the Crop into the database
    @PostMapping("/add")
    @ApiOperation(value = "Add A new Crop")
    public ResponseEntity<Crop> addCrop(@RequestBody Crop s) {
        try {
            return new ResponseEntity<>(cropService.AddCrop(s), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Updates the Crop data
    @PutMapping("/update")
    @ApiOperation(value = "Update  an existing Crop ")
    public ResponseEntity<Crop> updateCrop(@RequestBody Crop s) {
        try {
            return new ResponseEntity<>(cropService.updateCrop(s), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deletes the Crop data by using id
    @DeleteMapping("/delete/{Id}")
    @ApiOperation(value = "Delete an existing Crop By id ")
    public ResponseEntity<String> deleteCrop(@PathVariable String Id) {
        try {
            return new ResponseEntity<>(cropService.deleteById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
