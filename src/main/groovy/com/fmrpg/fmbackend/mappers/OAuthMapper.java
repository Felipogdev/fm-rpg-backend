package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.OAuth2UserDto;
import com.fmrpg.fmbackend.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OAuthMapper {

    User toUser(OAuth2UserDto oAuth2UserDto);
}
