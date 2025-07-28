package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.FeatsResponseDto;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterFeat;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CharacterFeatsMapper {
    FeatsResponseDto toResponse(CharacterFeat characterFeat);
}
