package com.example.propertymanagement.dto.userdto;

import com.example.propertymanagement.dto.propertydto.GetPropertiesDTO;
import com.example.propertymanagement.model.Property;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetUserDTO {

    private int id;

    private String username;


    private List<GetPropertiesDTO> properties = new ArrayList<GetPropertiesDTO>();
}
