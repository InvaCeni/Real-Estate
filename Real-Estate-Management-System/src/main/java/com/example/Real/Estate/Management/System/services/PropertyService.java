package com.example.Real.Estate.Management.System.services;

import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.repositories.PropertyRepository;
import com.example.Real.Estate.Management.System.request.PropertyRequest;
import com.example.Real.Estate.Management.System.request.PropertySearchRequest;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }

    public Property createProperty(PropertyRequest propertyRequest) {
        if (propertyRequest == null) {
            throw new ServiceException("Null request");
        }

        Property property = new Property();
        property.setName(propertyRequest.getName());
        property.setLocation(propertyRequest.getLocation());
        property.setPrice(propertyRequest.getPrice());
        property.setAgentId(propertyRequest.getAgentId());
        property.setDescription(propertyRequest.getDescription());
        property.setStatus(propertyRequest.getStatus());
        property.setImageUrl(propertyRequest.getImageUrl());
        property.setCategory(propertyRequest.getCategory());

        return propertyRepository.saveAndFlush(property);
    }

    public Property updateProperty(Long propertyId, PropertyRequest propertyRequest) {
        Optional<Property> existingProperty = propertyRepository.findById(propertyId);

        if (!existingProperty.isPresent()) {
            throw new ServiceException("Property does not exist");
        }

        Property responseProperty = existingProperty.get();

        if (propertyRequest == null) {
            throw new ServiceException("Null request");
        }

        if (propertyRequest.getName() != null && !propertyRequest.getName().isEmpty()) {
            responseProperty.setName(propertyRequest.getName());
        }
        if (propertyRequest.getLocation() != null && !propertyRequest.getLocation().isEmpty()) {
            responseProperty.setLocation(propertyRequest.getLocation());
        }
        if (propertyRequest.getPrice() != 0.0) {
            responseProperty.setPrice(propertyRequest.getPrice());
        }
        if (propertyRequest.getCategory() != null && !propertyRequest.getCategory().isEmpty()) {
            responseProperty.setCategory(propertyRequest.getCategory());
        }
        if (propertyRequest.getAgentId() != null) {
            responseProperty.setAgentId(propertyRequest.getAgentId());
        }
        if (propertyRequest.getDescription() != null && !propertyRequest.getDescription().isEmpty()) {
            responseProperty.setDescription(propertyRequest.getDescription());
        }

        if (propertyRequest.getStatus() != null && !propertyRequest.getStatus().isEmpty()) {
            responseProperty.setStatus(propertyRequest.getStatus());
        }
        if (propertyRequest.getImageUrl() != null && !propertyRequest.getImageUrl().isEmpty()) {
            responseProperty.setImageUrl(propertyRequest.getImageUrl());
        }
        if (propertyRequest.getCategory() != null && !propertyRequest.getCategory().isEmpty()) {
            responseProperty.setCategory(propertyRequest.getCategory());
        }

        propertyRepository.save(responseProperty);
        return responseProperty;
    }

    public int deleteProperty(Long propertyId) {
        Optional<Property> existingProperty = propertyRepository.findById(propertyId);

        if (!existingProperty.isPresent()) {
            throw new ServiceException("Property does not exist");
        }

        try {
            propertyRepository.deleteById(propertyId);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Property> getPropertiesByLocation(String location) {
        return propertyRepository.findByLocation(location);
    }

    public List<Property> searchProperties(PropertySearchRequest searchRequest) {
        return propertyRepository.searchProperties(
                searchRequest.getLocation(),
                searchRequest.getMinPrice(),
                searchRequest.getMaxPrice(),
                searchRequest.getPropertyType(),
                searchRequest.getIsAvailable()
        );
    }

}
