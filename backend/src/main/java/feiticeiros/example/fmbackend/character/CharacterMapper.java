package feiticeiros.example.fmbackend.character;

import org.mapstruct.Mapper;

@Mapper
public interface CharacterMapper {
    CharacterDTO toDto(CharacterEntity characterEntity);
    CharacterEntity toEntity(CharacterDTO dto);   
    }

