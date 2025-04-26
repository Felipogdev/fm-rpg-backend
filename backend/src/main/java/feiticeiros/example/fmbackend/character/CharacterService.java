package feiticeiros.example.fmbackend.character;

import feiticeiros.example.fmbackend.user.User;
import feiticeiros.example.fmbackend.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final UserService userService;

    public CharacterService(CharacterRepository characterRepository, CharacterMapper characterMapper,UserService userService) {
        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.userService = userService;
    }

    public List<CharacterEntity> getAllCharacters() {
        return characterRepository.findAll();
    }

    public CharacterEntity createCharacter(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterMapper.toEntity(characterDTO);
        System.out.println(characterEntity);
        System.out.println(characterEntity.getUser());
        User user = characterEntity.getUser();
        if (user == null) {

            throw new IllegalArgumentException("User não pode ser nulo ao criar um personagem");
        }
        return characterRepository.save(characterEntity);
    }


}
