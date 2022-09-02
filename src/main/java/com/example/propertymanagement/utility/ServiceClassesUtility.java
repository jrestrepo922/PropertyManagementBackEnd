package com.example.propertymanagement.utility;

import com.example.propertymanagement.dto.propertydto.GetPropertiesDTO;
import com.example.propertymanagement.model.Property;
import com.example.propertymanagement.model.User;
import com.example.propertymanagement.repository.PropertyRepository;
import com.example.propertymanagement.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceClassesUtility {
    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    public  User  findUser(Integer id){
        User user = this.userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return user;
    }

    public Property findUserProperty(User user, Integer propertyId){
        //get the user's properties
        List<Property> properties = user.getProperties();

        //find the single property using propertyId
        List<Property> property = properties.stream().filter(propertyStream -> propertyStream.getId() == propertyId).collect(Collectors.toList());

        if(property.size() == 0){
            new ResponseStatusException(HttpStatus.NOT_FOUND, "property not found");
        }

        return property.get(0);
    }

}
