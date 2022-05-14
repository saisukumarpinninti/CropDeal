package com.cropdeal.Controller;

import com.cropdeal.Service.AdminService;
import com.cropdeal.entity.Admin;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    private AdminService AdminService;

    //Returns the data of The Admin by using id
    @GetMapping("/{Id}")
    @ApiOperation(value = "Get the data of The Admin by using id")
     public ResponseEntity<Admin> findById(@PathVariable String Id) {
        try {
            return new ResponseEntity<>(AdminService.findById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Adds the Admin into the database
    @PostMapping("/add")
    @ApiOperation(value = "Add a New Admin")
     public ResponseEntity<Admin> addAdmin(@RequestBody Admin s) {
        try {
            return new ResponseEntity<>(AdminService.addAdmin(s), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    //Updates the Admin data
    @PutMapping("/update")
    @ApiOperation(value = "Update an Existing Admin")
     public ResponseEntity<Admin> updateAdmin(@RequestBody Admin s) {
        try {
            return new ResponseEntity<>(AdminService.updateAdmin(s), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Deletes the Admin data by using id
    @DeleteMapping("/delete/{Id}")
    @ApiOperation(value = "Delete an Admin")
    public ResponseEntity<String> deleteAdmin(@PathVariable String Id){
        try {
            return new ResponseEntity<>(AdminService.deleteById(Id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}