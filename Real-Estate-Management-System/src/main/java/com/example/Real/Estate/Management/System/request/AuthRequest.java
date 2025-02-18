package com.example.Real.Estate.Management.System.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

//@Getter
//@Setter
@AllArgsConstructor
public class AuthRequest  implements Serializable {

    public String email;
    public String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
