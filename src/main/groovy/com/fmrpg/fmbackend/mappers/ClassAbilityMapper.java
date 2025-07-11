package com.fmrpg.fmbackend.mappers;

import com.fmrpg.fmbackend.dtos.ClassAbilityResponseDto;
import com.fmrpg.fmbackend.entities.characterpkg.ClassAbility;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClassAbilityMapper {
    ClassAbilityResponseDto toResponse(ClassAbility classAbility);

}
