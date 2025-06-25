package com.fmrpg.fmbackend.controllers;

import com.fmrpg.fmbackend.dtos.CharacterSkillDto;
import com.fmrpg.fmbackend.dtos.characterdtos.CharacterResponseDto;
import com.fmrpg.fmbackend.entities.CharacterEntity;
import com.fmrpg.fmbackend.mappers.CharacterResponseMapper;
import com.fmrpg.fmbackend.repositories.CharacterRepository;
import com.fmrpg.fmbackend.services.CharacterSkillsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/characters/skills")
public class CharacterSkillController {

    private final CharacterRepository characterRepository;
    private final CharacterSkillsService characterSkillsService;
    private final CharacterResponseMapper characterResponseMapper;

    public CharacterSkillController(
            CharacterRepository characterRepository,
            CharacterSkillsService characterSkillsService,
            CharacterResponseMapper characterResponseMapper
    ) {
        this.characterRepository = characterRepository;
        this.characterSkillsService = characterSkillsService;
        this.characterResponseMapper = characterResponseMapper;
    }

    @PatchMapping("{id}")
    public ResponseEntity<CharacterResponseDto> updateSkill(
            @RequestBody CharacterSkillDto dto,
            @PathVariable(name = "id") UUID id
            ) {

        CharacterEntity character = characterRepository.findByPublicId(id);

       return ResponseEntity.ok(characterResponseMapper.toDto(characterSkillsService.updateSkill(dto, character)));
    }




}
