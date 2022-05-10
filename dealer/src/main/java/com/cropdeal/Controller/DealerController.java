package com.cropdeal.Controller;

import com.cropdeal.entity.Dealer;
import com.cropdeal.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class DealerController {

    @Autowired
    private DealerService DealerService;

    //Returns List Of All The Dealers
    @GetMapping("/Dealers")
    public List<Dealer> getDealers() {
        return DealerService.getAllDealers();
    }

    //Returns the data of The Dealer by using id
    @GetMapping("/Dealer/{Id}")
    public Dealer findById(@PathVariable String Id) {
        return DealerService.findById(Id);
    }

    //Adds the Dealer into the database
    @PostMapping("/Dealer/add")
    public Dealer addDealer(@RequestBody Dealer s) {
        return DealerService.AddDealer(s);
    }

    //Updates the Dealer data
    @PutMapping("/Dealer/update")
    public Dealer updateDealer(@RequestBody Dealer s) {
        return DealerService.updateDealer(s);
    }

    //Deletes the Dealer data by using id
    @DeleteMapping("/Dealer/delete/{Id}")
    public String deleteDealer(@PathVariable String Id){
        return DealerService.deleteById(Id);
    }

}
