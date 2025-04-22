package feiticeiros.example.fmbackend.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface UserMapper {


    User toEntity(UserDTO dto);

    @Mapping(target = "password", ignore = true)
    UserDTO toDTO(User user);
}
