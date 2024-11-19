package com.user.usermanagment.dto;

import lombok.Data;

@Data
public class UserRegistrationDTO {

    private String username;
    private String email;
    private String password;
    private String role;  // This will be one of "ADMIN", "CLIENT", or "FARMER"

    // Getters and setters

    public Role getRoleEnum() {
        return Role.valueOf(role.toUpperCase()); // Converts role to enum
    }

    public enum Role {
        ADMIN, CLIENT, FARMER
    }
}
