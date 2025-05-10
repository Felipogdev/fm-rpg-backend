package feiticeiros.example.fmbackend.itempackages.characteritems.weaponcharacteritem.weaponmodifiers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WeaponModifierMapper {

    WeaponModifierDTO toDTO(WeaponModifiersEntity weaponModifiersEntity);

    WeaponModifiersEntity toEntity(WeaponModifierDTO weaponModifierDTO);
}
