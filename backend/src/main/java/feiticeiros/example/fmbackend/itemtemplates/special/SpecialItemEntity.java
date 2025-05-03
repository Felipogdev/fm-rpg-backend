package feiticeiros.example.fmbackend.itemtemplates.special;

import feiticeiros.example.fmbackend.itemtemplates.ItemTemplateEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "special_items")
@Getter
@Setter
public class SpecialItemEntity extends ItemTemplateEntity {

    @Enumerated(EnumType.STRING)
    private SpecialItemEnum special_item_type;
}
