package com.example.Real.Estate.Management.System.controllers;
import com.example.Real.Estate.Management.System.utils.JwtUtil;
import com.example.Real.Estate.Management.System.request.ApiResponse;
import com.example.Real.Estate.Management.System.request.PropertyCreateRequest;
import com.example.Real.Estate.Management.System.request.PropertyGetQueryRequest;
import com.example.Real.Estate.Management.System.request.PropertyUpdateRequest;
import com.example.Real.Estate.Management.System.models.Property;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Real.Estate.Management.System.services.PropertyService;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private JwtUtil jwtTokenProvider;

    private static final Logger logger = LoggerFactory.getLogger(PropertyController.class);

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProperty(@RequestBody PropertyCreateRequest request,
                                                      @CookieValue(name = "access_token") String accessTokenCookie) {
        try {
            if (jwtTokenProvider.isAdmin(accessTokenCookie) || jwtTokenProvider.isAgent(accessTokenCookie)) {
                Property createdProperty = propertyService.createProperty(request);
                return ResponseEntity.ok(new ApiResponse(true, null, List.of(createdProperty), "Property created successfully"));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null, "Token is not valid"));
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null, "Token is not valid"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null, "Error creating property"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteProperty(@PathVariable Long id,
                                                      @CookieValue(name = "access_token") String accessTokenCookie) {
        try {
            if (jwtTokenProvider.isAdmin(accessTokenCookie) || jwtTokenProvider.isAgent(accessTokenCookie)) {
                propertyService.deleteProperty(id);
                return ResponseEntity.ok(new ApiResponse(true, null, null, "Property deleted successfully"));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null, "Token is not valid"));
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null, "Token is not valid"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null, "Error deleting property"));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateProperty(@PathVariable Long id,
                                                      @RequestBody PropertyUpdateRequest request,
                                                      @CookieValue(name = "access_token") String accessTokenCookie) {
        try {
            if (jwtTokenProvider.isAdmin(accessTokenCookie) || jwtTokenProvider.isAgent(accessTokenCookie)) {
                Property updatedProperty = propertyService.updateProperty(id, request);
                return ResponseEntity.ok(new ApiResponse(true, null, List.of(updatedProperty), "Property updated successfully"));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null, "Token is not valid"));
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null, "Token is not valid"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null, "Error updating property"));
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponse> getProperty(@PathVariable Long id) {
        try {
            Property property = propertyService.getProperty(id);
            return ResponseEntity.ok(new ApiResponse(true,null, List.of(property), "Property retrieved successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null, "Error getting property"));
        }
    }

    @GetMapping("/get")
    public ResponseEntity<?> searchAndFilterProperties(@RequestParam(required = false) String searchTerm,
                                                     @RequestParam(required = false) Boolean furnished,
                                                     @RequestParam(required = false) Boolean parking,
                                                     @RequestParam(required = false) String sort,
                                                     @RequestParam(required = false) String order,
                                                     @RequestParam(required = false) Integer limit,
                                                     @RequestParam(required = false) Integer startIndex
    ) {

        PropertyGetQueryRequest request = new PropertyGetQueryRequest();

        try {

            boolean[] furnishedValues;
            boolean[] parkingValues;

            if (limit == null) {
                request.setLimit(9);
            } else {
                request.setLimit(limit);
            }

            if (startIndex == null || startIndex < 0) {
                request.setStartIndex(0);
            } else {
                request.setStartIndex(startIndex);
            }

            if (furnished == null || !furnished) {
                furnishedValues = new boolean[]{true, false};
            } else {
                furnishedValues = new boolean[]{true};
            }

            if (parking == null || !parking) {
                parkingValues = new boolean[]{true, false};
            } else {
                parkingValues = new boolean[]{true};
            }

            if (searchTerm == null) {
                request.setSearchTerm("");
            } else {
                request.setSearchTerm(searchTerm);
            }

            if (sort == null) {
                request.setSort("createdAt");
            } else {
                request.setSort(sort);
            }

            if (order == null) {
                request.setOrder("desc");
            } else {
                request.setOrder(order);
            }

            System.out.println("#######################");
            System.out.println("request is "  + request.toString());

            System.out.println("#######################");


            Property[] searchResults = propertyService.searchAndFilterProperties(request, furnishedValues, parkingValues);
            return ResponseEntity.ok(new ApiResponse(true, null,  List.of(searchResults), "Property retrieved successfully"));

        } catch (Exception e) {
            logger.error("Error searching and filtering property", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null,  "Error searching and filtering property"));
        }
    }
}