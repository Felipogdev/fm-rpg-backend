package feiticeiros.example.fmbackend.itempackages.itemtemplates.special;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/special-items")
public class SpecialItemController {

    @Autowired
    private SpecialItemRepository specialItemRepository;
    @Autowired
    private SpecialItemMapper specialItemMapper;

    @PostMapping
    public ResponseEntity<SpecialItemEntity> createSpecialItem(@RequestBody SpecialItemDTO specialItemDTO) {
        SpecialItemEntity specialItem = specialItemMapper.toEntity(specialItemDTO);
        SpecialItemEntity savedSpecialItem = specialItemRepository.save(specialItem);
        return ResponseEntity.ok(savedSpecialItem);
    }


    @PostMapping("/init")
    public ResponseEntity<List<SpecialItemEntity>> createSpecialItems(@RequestBody List<SpecialItemDTO> specialItemDTOs) {
        List<SpecialItemEntity> specialItems = specialItemDTOs.stream()
                .map(specialItemMapper::toEntity)
                .toList();
        List<SpecialItemEntity> savedSpecialItems = specialItemRepository.saveAll(specialItems);
        return ResponseEntity.ok(savedSpecialItems);
    }
}
