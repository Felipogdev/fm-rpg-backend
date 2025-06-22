package com.fmrpg.fmbackend.dtos;

public record OAuth2UserDto(
        String email,
        String imageUrl,
        String googleId
) {
}
