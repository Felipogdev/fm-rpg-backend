package feiticeiros.example.fmbackend.character;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/characters")
public class CharacterController {

    @PostMapping
    public CharacterDTO createCharacter(@RequestBody CharacterDTO dto) {
        return dto;
    }
}
