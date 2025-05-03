package feiticeiros.example.fmbackend.characteritems.weaponcharacteritem.weaponmodifiers;

import java.util.List;

public record WeaponModifierDTO(
    Long id,
    String modifier_name,
    String modifier_description,
    List<WeaponModifiersEntity> pre_requisites
) { }
