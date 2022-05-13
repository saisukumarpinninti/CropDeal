package com.cropdeal.Controller;

import com.cropdeal.entity.Dealer;
import com.cropdeal.service.DealerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Dealer> getDealers() {
        return DealerService.getAllDealers();
    }

    //Returns the data of The Dealer by using id
    @GetMapping("/{Id}")
    @ApiOperation(value = "Get the Dealers Particular Dealer Details By Id")
    public Dealer findById(@PathVariable String Id) {
        return DealerService.findById(Id);
    }

    //Adds the Dealer into the database
    @PostMapping("/add")
    @ApiOperation(value = "Add a New Dealer")
    public Dealer addDealer(@RequestBody Dealer s) {
        return DealerService.AddDealer(s);
    }

    //Updates the Dealer data
    @PutMapping("/update")
    @ApiOperation(value = "Update an Existing Dealer")
    public Dealer updateDealer(@RequestBody Dealer s) {
        return DealerService.updateDealer(s);
    }

    //Deletes the Dealer data by using id
    @DeleteMapping("/delete/{Id}")
    @ApiOperation(value = "Delete an existing Dealer By id ")
    public String deleteDealer(@PathVariable String Id){
        return DealerService.deleteById(Id);
    }


}
