package com.cropdeal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;


@AllArgsConstructor
@Data
public class AuthenticationResponse implements Serializable {
    private final User userDetails;
    private final String jwt;

}