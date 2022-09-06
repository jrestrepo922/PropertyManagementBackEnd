package com.example.propertymanagement.dto.security;
import lombok.Data;


@Data
// body being pass down to the controller
public class CreateUserDTO {

    private String username;

    private String password;
}
