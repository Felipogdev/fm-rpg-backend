package feiticeiros.example.fmbackend.item.uniform;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses= ItemAbstractEntity.class)
public interface UniformMapper {

    UniformDTO toDto(UniformEntity uniformEntity);

    UniformEntity toEntity(UniformDTO uniformDTO);
}
