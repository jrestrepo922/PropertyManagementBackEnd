package com.example.propertymanagement.controller;

import com.example.propertymanagement.dto.security.AuthRequest;
import com.example.propertymanagement.dto.security.AuthResponse;
import com.example.propertymanagement.dto.security.CreateUserDTO;
import com.example.propertymanagement.dto.security.GetUserDTO;
import com.example.propertymanagement.service.UserService;
import com.example.propertymanagement.utility.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping("/users")

public class UserController {

    @Autowired
    private  UserService userService;


    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/authenticate/signup")
    public GetUserDTO createUser(@RequestBody CreateUserDTO createUserDTO){
        return this.userService.createUser(createUserDTO);
    }

    @GetMapping("/users/{id}")
    public GetUserDTO getUser(@PathVariable Integer id){
        return this.userService.getUser(id);
    }

    // will need to get erase once we enable security
    @PostMapping("/authenticate/login")
    public AuthResponse validateUser(@RequestBody AuthRequest authRequest) throws Exception {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username/password");
        }
        String username = authRequest.getUsername();
        String token = jwtUtil.generateToken(username);
        Integer id = this.userService.getUserByUsername(username).getId();

        return new AuthResponse(id, username,token);


    }

}
