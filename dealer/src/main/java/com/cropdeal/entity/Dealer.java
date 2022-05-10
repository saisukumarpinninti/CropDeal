package com.cropdeal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * This is an Entity Class For Dealer,
 *
 * The id filed is the Mongodb Id and the Dealer Id,
 *
 * the firstname , lastname , password , dob , email , address ,  MobileNumber, PaymentInfo are the attributes of the Dealer class,
 *
 * Status Represents the Status of the Dealer as Active Or Inactive ,
 *
 * SEQUENCE_Name is sequence name that the Class SequenceGenerator Uses to Get Sequence .
 */
@Document(collection = "Dealer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Dealer {
    @Transient
    private static final String SEQUENCE_Name="Dealer_Sequence";
    @MongoId
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private Date dob;
    private String email;
    private String address;
    private Long mobileNumber;
    private String paymentInfo;
    private Boolean status;
    private  String[] addons;

}
