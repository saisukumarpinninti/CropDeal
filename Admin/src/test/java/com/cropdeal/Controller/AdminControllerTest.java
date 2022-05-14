package com.cropdeal.Controller;

import com.cropdeal.Repository.AdminRepository;
import com.cropdeal.entity.Admin;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Boolean.TRUE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AdminControllerTest {

    @InjectMocks
    public AdminController AdminController= Mockito.mock(AdminController.class);

    @Mock
    public AdminRepository AdminRepository;
    
    Admin F =new Admin("1","Sukumar","ll","sai@mail.com");
    
    public ResponseEntity<List<Admin>> getlist(){
        // Creating A Admin List
        List<Admin> testAdmins = new ArrayList<Admin>();
        testAdmins.add(F);      // adding a Admin
        Admin s = new Admin("2","Sukumar","ll","sai@mail.com");
        testAdmins.add(s);
        return new ResponseEntity<>(testAdmins, HttpStatus.OK);    //generating & Returning Response
    }
    @Test
    void findById() {
        ResponseEntity<List<Admin>> Response = getlist() ;
        List<Admin> f = getlist().getBody();
        Admin f1 = f.stream()
                .filter(Admin -> "2".equals(Admin.getId()))
                .findAny().orElse(null);
        assertThat(Response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(f1).isNotSameAs(F);
        assertThat(f1.getId()).isNotNull();
    
    }

    @Test
    void addAdmin() {
        Admin s = new Admin("2","Sukumar","ll","sai@mail.com");
        when(AdminController.addAdmin(s)).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
        when(AdminController.addAdmin(F)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        ResponseEntity<Admin> result1 = AdminController.addAdmin(F);
        ResponseEntity<Admin> result = AdminController.addAdmin(s);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((s.toString()));
    }

    @Test
    void updateAdmin() {
        Admin s = new Admin("2","Sukumar","ll","sai@mail.com");
        when(AdminController.addAdmin(s)).thenReturn(new ResponseEntity<>(s,HttpStatus.OK));
        when(AdminController.addAdmin(F)).thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
        ResponseEntity<Admin> result1 = AdminController.addAdmin(F);
        ResponseEntity<Admin> result = AdminController.addAdmin(s);
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody().toString()).isEqualTo((s.toString()));
        
    }

    @Test
    void deleteAdmin() {
        when(AdminController.deleteAdmin("1")).thenReturn(new ResponseEntity<>("Deleted SuccessFully",HttpStatus.OK));
        when(AdminController.deleteAdmin("2")).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
        ResponseEntity<String> result1 = AdminController.deleteAdmin("1");
        ResponseEntity<String> result = AdminController.deleteAdmin("2");
        assertThat(result1.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}