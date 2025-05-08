package feiticeiros.example.fmbackend.characterpackages.character;

import feiticeiros.example.fmbackend.characterpackages.characterstatus.StatusEntity;
import feiticeiros.example.fmbackend.characterpackages.characterstatus.StatusService;
import feiticeiros.example.fmbackend.user.User;
import feiticeiros.example.fmbackend.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CharacterService {


    private final CharacterRepository characterRepository;
    private final CharacterMapper characterMapper;
    private final UserService userService;
    private final StatusService statusService;


    public CharacterService(CharacterRepository characterRepository,
                            CharacterMapper characterMapper,
                            UserService userService,
                            StatusService statusService) {

        this.characterRepository = characterRepository;
        this.characterMapper = characterMapper;
        this.userService = userService;
        this.statusService = statusService;
    }


    public List<CharacterEntity> getAllCharacters() {
        return characterRepository.findAll();
    }


        public CharacterEntity createCharacter(CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterMapper.toEntity(characterDTO);
        User user = characterEntity.getUser();

        if (user == null) {

            throw new IllegalArgumentException("User não pode ser nulo ao criar um personagem");
        }

        StatusEntity statusEntity = statusService.createStatus(characterEntity);
        characterEntity.setStatus(statusEntity);
        userService.addCharacter(characterEntity);
        return characterRepository.save(characterEntity);
    }

    public void deleteCharacter(CharacterEntity characterEntity) {
        User user = characterEntity.getUser();
        if (user == null) {
            throw new IllegalArgumentException("User não pode ser nulo ao criar um personagem");
        }

        UUID characterId = characterEntity.getId();
        characterRepository.deleteById(characterId);
    }

    public CharacterEntity findCharacterById(UUID id) {
        return characterRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Personagem não encontrado para o id: " + id));
    }

    public void deleteCharacterById(UUID id) {
        CharacterEntity character = findCharacterById(id);
        deleteCharacter(character);
    }


}



