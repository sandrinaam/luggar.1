package com.example.luggar.user.dto;

import com.example.luggar.user.enums.Role;

public record UserResponse (
    Long id,
    String firstName,
    String lastName,
    String email,
    Role role

) {}
