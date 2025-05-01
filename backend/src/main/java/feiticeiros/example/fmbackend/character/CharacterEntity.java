package feiticeiros.example.fmbackend.character;

import feiticeiros.example.fmbackend.AbstractEntity;
import feiticeiros.example.fmbackend.character.enums.CharacterClasses;
import feiticeiros.example.fmbackend.character.enums.CharacterOrigin;
import feiticeiros.example.fmbackend.characterstatus.StatusEntity;
import feiticeiros.example.fmbackend.item.ItemAbstractEntity;
import feiticeiros.example.fmbackend.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CharacterEntity extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private CharacterOrigin origin;

    private Integer level;

    @Enumerated(EnumType.STRING)
    private CharacterClasses character_class;

    private String image;

    private Integer tier; //TODO: Pode ser tier 4,3,2,1,especial (FAZER A PORRA DISSO)

    @CreationTimestamp
    private Date createdAt;

    @OneToOne(mappedBy = "character", cascade = CascadeType.PERSIST)
    private StatusEntity status;

    //@OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    //private List<ItemAbstractEntity> inventory = new ArrayList<ItemAbstractEntity>();
}
