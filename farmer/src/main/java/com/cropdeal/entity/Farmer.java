package com.cropdeal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.util.Date;


/*
* This is an Entity Class For Farmer,
*
* The id filed is the Mongodb Id and the Farmer Id,
*
* the firstname , lastname , password , dob , email , address ,  MobileNumber, PaymentInfo are the attributes of the farmer class,
*
* Status Represents the Status of the Farmer as Active Or Inactive ,
*
* SEQUENCE_Name is sequence name that the Class SequenceGenerator Uses to Get Sequence .
*/
@Document(collection = "Farmer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Farmer {
    @Transient
    private static final String SEQUENCE_Name="Farmer_Sequence";
    @MongoId
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private Date dob;
    private String email;
    private String address;
    private Long MobileNumber;
    private String PaymentInfo;
    private Boolean Status;

}
