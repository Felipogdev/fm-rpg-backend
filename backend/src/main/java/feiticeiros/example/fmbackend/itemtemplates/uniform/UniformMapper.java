package feiticeiros.example.fmbackend.itemtemplates.uniform;

import feiticeiros.example.fmbackend.itemtemplates.ItemTemplateEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses= ItemTemplateEntity.class)
public interface UniformMapper {

    UniformDTO toDto(UniformEntity uniformEntity);

    UniformEntity toEntity(UniformDTO uniformDTO);
}
