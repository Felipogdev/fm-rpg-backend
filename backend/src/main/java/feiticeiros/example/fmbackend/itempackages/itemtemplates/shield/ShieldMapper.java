package feiticeiros.example.fmbackend.itempackages.itemtemplates.shield;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShieldMapper {

    ShieldDTO toDto(ShieldEntity shieldEntity);

    ShieldEntity toEntity(ShieldDTO shieldDTO);
}
