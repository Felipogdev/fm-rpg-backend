package feiticeiros.example.fmbackend.character;

public class CharacterMapper {

    //ToEntity
    public static CharacterEntity toEntity(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = new CharacterEntity();
        characterEntity.setName(characterDTO.name());
        characterEntity.setOrigin(characterDTO.origin());
        characterEntity.setLevel(characterDTO.level());
        characterEntity.setCharacter_class(CharacterDTO.character_class());
        characterEntity.setImage(characterDTO.image());
        characterEntity.User.(characterDTO.userId());
        return characterEntity;


    }

}
