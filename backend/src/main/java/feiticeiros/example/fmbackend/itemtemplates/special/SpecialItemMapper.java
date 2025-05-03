package feiticeiros.example.fmbackend.itemtemplates.special;

import feiticeiros.example.fmbackend.itemtemplates.ItemTemplateEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ItemTemplateEntity.class)
public interface SpecialItemMapper{

    SpecialItemDTO toDto(SpecialItemEntity specialItemEntity);

    SpecialItemEntity toEntity(SpecialItemDTO specialItemDTO);
}
