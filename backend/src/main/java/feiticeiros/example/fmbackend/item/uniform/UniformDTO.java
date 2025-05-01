package feiticeiros.example.fmbackend.item.uniform;

import feiticeiros.example.fmbackend.item.ItemType;

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
