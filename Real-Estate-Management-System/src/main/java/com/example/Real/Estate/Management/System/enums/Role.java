package com.example.Real.Estate.Management.System.enums;

import com.example.Real.Estate.Management.System.models.User;

public enum Role {

    ADMIN("ADMIN"),AGENT("AGENT"), GUEST("GUEST");

    private String name;

    Role(String name){
        this.name = name;
    }

    String getNameByEnum(Role role){
        return role.name;
    }
}
