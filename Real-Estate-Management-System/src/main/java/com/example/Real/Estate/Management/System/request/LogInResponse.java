package com.example.Real.Estate.Management.System.request;

import com.example.Real.Estate.Management.System.models.User;
import jakarta.servlet.http.Cookie;

public record LogInResponse(boolean success, String message, User user, Cookie cookie) {

}