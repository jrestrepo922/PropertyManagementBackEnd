package com.example.propertymanagement.dto.propertydto;

import com.example.propertymanagement.model.Tenant;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetPropertyDTO {

    private int id;

    private String street;

    private String zipcode;

    private String state;

    private int bedroomCount;

    private int bathroomCount;

    private String imgUrl;

    private List<Tenant> tenants = new ArrayList<Tenant>();
}
