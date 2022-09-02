package com.example.propertymanagement.dto.userdto;
import lombok.Data;


@Data
// body being pass down to the controller
public class CreateUserDTO {

    private String username;

    private String password;
}
