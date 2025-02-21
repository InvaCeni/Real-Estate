package com.example.Real.Estate.Management.System.controllers;

import com.example.Real.Estate.Management.System.utils.JwtUtil;
import com.example.Real.Estate.Management.System.request.ApiResponse;
import com.example.Real.Estate.Management.System.request.UserUpdateRequest;

import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.models.User;
import com.example.Real.Estate.Management.System.services.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtTokenProvider;

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id,
                                                  @RequestBody UserUpdateRequest request,
                                                  @CookieValue(name = "access_token") String accessTokenCookie) {
        try {
            if (jwtTokenProvider.isAdmin(accessTokenCookie)) {
                User updatedUser = userService.updateUser(id, request);
                return ResponseEntity.ok(new ApiResponse(true, updatedUser, null, "User updated successfully"));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null, "Token is not valid"));
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null, "Token is not valid"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null, "Error updating user"));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id,
                                                  @CookieValue(name = "access_token") String accessTokenCookie) {
        try {
            if(jwtTokenProvider.isAdmin(accessTokenCookie)){
                 userService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse(true, null, null, "User deleted successfully"));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null,null, "Token is not valid"));
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null,null, "Token is not valid"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null,"Error deleting user"));
        }
    }

    @GetMapping("/properties/{id}")
    public ResponseEntity<ApiResponse> getUserProperty(@PathVariable Long id) {
        try {
            List<Property> userProperty = userService.getUserProperty(id);
            return ResponseEntity.ok(new ApiResponse(true, null,  userProperty, "User property fetched successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null, "Error fetching user property"));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable Long id,
                                               @CookieValue(name = "access_token") String accessTokenCookie) {
        try {
            if(jwtTokenProvider.isTokenValid(accessTokenCookie)) {
                User user = userService.getUser(id);
                return ResponseEntity.ok(new ApiResponse(true, user, null ,"User fetched successfully"));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null,  "Token is not valid"));
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ApiResponse(false, null, null,  "Token is not valid"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(false, null, null,  "Error fetching user"));
        }
    }
}