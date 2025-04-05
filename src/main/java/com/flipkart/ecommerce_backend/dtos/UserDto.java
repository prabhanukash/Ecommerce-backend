package com.flipkart.ecommerce_backend.dtos;


import lombok.Builder;

import java.util.Set;
import java.util.UUID;

@Builder
public record UserDto(
    UUID id,
    String username,
    String email,
    String firstName,
    String lastName,
    Set<String> roles,
    boolean isEnabled,
    boolean isEmailVerified
//    String phoneNumber,
//    String address,
//    String city,
//    String state,
//    String country,
//    String zipCode,
//    boolean isAdmin
) {
}
