package feiticeiros.example.fmbackend.itemtemplates.special;

import feiticeiros.example.fmbackend.itemtemplates.ItemType;

public record SpecialItemDTO(
        Long id,
        String name,
        String description,
        Integer cost,
        ItemType type,
        Integer tier,
        Integer inventory_space,
        SpecialItemEnum special_item_type
) {
}
