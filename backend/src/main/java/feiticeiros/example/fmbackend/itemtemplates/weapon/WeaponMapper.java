package feiticeiros.example.fmbackend.itemtemplates.weapon;

import feiticeiros.example.fmbackend.itemtemplates.ItemTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses= ItemTemplateEntity.class)
public interface WeaponMapper {

    WeaponDTO toDto(WeaponEntity weaponEntity);

    WeaponEntity toEntity(WeaponDTO weaponDTO);
}
