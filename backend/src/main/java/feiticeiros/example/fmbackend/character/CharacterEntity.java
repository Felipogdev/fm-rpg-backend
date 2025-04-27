package feiticeiros.example.fmbackend.character;

import feiticeiros.example.fmbackend.AbstractEntity;
import feiticeiros.example.fmbackend.characterstatus.StatusEntity;
import feiticeiros.example.fmbackend.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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

    private String origin;

    private Integer level;

    private String character_class;

    private String image;

    @CreationTimestamp
    private Date createdAt;

    @OneToOne(mappedBy = "character")
    private StatusEntity status;
}
