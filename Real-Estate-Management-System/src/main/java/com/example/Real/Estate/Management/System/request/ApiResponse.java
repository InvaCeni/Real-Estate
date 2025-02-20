package com.example.Real.Estate.Management.System.request;
import com.example.Real.Estate.Management.System.models.Property;
import com.example.Real.Estate.Management.System.models.User;

import jakarta.servlet.http.Cookie;
import java.util.List;

public class ApiResponse {

    private final boolean success;
    private final User user;
    private final List<Property> property;
    private final String message;


    public ApiResponse(boolean success, User user, List<Property> property, String message) {
        this.success = success;
        this.user = user;
        this.property = property;
        this.message = message;

    }

    public boolean isSuccess() {
        return success;
    }

    public User getUser() {
        return user;
    }

    public List<Property> getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }


}