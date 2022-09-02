package com.example.propertymanagement.service;

import com.example.propertymanagement.dto.propertydto.CreatePropertyDTO;
import com.example.propertymanagement.dto.propertydto.GetPropertiesDTO;
import com.example.propertymanagement.dto.propertydto.GetPropertyDTO;
import com.example.propertymanagement.model.Property;
import com.example.propertymanagement.model.User;
import com.example.propertymanagement.repository.PropertyRepository;
import com.example.propertymanagement.repository.UserRepository;
import com.example.propertymanagement.utility.ServiceClassesUtility;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private ServiceClassesUtility serviceClassesUtility;


    public GetPropertyDTO createProperty(Integer id, CreatePropertyDTO createPropertyDTO){
        //find the user
        User user = serviceClassesUtility.findUser(id);
        //crate a property using the createPropertyDTO
        Property property = mapper.map(createPropertyDTO, Property.class);
        // create the bidirectional mapping
        // the user.addProperty takes care of assigning user to property
        user.addProperty(property);

        //We have cascadeType.ALL meaning if you save the user it will also save the property
        // only one save is needed
        this.userRepository.save(user);

        // turn the property into GetPropertyDTO
        GetPropertyDTO getPropertyDTO = mapper.map(property, GetPropertyDTO.class);
        return getPropertyDTO;

    }

    public List<GetPropertiesDTO> getProperties(Integer id){
        //find the user
        User user = serviceClassesUtility.findUser(id);

        //get the user's properties
        List<Property> properties = user.getProperties();

        // covert the properties into GetPropertiesDTO
        List<GetPropertiesDTO> getPropertiesDTO =
                properties.stream().map(property -> mapper.map(property, GetPropertiesDTO.class)).collect(Collectors.toList());

        return getPropertiesDTO;
    }

    public GetPropertyDTO getProperty(Integer id, Integer propertyId){
        // find the user
        User user = serviceClassesUtility.findUser(id);
        // find the property
        Property property = serviceClassesUtility.findUserProperty(user, propertyId);
        // convert property into a GetPropertyDTO
        GetPropertyDTO getPropertyDTO = mapper.map(property,GetPropertyDTO.class);

        return getPropertyDTO;
    }



}
