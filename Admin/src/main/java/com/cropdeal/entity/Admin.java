package com.cropdeal.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

/*
 * This is an Entity Class For Farmer,
 *
 * @Param : Id - The id field is the Mongodb id and refers to AdminId .
 *
 * @Param : name - is the Name of the Admin ,
 *
 * @Param :  password - is the password of the admin,
 *
 * @Param : email - is the password of the admin
 */

@Document(collection = "Admin")
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Data

public class Admin {
    @MongoId
    private String id;
    private String name;
    private String password;
    private String email;

}
