package com.copdeal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class FarmerService {
    @Autowired
    private RestTemplate restTemplate;

    //Checks and Gets the Boolean True if the Farmer Exits and False If Not By id Provided  as
    // The Argument Here to the farmerService .
    public Boolean checkIsFarmer(String Id){
        return restTemplate.getForObject("http://FarmerService/farmer/check/"+Id,Boolean.class);
    }
}
