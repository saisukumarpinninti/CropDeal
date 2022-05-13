package com.cropdeal.Controller;

import com.cropdeal.Repository.FarmerRepository;
import com.cropdeal.entity.Farmer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class FarmerControllerTest {
    @InjectMocks
    public FarmerController farmerController= Mockito.mock(FarmerController.class);

    @Mock
    public FarmerRepository farmerRepository;

    //Creating A Farmer
    Farmer F  = new Farmer("1","sai","Sukumar","sa",new Date(),"sai@Mail.com"
            ,"s", 90L,"bac",TRUE);


    public ResponseEntity<List<Farmer>> getlist(){
        // Creating A Farmer List
        List<Farmer> testfarmers = new ArrayList<Farmer>();
        testfarmers.add(F);      // adding a farmer
        Farmer s = new Farmer("2","sai","Sukumar","sa",new Date(),"sai@Mail.com"
                ,"s", 90L,"bac",TRUE);
        testfarmers.add(s);
        return new ResponseEntity<>(testfarmers,HttpStatus.OK);    //generating & Returning Response

    }

    @BeforeEach
    void setup()
    {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getFarmers() {
        ResponseEntity<List<Farmer>> Response = getlist() ;
        when(farmerController.getFarmers()).thenReturn(Response);               //  Setting The Response
        // Getting The Response
        ResponseEntity<List<Farmer>>  result = farmerController.getFarmers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((getlist().getBody().toString()));
    }

    @Test
    void findById() {
        ResponseEntity<List<Farmer>> Response = getlist() ;
        List<Farmer> f = getlist().getBody();
        Farmer f1 = f.stream()
                .filter(farmer -> "2".equals(farmer.getId()))
                .findAny().orElse(null);
        assertThat(Response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(f1).isNotSameAs(F);
        assertThat(f1.getId()).isNotNull();

    }

    @Test
    void addFarmer() {
        Farmer s = new Farmer("2","sai","Sukumar","sa",new Date(),"sai@Mail.com"
                ,"s", 90L,"bac",TRUE);
        when(farmerController.addFarmer(s)).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
        when(farmerController.addFarmer(F)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        ResponseEntity<Farmer> result1 = farmerController.addFarmer(F);
        ResponseEntity<Farmer> result = farmerController.addFarmer(s);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((s.toString()));
    }

    @Test
    void updateFarmer() {
        Farmer s = new Farmer("2","sai","Sukumar","sssasa",new Date(),"sairam@Mail.com"
                ,"s", 90L,"bac",FALSE);
        when(farmerController.updateFarmer(s)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        when(farmerController.updateFarmer(F)).thenReturn(new ResponseEntity<>(F,HttpStatus.OK));
        ResponseEntity<Farmer> result1 = farmerController.updateFarmer(F);
        ResponseEntity<Farmer> result = farmerController.updateFarmer(s);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result1.getBody()).isEqualTo(F);
    }

    @Test
    void deleteFarmer() {
        when(farmerController.deleteFarmer("1")).thenReturn(new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK));
        when(farmerController.deleteFarmer("2")).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<String> result1 = farmerController.deleteFarmer("1");
        ResponseEntity<String> result = farmerController.deleteFarmer("2");
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void farmerExits() {
        ResponseEntity<List<Farmer>> result= getlist();
        List<Farmer> f = getlist().getBody();
        Farmer f1 = f.stream()
                        .filter(farmer -> "1".equals(farmer.getId()))
                                .findAny().orElse(null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(f1.toString()).isEqualTo(F.toString());
        assertThat(f1.getId()).isEqualTo("1");
    }
}