package feiticeiros.example.fmbackend.character;

import feiticeiros.example.fmbackend.user.UserMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = UserMapperHelper.class)
public interface CharacterMapper {

    CharacterDTO toDto(CharacterEntity characterEntity);

    @Mapping(target = "user", source = "userId", qualifiedByName = "mapUserIdToUser")
    CharacterEntity toEntity(CharacterDTO dto);
}

