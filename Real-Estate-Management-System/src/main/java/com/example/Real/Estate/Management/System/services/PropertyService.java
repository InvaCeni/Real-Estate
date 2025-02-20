package com.example.Real.Estate.Management.System.services;


import com.example.Real.Estate.Management.System.request.PropertyCreateRequest;
import com.example.Real.Estate.Management.System.request.PropertyGetQueryRequest;
import com.example.Real.Estate.Management.System.request.PropertyUpdateRequest;
import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepository;

    public Property createProperty(PropertyCreateRequest request) {
        Property property = new Property();

        if (request.getName() != null) {
            property.setName(request.getName());
        }

        if (request.getDescription() != null) {
            property.setDescription(request.getDescription());
        }

        if (request.getAddress() != null) {
            property.setAddress(request.getAddress());
        }

        if (request.getBedrooms() != null) {
            property.setBedrooms(request.getBedrooms());
        }

        if (request.getBathrooms() != null) {
            property.setBathrooms(request.getBathrooms());
        }

        if (request.getPrice() != null) {
            property.setPrice(request.getPrice());
        }

        if (request.isParking() != null) {
            property.setParking(request.isParking());
        }

        if (request.isFurnished() != null) {
            property.setFurnished(request.isFurnished());
        }

        if (request.getUserId() != null) {
            property.setUserId(request.getUserId());
        }

        if (request.getImageUrls() != null) {
            property.setImageUrls(request.getImageUrls());
        }


        return propertyRepository.save(property);

    }

    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    public Property updateProperty(Long id, PropertyUpdateRequest request) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Check if request attributes are not null before updating
        if (request.getName() != null) {
            property.setName(request.getName());
        }

        if (request.getDescription() != null) {
            property.setDescription(request.getDescription());
        }

        if (request.getAddress() != null) {
            property.setAddress(request.getAddress());
        }

        if (request.getBedrooms() != null) {
            property.setBedrooms(request.getBedrooms());
        }

        if (request.getBathrooms() != null) {
            property.setBathrooms(request.getBathrooms());
        }

        if (request.getPrice() != null) {
            property.setPrice(request.getPrice());
        }

        if (request.isParking() != null) {
            property.setParking(request.isParking());
        }

        if (request.isFurnished() != null) {
            property.setFurnished(request.isFurnished());
        }

        if (request.getUrls() != null) {
            property.setImageUrls(request.getUrls());
        }
        return propertyRepository.save(property);
    }

    public Property getProperty(Long id) {


        return propertyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Property not found"));
    }


    public Property[] searchAndFilterProperties(PropertyGetQueryRequest request, boolean[] furnishedValues, boolean[] parkingValues) {


        Sort sort = Sort.by(request.getOrder().equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC, request.getSort());
        int startIndex = Math.max(request.getStartIndex(), 0);
        int pageSize = Math.max(request.getLimit(), 1);


        int pageIndex = startIndex / pageSize;
        int pageOffset = startIndex % pageSize;

        Pageable pageable = PageRequest.of(pageIndex, pageSize, sort);

        Page<Property> pageResult = propertyRepository.findByNameContainingIgnoreCaseAndFurnishedInAndParkingIn(
                request.getSearchTerm(), furnishedValues, parkingValues, pageable
        );

        List<Property> property = pageResult.getContent().subList(pageOffset, pageResult.getContent().size());

        return property.toArray(new Property[0]);
    }
}