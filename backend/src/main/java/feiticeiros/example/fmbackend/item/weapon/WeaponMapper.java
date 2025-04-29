package feiticeiros.example.fmbackend.item.weapon;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeaponMapper {

    WeaponDTO toDto(WeaponEntity weaponEntity);

    WeaponEntity toEntity(WeaponDTO weaponDTO);
}
