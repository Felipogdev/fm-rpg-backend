package feiticeiros.example.fmbackend.item.shield;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShieldMapper {

    ShieldDTO toDto(ShieldEntity shieldEntity);

    ShieldEntity toEntity(ShieldDTO shieldDTO);
}
