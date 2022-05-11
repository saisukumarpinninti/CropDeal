package com.cropdeal.Controller;

import com.cropdeal.entity.Dealer;
import com.cropdeal.service.DealerService;
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
    public List<Dealer> getDealers() {
        return DealerService.getAllDealers();
    }

    //Returns the data of The Dealer by using id
    @GetMapping("/{Id}")
    public Dealer findById(@PathVariable String Id) {
        return DealerService.findById(Id);
    }

    //Adds the Dealer into the database
    @PostMapping("/add")
    public Dealer addDealer(@RequestBody Dealer s) {
        return DealerService.AddDealer(s);
    }

    //Updates the Dealer data
    @PutMapping("/update")
    public Dealer updateDealer(@RequestBody Dealer s) {
        return DealerService.updateDealer(s);
    }

    //Deletes the Dealer data by using id
    @DeleteMapping("/delete/{Id}")
    public String deleteDealer(@PathVariable String Id){
        return DealerService.deleteById(Id);
    }


}
