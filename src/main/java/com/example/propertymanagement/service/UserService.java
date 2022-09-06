package com.example.propertymanagement.service;

import com.example.propertymanagement.dto.security.CreateUserDTO;
import com.example.propertymanagement.dto.security.GetUserDTO;
import com.example.propertymanagement.model.User;
import com.example.propertymanagement.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public GetUserDTO createUser(CreateUserDTO createUserDTO){
        // create a User mapping the data from the DTO
        User user = mapper.map(createUserDTO, User.class);

        // save user to repository
        user = this.userRepository.save(user);

        // change the user to a GetUserDTO
        return mapper.map(user, GetUserDTO.class);
    }

    public  GetUserDTO getUser(Integer id){
        // find the user in the repository
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        GetUserDTO getUserDTO = mapper.map(user, GetUserDTO.class);
        return getUserDTO;
    }

    public  GetUserDTO getUserByUsername(String username){
        User user = this.userRepository.findByUsername(username)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        GetUserDTO getUserDTO = mapper.map(user, GetUserDTO.class);
        return getUserDTO;
    }




}
