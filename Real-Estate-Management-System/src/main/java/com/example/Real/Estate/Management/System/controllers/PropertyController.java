package com.example.Real.Estate.Management.System.controllers;

import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.request.PropertyRequest;
import com.example.Real.Estate.Management.System.services.PropertyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {
    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public List<Property> getAllProperties() {
        return propertyService.getAllProperties();
    }

    //TODO: get proprty by location


    //TODO 2: create property


    //TODO 3: UPDATE PROPERTY

    //TODO: DELETE PROPERTY
}

//This class is a REST controller that handles HTTP requests related to property listings.
// It interacts with the PropertyService to fetch the property data.


