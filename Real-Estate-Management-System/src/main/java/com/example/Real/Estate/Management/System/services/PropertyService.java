package com.example.Real.Estate.Management.System.services;

import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.repositories.PropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

}


