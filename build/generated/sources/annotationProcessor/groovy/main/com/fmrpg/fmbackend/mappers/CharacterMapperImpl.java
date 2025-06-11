package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.CharacterDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.enums.CharacterClass;
import com.fmrpg.fmbackend.enums.CharacterOrigin;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-06-11T02:43:00+0100",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.14.jar, environment: Java 21.0.7 (Oracle Corporation)"
)
@Component
public class CharacterMapperImpl implements CharacterMapper {

    @Override
    public CharacterEntity toEntity(CharacterDto characterDto) {
        if ( characterDto == null ) {
            return null;
        }

        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setName( characterDto.name() );
        if ( characterDto.characterClass() != null ) {
            characterEntity.setCharacterClass( Enum.valueOf( CharacterClass.class, characterDto.characterClass() ) );
        }
        if ( characterDto.characterOrigin() != null ) {
            characterEntity.setCharacterOrigin( Enum.valueOf( CharacterOrigin.class, characterDto.characterOrigin() ) );
        }
        characterEntity.setImageUrl( characterDto.imageUrl() );

        return characterEntity;
    }
}
