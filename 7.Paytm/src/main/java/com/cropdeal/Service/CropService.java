package com.cropdeal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CropService {
    @Autowired
    private RestTemplate restTemplate;
    public void updateCrop(String Id){
         restTemplate.getForObject("http://CropService/Crop/inactive/"+Id,String.class);
    }
}
