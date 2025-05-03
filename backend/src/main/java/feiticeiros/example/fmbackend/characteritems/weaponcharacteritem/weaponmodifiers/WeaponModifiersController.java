package feiticeiros.example.fmbackend.characteritems.weaponcharacteritem.weaponmodifiers;

import feiticeiros.example.fmbackend.itemtemplates.weapon.WeaponDTO;
import feiticeiros.example.fmbackend.itemtemplates.weapon.WeaponEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/weapon-modifiers")
public class WeaponModifiersController {


    @Autowired
    private WeaponModifiersRepository weaponModifiersRepository;

    @Autowired
    private WeaponModifierMapper weaponModifierMapper;


    @PostMapping("/init")
    public ResponseEntity<List<WeaponModifiersEntity>> createWeaponModifiers(@RequestBody List<WeaponModifierDTO> weaponModifierDTOs) {
        List<WeaponModifiersEntity> weaponModifiers = weaponModifierDTOs.stream()
                .map(weaponModifierMapper::toEntity)
                .collect(Collectors.toList());

        List<WeaponModifiersEntity> savedWeaponModifiers = weaponModifiersRepository.saveAll(weaponModifiers);
        return ResponseEntity.ok(savedWeaponModifiers);
    }

}
