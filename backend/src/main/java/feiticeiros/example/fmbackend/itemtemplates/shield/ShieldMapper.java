package feiticeiros.example.fmbackend.itemtemplates.shield;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShieldMapper {

    ShieldDTO toDto(ShieldEntity shieldEntity);

    ShieldEntity toEntity(ShieldDTO shieldDTO);
}
