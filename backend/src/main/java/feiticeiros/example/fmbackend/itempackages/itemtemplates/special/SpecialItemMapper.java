package feiticeiros.example.fmbackend.itempackages.itemtemplates.special;

import feiticeiros.example.fmbackend.itempackages.itemtemplates.ItemTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ItemTemplateEntity.class)
public interface SpecialItemMapper{

    SpecialItemDTO toDto(SpecialItemEntity specialItemEntity);

    SpecialItemEntity toEntity(SpecialItemDTO specialItemDTO);
}
