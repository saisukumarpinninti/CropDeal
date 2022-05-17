package com.cropdeal.Controller;

import com.cropdeal.entity.Dealer;
import com.cropdeal.service.DealerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/Dealer")
public class DealerController {

    @Autowired
    private DealerService DealerService;

    //Returns List Of All The Dealers
    @GetMapping("/all")
    @ApiOperation(value = "Get all the Dealers List")
    public ResponseEntity<List<Dealer>> getDealers() {
        try {
            return new ResponseEntity<>(DealerService.getAllDealers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //Returns the data of The Dealer by using id
    @GetMapping("/{Id}")
    @ApiOperation(value = "Get the Dealers Particular Dealer Details By Id")
    public ResponseEntity<Dealer> findById(@PathVariable String Id) {
        try {
            return new ResponseEntity<>(DealerService.findById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Adds the Dealer into the database
    @PostMapping("/add")
    @ApiOperation(value = "Add a New Dealer")
    public ResponseEntity<Dealer> addDealer(@RequestBody Dealer s) {
        try {
            return new ResponseEntity<>(DealerService.AddDealer(s), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Updates the Dealer data
    @PutMapping("/update")
    @ApiOperation(value = "Update an Existing Dealer")
    public ResponseEntity<Dealer> updateDealer(@RequestBody Dealer s) {
        try {
            return new ResponseEntity<>(DealerService.updateDealer(s), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //Deletes the Dealer data by using id
    @DeleteMapping("/delete/{Id}")
    @ApiOperation(value = "Delete an existing Dealer By id ")
    public ResponseEntity<String> deleteDealer(@PathVariable String Id) {
        try {
            return new ResponseEntity<String>(DealerService.deleteById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/check/{Id}")
    @ApiOperation(value = "To check the Dealerid is Available or not")
    public Boolean DealerExits(@PathVariable String Id) {
        return DealerService.Checkexits(Id);
    }


}
