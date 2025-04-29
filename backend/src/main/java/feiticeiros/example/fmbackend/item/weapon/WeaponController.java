package feiticeiros.example.fmbackend.item.weapon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
