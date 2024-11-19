package com.user.usermanagment.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.usermanagment.dto.UserRegistrationDTO;
import com.user.usermanagment.dto.UserResponseDTO;
import com.user.usermanagment.entity.User;
import com.user.usermanagment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDTO registerUser(UserRegistrationDTO dto) {
        // Convert the role from string to enum
        User.Role role = User.Role.valueOf(dto.getRole().toUpperCase());

        // Create a new user instance
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));  // Encode the password
        user.setRole(role);

        // Save the user in the database
        userRepository.save(user);

        // Return the user response DTO
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name());
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        // Find user by id, throw an exception if not found
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Return the user response DTO
        return new UserResponseDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRole().name());
    }

    @Override
    public User findByUsername(String username) {
        // Find user by username
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
