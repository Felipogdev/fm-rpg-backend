package feiticeiros.example.fmbackend.itemtemplates.shield;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/shields")
public class ShieldController {

    @Autowired
    private ShieldRepository shieldRepository;

    @Autowired
    private ShieldMapper shieldMapper;

    @PostMapping("/create")
    public ResponseEntity<ShieldEntity> createShield(@RequestBody ShieldDTO shieldDTO) {
        ShieldEntity shield = shieldMapper.toEntity(shieldDTO);
        ShieldEntity savedShield = shieldRepository.save(shield);
        return ResponseEntity.ok(savedShield);
    }

    @PostMapping("/init")
    public ResponseEntity<List<ShieldEntity>> createShields(@RequestBody List<ShieldDTO> shieldDTOs) {
        List<ShieldEntity> shields = shieldDTOs.stream()
                .map(shieldMapper::toEntity)
                .toList();
        List<ShieldEntity> savedShields = shieldRepository.saveAll(shields);
        return ResponseEntity.ok(savedShields);
    }
}
