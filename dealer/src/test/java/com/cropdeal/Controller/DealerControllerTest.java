package com.cropdeal.Controller;

import com.cropdeal.Repository.DealerRepository;
import com.cropdeal.entity.Dealer;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DealerControllerTest {

    @InjectMocks
    public DealerController DealerController= Mockito.mock(DealerController.class);

    @Mock
    public DealerRepository DealerRepository;

    //Creating A Dealer
    String[] a ;
    Dealer F  = new Dealer("1","sai","Sukumar","sa",new Date(),"sai@Mail.com"
            ,"s", 90L,"bac",TRUE,a );

    public ResponseEntity<List<Dealer>> getlist(){
        // Creating A Dealer List
        List<Dealer> testDealers = new ArrayList<Dealer>();
        testDealers.add(F);      // adding a Dealer
        Dealer s = new Dealer("2","sai","Sukumar","sa",new Date(),"sai@Mail.com","s",
                90L,"bac",TRUE,a);
        testDealers.add(s);
        return new ResponseEntity<>(testDealers, HttpStatus.OK);    //generating & Returning Response
    }
    @Test
    void getDealers() {
        ResponseEntity<List<Dealer>> Response = getlist() ;
        when(DealerController.getDealers()).thenReturn(Response);               //  Setting The Response
        // Getting The Response
        ResponseEntity<List<Dealer>>  result = DealerController.getDealers();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((getlist().getBody().toString()));
    }

    @Test
    void findById() {
        ResponseEntity<List<Dealer>> Response = getlist() ;
        List<Dealer> f = getlist().getBody();
        Dealer f1 = f.stream()
                .filter(Dealer -> "2".equals(Dealer.getId()))
                .findAny().orElse(null);
        assertThat(Response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(f1).isNotSameAs(F);
        assertThat(f1.getId()).isNotNull();
    }

    @Test
    void addDealer() {
        Dealer s = new Dealer("2","sai","Sukumar","sa",new Date(),"sai@Mail.com"
                ,"s", 90L,"bac",TRUE,a);
        when(DealerController.addDealer(s)).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
        when(DealerController.addDealer(F)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        ResponseEntity<Dealer> result1 = DealerController.addDealer(F);
        ResponseEntity<Dealer> result = DealerController.addDealer(s);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((s.toString()));
    }

    @Test
    void updateDealer() {
        Dealer s = new Dealer("2","sai","Sukumar","sssasa",new Date(),"sairam@Mail.com"
                ,"s", 90L,"bac",FALSE,a);
        when(DealerController.updateDealer(s)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        when(DealerController.updateDealer(F)).thenReturn(new ResponseEntity<>(F,HttpStatus.OK));
        ResponseEntity<Dealer> result1 = DealerController.updateDealer(F);
        ResponseEntity<Dealer> result = DealerController.updateDealer(s);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result1.getBody()).isEqualTo(F);
    }

    @Test
    void deleteDealer() {
        when(DealerController.deleteDealer("1")).thenReturn(new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK));
        when(DealerController.deleteDealer("2")).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<String> result1 = DealerController.deleteDealer("1");
        ResponseEntity<String> result = DealerController.deleteDealer("2");
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void dealerExits() {
        ResponseEntity<List<Dealer>> result= getlist();
        List<Dealer> f = getlist().getBody();
        Dealer f1 = f.stream()
                .filter(Dealer -> "1".equals(Dealer.getId()))
                .findAny().orElse(null);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(f1.toString()).isEqualTo(F.toString());
        assertThat(f1.getId()).isEqualTo("1");
    }
}