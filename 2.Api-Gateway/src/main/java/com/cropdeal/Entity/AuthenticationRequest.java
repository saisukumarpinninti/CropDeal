package com.cropdeal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class AuthenticationRequest implements Serializable {
    private String username;
    private String password;
}