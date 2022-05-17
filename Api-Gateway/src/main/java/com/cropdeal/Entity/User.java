package com.cropdeal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "User")
@Data
public class User {
    @Id
    private String id;
    private String email;
    private String name;
    private String password;
    private String Role;
}
