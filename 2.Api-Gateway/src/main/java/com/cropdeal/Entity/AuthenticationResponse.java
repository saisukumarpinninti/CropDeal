package com.cropdeal.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;


@AllArgsConstructor
@Data
public class AuthenticationResponse implements Serializable {
    private final String jwt;

}