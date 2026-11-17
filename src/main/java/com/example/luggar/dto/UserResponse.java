package com.example.luggar.dto;

import com.example.luggar.enums.Role;

public record UserResponse (
    Long id,
    String firstName,
    String lastName,
    String email,
    Role role

) {}
