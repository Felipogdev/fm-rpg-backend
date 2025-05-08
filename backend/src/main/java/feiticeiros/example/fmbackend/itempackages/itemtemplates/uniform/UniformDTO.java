package feiticeiros.example.fmbackend.itempackages.itemtemplates.uniform;

import feiticeiros.example.fmbackend.itempackages.itemtemplates.ItemType;

public record UniformDTO(
        Long id,
        String name,
        String description,
        Integer cost,
        ItemType type,
        Integer tier,
        Integer inventory_space,
        Integer bonus_armor,
        Integer penalty
) {
}
