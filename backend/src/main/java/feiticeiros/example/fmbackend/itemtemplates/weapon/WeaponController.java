package feiticeiros.example.fmbackend.itemtemplates.weapon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/weapons")
public class WeaponController {

    @Autowired
    private WeaponRepository weaponRepository;

    @Autowired
    private WeaponMapper weaponMapper;

    @PostMapping("/create")
    public ResponseEntity<WeaponEntity> createWeapon(@RequestBody WeaponDTO weaponDTO) {
        WeaponEntity weapon = weaponMapper.toEntity(weaponDTO);
        WeaponEntity savedWeapon = weaponRepository.save(weapon);
        return ResponseEntity.ok(savedWeapon);
    }

    @PostMapping("/init")
    public ResponseEntity<List<WeaponEntity>> createWeapons(@RequestBody List<WeaponDTO> weaponDTOs) {
        List<WeaponEntity> weapons = weaponDTOs.stream()
                .map(weaponMapper::toEntity)
                .collect(Collectors.toList());

        List<WeaponEntity> savedWeapons = weaponRepository.saveAll(weapons);
        return ResponseEntity.ok(savedWeapons);
    }


}
