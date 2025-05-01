package feiticeiros.example.fmbackend.item.special;

import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
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
public class SpecialItemEntity extends ItemAbstractEntity {

    @Enumerated(EnumType.STRING)
    private SpecialItemEnum special_item_type;
}
