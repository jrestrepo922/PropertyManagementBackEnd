package com.example.propertymanagement.dto.propertydto;

import lombok.Data;

@Data
public class GetPropertiesDTO {

    private int id;

    private String street;

    private String zipcode;

    private String state;

    private int bedroomCount;

    private int bathroomCount;

    private String imgUrl;


}
