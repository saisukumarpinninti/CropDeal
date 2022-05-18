package com.cropdeal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
@Data
public class User {

    @MongoId
    private String id;
    private String email;
    private String name;
    private String password;
    private String Role;
}
