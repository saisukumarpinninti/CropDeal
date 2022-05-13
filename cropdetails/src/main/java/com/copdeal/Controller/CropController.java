package com.copdeal.Controller;

import com.copdeal.entity.Crop;
import com.copdeal.service.CropService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Crop> getCrops() {
        return cropService.getAllCrops();
    }

    //returns  the list of crops by a specific farmerid
    @GetMapping("/{farmerid}/all")
    @ApiOperation(value = "Get all Crops of a Specific farmer")
    public List<Crop> getListByFarmerId(@PathVariable String farmerid) {
        return cropService.getListByFarmer(farmerid);
    }


    //Returns the data of The Crop by using id
    @GetMapping("/{Id}")
    @ApiOperation(value = "Get The Crop Data ")
    public Crop findById(@PathVariable String Id) {
        return cropService.findById(Id);
    }

    //Adds the Crop into the database
    @PostMapping("/add")
    @ApiOperation(value = "Add A new Crop")
    public Crop addCrop(@RequestBody Crop s) {
        return cropService.AddCrop(s);
    }

    //Updates the Crop data
    @PutMapping("/update")
    @ApiOperation(value = "Update  an existing Crop ")
    public Crop updateCrop(@RequestBody Crop s) {
        return cropService.updateCrop(s);
    }

    //Deletes the Crop data by using id
    @DeleteMapping("/delete/{Id}")
    @ApiOperation(value = "Delete an existing Crop By id ")
    public String deleteCrop(@PathVariable String Id){
        return cropService.deleteById(Id);
    }
}
