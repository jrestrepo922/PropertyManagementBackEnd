package com.example.propertymanagement.controller;

import com.example.propertymanagement.dto.userdto.CreateUserDTO;
import com.example.propertymanagement.dto.userdto.GetUserDTO;
import com.example.propertymanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")

public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping
    public GetUserDTO createUser(@RequestBody CreateUserDTO createUserDTO){
        return this.userService.createUser(createUserDTO);
    }

    @GetMapping("/{id}")
    public GetUserDTO getUser(@PathVariable Integer id){
        return this.userService.getUser(id);
    }

    // will need to get erase once we enable security
    @GetMapping("/validate")
    public GetUserDTO validateUser(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password){
        return this.userService.validateUser(username,password);
    }

}
