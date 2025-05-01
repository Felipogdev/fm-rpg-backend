package feiticeiros.example.fmbackend.item.special;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = ItemAbstractEntity.class)
public interface SpecialItemMapper{

    SpecialItemDTO toDto(SpecialItemEntity specialItemEntity);

    SpecialItemEntity toEntity(SpecialItemDTO specialItemDTO);
}
