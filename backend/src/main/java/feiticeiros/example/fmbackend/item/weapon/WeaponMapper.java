package feiticeiros.example.fmbackend.item.weapon;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses= ItemAbstractEntity.class)
public interface WeaponMapper {

    WeaponDTO toDto(WeaponEntity weaponEntity);

    WeaponEntity toEntity(WeaponDTO weaponDTO);
}
