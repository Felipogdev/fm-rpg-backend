package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.OAuth2UserDto;
import com.fmrpg.fmbackend.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T05:02:22+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Arch Linux)"
)
@Component
public class OAuthMapperImpl implements OAuthMapper {

    @Override
    public User toUser(OAuth2UserDto oAuth2UserDto) {
        if ( oAuth2UserDto == null ) {
            return null;
        }

        User user = new User();

        user.setOauthId( oAuth2UserDto.oauthId() );
        user.setEmail( oAuth2UserDto.email() );
        user.setImageUrl( oAuth2UserDto.imageUrl() );

        return user;
    }
}
