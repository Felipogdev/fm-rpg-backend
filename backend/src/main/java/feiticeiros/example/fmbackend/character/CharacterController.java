package feiticeiros.example.fmbackend.character;

import feiticeiros.example.fmbackend.user.User;
import feiticeiros.example.fmbackend.user.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/characters")
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;

    public CharacterController(CharacterService characterService, CharacterMapper characterMapper) {
        this.characterService = characterService;
        this.characterMapper = characterMapper;
    }

    @GetMapping
    public List<CharacterEntity> findAll() {
       return characterService.getAllCharacters();
    }

    @PostMapping
    public CharacterDTO createCharacter(@RequestBody CharacterDTO characterDTO) {
        CharacterEntity characterEntity = characterService.createCharacter(characterDTO);
        return characterMapper.toDto(characterEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable UUID id) {
        characterService.deleteCharacterById(id);
        return ResponseEntity.noContent().build();
    }


}

