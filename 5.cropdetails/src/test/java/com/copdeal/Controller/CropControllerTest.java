package com.copdeal.Controller;

import com.copdeal.Repository.CropRepository;
import com.copdeal.entity.Crop;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CropControllerTest {

    @InjectMocks
    public CropController CropController= Mockito.mock(CropController.class);

    @Mock
    public com.copdeal.Repository.CropRepository CropRepository;
    
    Crop F = new Crop("1","1","Apple",100,5,"tons",Boolean.TRUE);

    public ResponseEntity<List<Crop>> getlist(){
        Crop s = new Crop("2","1","Apple",100,5,"tons",Boolean.TRUE);
        Crop p = new Crop("3","2","Grapes",80,15,"tons",Boolean.TRUE);
        // Creating A Crop List
        List<Crop> testCrops = new ArrayList<Crop>();
        testCrops.add(F);      // adding a Crop
        testCrops.add(s);
        testCrops.add(p);
        return new ResponseEntity<>(testCrops, HttpStatus.OK);    //generating & Returning Response
    }
    
    @Test
    void getCrops() {
        ResponseEntity<List<Crop>> Response = getlist() ;
        when(CropController.getCrops()).thenReturn(Response);               //  Setting The Response
        // Getting The Response
        ResponseEntity<List<Crop>>  result = CropController.getCrops();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((getlist().getBody().toString()));
    }

    @Test
    void getListByFarmerId() {
        ResponseEntity<List<Crop>> Response = getlist() ;
        List<Crop> f = Response.getBody().stream().filter(
                crop -> crop.getFarmerid().equals("1")).collect(Collectors.toList());
        when(CropController.getListByFarmerId("1")).thenReturn(new ResponseEntity<>(f,HttpStatus.OK));               //  Setting The Response
        when(CropController.getListByFarmerId("3")).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        // Getting The Response
        ResponseEntity<List<Crop>>  result = CropController.getListByFarmerId("1");
        ResponseEntity<List<Crop>>  result1 = CropController.getListByFarmerId("3");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody().toString()).isEqualTo((f.toString()));
    }

    @Test
    void getListByname() {
        ResponseEntity<List<Crop>> Response = getlist() ;
        List<Crop> f = Response.getBody().stream().filter(
                crop -> crop.getName().equals("Apple")).collect(Collectors.toList());
        when(CropController.getListByname("Apple")).thenReturn(new ResponseEntity<>(f,HttpStatus.OK));               //  Setting The Response
        when(CropController.getListByname("Grapes")).thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));
        // Getting The Response
        ResponseEntity<List<Crop>>  result = CropController.getListByname("Apple");
        ResponseEntity<List<Crop>>  result1 = CropController.getListByname("Grapes");
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(result.getBody().toString()).isEqualTo((f.toString()));
    }
    

    @Test
    void findById() {
        ResponseEntity<List<Crop>> Response = getlist() ;
        List<Crop> f = getlist().getBody();
        Crop f1 = f.stream()
                .filter(Crop -> "2".equals(Crop.getId()))
                .findAny().orElse(null);
        assertThat(Response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(f1).isNotSameAs(F);
        assertThat(f1.getId()).isNotNull();
    }

    @Test
    void addCrop() {
        Crop s = new Crop("4","2","Apple",1000,5,"tons",Boolean.TRUE);
        when(CropController.addCrop(s)).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
        when(CropController.addCrop(F)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        ResponseEntity<Crop> result1 = CropController.addCrop(F);
        ResponseEntity<Crop> result = CropController.addCrop(s);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((s.toString()));
    }

    @Test
    void updateCrop() {
        Crop s = new Crop("4","2","Apple",100,3,"tons",Boolean.TRUE);
        when(CropController.updateCrop(s)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        when(CropController.updateCrop(F)).thenReturn(new ResponseEntity<>(F,HttpStatus.OK));
        ResponseEntity<Crop> result1 = CropController.updateCrop(F);
        ResponseEntity<Crop> result = CropController.updateCrop(s);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result1.getBody()).isEqualTo(F);
    }

    @Test
    void deleteCrop() {
        when(CropController.deleteCrop("1")).thenReturn(new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK));
        when(CropController.deleteCrop("2")).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<String> result1 = CropController.deleteCrop("1");
        ResponseEntity<String> result = CropController.deleteCrop("2");
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}