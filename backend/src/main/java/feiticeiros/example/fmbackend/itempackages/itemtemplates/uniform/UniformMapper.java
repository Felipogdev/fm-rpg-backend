package feiticeiros.example.fmbackend.itempackages.itemtemplates.uniform;

import feiticeiros.example.fmbackend.itempackages.itemtemplates.ItemTemplateEntity;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses= ItemTemplateEntity.class)
public interface UniformMapper {

    UniformDTO toDto(UniformEntity uniformEntity);

    UniformEntity toEntity(UniformDTO uniformDTO);
}
