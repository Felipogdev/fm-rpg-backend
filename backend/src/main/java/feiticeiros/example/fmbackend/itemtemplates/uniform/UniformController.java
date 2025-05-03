package feiticeiros.example.fmbackend.itemtemplates.uniform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/uniforms")
public class UniformController {

    @Autowired
    private UniformRepository uniformRepository;

    @Autowired
    private UniformMapper uniformMapper;

    @PostMapping("/create")
    public ResponseEntity<UniformEntity> createUniform(@RequestBody UniformDTO uniformDTO) {
        UniformEntity uniform = uniformMapper.toEntity(uniformDTO);
        UniformEntity savedUniform = uniformRepository.save(uniform);
        return ResponseEntity.ok(savedUniform);
    }

    @PostMapping("/init")
    public ResponseEntity<List<UniformEntity>> createUniforms(@RequestBody List<UniformDTO> uniformDTOs) {
        List<UniformEntity> uniforms = uniformDTOs.stream()
                .map(uniformMapper::toEntity)
                .toList();

        List<UniformEntity> savedUniforms = uniformRepository.saveAll(uniforms);
        return ResponseEntity.ok(savedUniforms);
    }
}
