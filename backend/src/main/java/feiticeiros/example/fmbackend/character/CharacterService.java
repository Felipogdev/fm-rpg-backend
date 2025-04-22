package feiticeiros.example.fmbackend.character;

import feiticeiros.example.fmbackend.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<CharacterEntity> getAllCharacters() {
        return characterRepository.findAll();
    }

    public CharacterEntity createCharacter(CharacterEntity characterEntity) {
        return characterRepository.save(characterEntity);
    }

}
