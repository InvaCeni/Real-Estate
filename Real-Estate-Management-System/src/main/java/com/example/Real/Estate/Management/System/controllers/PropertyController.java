package com.example.Real.Estate.Management.System.controllers;

import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.request.PropertyRequest;
import com.example.Real.Estate.Management.System.services.PropertyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Property>> getPropertiesByLocation(@PathVariable String location) {
        List<Property> properties = propertyService.getPropertiesByLocation(location);
        return ResponseEntity.ok(properties);
    }


    //TODO 2: create property

    @PostMapping("/create")
    public ResponseEntity<?> createProperty(@RequestBody PropertyRequest propertyRequest) {
        Property property = propertyService.createProperty(propertyRequest);
        return ResponseEntity.ok(property);
    }


    //TODO 3: UPDATE PROPERTY



    //TODO: DELETE PROPERTY
}



