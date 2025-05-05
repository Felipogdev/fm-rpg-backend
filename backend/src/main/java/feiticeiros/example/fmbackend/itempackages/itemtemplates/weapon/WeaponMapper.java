package feiticeiros.example.fmbackend.itempackages.itemtemplates.weapon;

import feiticeiros.example.fmbackend.itempackages.itemtemplates.ItemTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses= ItemTemplateEntity.class)
public interface WeaponMapper {

    WeaponDTO toDto(WeaponEntity weaponEntity);

    WeaponEntity toEntity(WeaponDTO weaponDTO);
}
