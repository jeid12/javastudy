package com.user.usermanagment.services;

import com.user.usermanagment.dto.UserRegistrationDTO;
import com.user.usermanagment.dto.UserResponseDTO;
import com.user.usermanagment.entity.User;

public interface UserService {
 // Method to register a new user
    UserResponseDTO registerUser(UserRegistrationDTO dto);

    // Method to get user by id
    UserResponseDTO getUserById(Long id);

    // Method to get a user by username
    User findByUsername(String username);
}
