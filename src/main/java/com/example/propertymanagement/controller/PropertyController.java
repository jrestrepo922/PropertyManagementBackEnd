package com.example.propertymanagement.controller;

import com.example.propertymanagement.dto.propertydto.CreatePropertyDTO;
import com.example.propertymanagement.dto.propertydto.GetPropertiesDTO;
import com.example.propertymanagement.dto.propertydto.GetPropertyDTO;
import com.example.propertymanagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/properties")
@CrossOrigin(origins = "http://localhost:4200")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping
    public GetPropertyDTO createProperty(
            @PathVariable(name = "id") Integer id,
            @RequestBody CreatePropertyDTO createPropertyDTO)
    {
      return this.propertyService.createProperty(id, createPropertyDTO);
    }

    @GetMapping
    public List<GetPropertiesDTO> getProperties(@PathVariable(name = "id") Integer id){
        return this.propertyService.getProperties(id);
    }

    @GetMapping("/{propertyId}")
    public GetPropertyDTO getProperty(@PathVariable(name = "id") Integer id, @PathVariable(name = "propertyId") Integer propertyId){
        return this.propertyService.getProperty(id, propertyId);
    }
}
