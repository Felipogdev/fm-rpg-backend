package feiticeiros.example.fmbackend.user;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);

    @Mappings({@Mapping(target = "password", ignore = true)})
    User toDTO(User user);
}
