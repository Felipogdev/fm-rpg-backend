package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.dtos.ShieldItemDto;
import com.fmrpg.fmbackend.dtos.SpecialItemDto;
import com.fmrpg.fmbackend.dtos.UniformItemDto;
import com.fmrpg.fmbackend.dtos.WeaponItemDto;
import com.fmrpg.fmbackend.entities.*;
import com.fmrpg.fmbackend.entities.characteritempkg.*;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.repositories.UserRepository;
import com.fmrpg.fmbackend.repositories.WeaponGroupRepository;
import com.fmrpg.fmbackend.repositories.WeaponPropertiesRepository;
import com.fmrpg.fmbackend.repositories.characteritempkg.*;
import com.fmrpg.fmbackend.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CharacterItemRepository characterItemRepository;
    private final WeaponCharacterRepository weaponCharacterRepository;
    private final UniformCharacterRepository uniformCharacterRepository;
    private final ShieldCharacterRepository shieldCharacterRepository;
    private final SpecialItemsCharacterRepository specialItemsCharacterRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final CharacterService characterService;
    private final WeaponGroupRepository weaponGroupRepository;
    private final WeaponPropertiesRepository weaponPropertiesRepository;

    public ItemService(ItemRepository itemRepository,
                       CharacterItemRepository characterItemRepository, WeaponCharacterRepository weaponCharacterRepository, UniformCharacterRepository uniformCharacterRepository, ShieldCharacterRepository shieldCharacterRepository, SpecialItemsCharacterRepository specialItemsCharacterRepository, UserRepository userRepository, UserService userService, CharacterService characterService, WeaponGroupRepository weaponGroupRepository, WeaponPropertiesRepository weaponPropertiesRepository
    ) {
        this.itemRepository = itemRepository;
        this.characterItemRepository = characterItemRepository;

        this.weaponCharacterRepository = weaponCharacterRepository;
        this.uniformCharacterRepository = uniformCharacterRepository;
        this.shieldCharacterRepository = shieldCharacterRepository;
        this.specialItemsCharacterRepository = specialItemsCharacterRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.characterService = characterService;

        this.weaponGroupRepository = weaponGroupRepository;
        this.weaponPropertiesRepository = weaponPropertiesRepository;
    }


    private void validateCharacterFromUser(OAuth2User oAuth2User, CharacterEntity character) {
        User user = userRepository.findByGoogleId(oAuth2User.getName()).orElseThrow();

        if(!userService.isCharacterFromuser(user, character)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    private void validadeItemFromCharacter(CharacterEntity character, CharacterItem item) {
        if (!characterService.isItemFromCharacter(character, item)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

    @Transactional
    public CharacterItem createItem(OAuth2User oAuth2User, CharacterEntity character, String itemName) {

        validateCharacterFromUser(oAuth2User, character);

        ItemAbstract item = itemRepository.findByName(itemName);
        if (item == null) {
            throw new IllegalArgumentException("Item '" + itemName + "' não encontrado.");
        }

        if(item instanceof Weapon){
            WeaponCharacter characterItem = new WeaponCharacter(
                    item.getName(),
                    item.getWeight(),
                    item.getCost(),
                    item.getDescription(),
                    new ArrayList<>(((Weapon) item).getDiceQuantity()),
                    new ArrayList<>(((Weapon) item).getDiceValue()),
                    ((Weapon) item).getCritMargin(),
                    ((Weapon) item).getDamageType(),
                    ((Weapon) item).getWeaponGroup(),
                    new ArrayList<>(((Weapon) item).getWeaponProperties()),
                    character);
            weaponCharacterRepository.save(characterItem);
            character.getInventory().add(characterItem);
            return characterItem;
        }

        if(item instanceof Uniform) {
            UniformCharacter characterItem = new UniformCharacter(
                    item.getName(),
                    item.getDescription(),
                    ((Uniform) item).getBonusArmor(),
                    ((Uniform) item).getPenalty(),
                    item.getWeight(),
                    item.getCost(),
                    character);
            uniformCharacterRepository.save(characterItem);
            character.getInventory().add(characterItem);
            return characterItem;
        }

        if(item instanceof Shield) {
            ShieldCharacter characterItem = new ShieldCharacter(
                    item.getName(),
                    item.getDescription(),
                    ((Shield) item).getBonusArmor(),
                    ((Shield) item).getPenalty(),
                    item.getWeight(),
                    item.getCost()
                    ,character);
            shieldCharacterRepository.save(characterItem);
            character.getInventory().add(characterItem);
            return characterItem;
        }

        if (item instanceof SpecialItems) {
            SpecialItemCharacter characterItem = new SpecialItemCharacter(
                    item.getName(),
                    item.getDescription(),
                    item.getWeight(),
                    item.getCost(),
                    ((SpecialItems) item).getSpecialItemCategory(),
                    character);
            specialItemsCharacterRepository.save(characterItem);
            character.getInventory().add(characterItem);
            return characterItem;
        }

        throw new IllegalArgumentException("Tipo de item '" + itemName + "' não é suportado.");
    }

    @Transactional
    public CharacterItem updateWeapon(OAuth2User oAuth2User, CharacterEntity character, WeaponItemDto dto, Long itemId) {
        validateCharacterFromUser(oAuth2User, character);

        WeaponCharacter item = weaponCharacterRepository.findById(itemId).orElseThrow();
        validadeItemFromCharacter(character, item);

        if(dto.name() != null) item.setName(dto.name());
        if(dto.description() != null) item.setDescription(dto.description());
        if(dto.cost() != null) item.setCost(dto.cost());
        if(dto.weight() != null) item.setWeight(dto.weight());
        if(dto.diceQuantity() != null) item.setDiceQuantity(dto.diceQuantity());
        if(dto.diceValue() != null) item.setDiceValue(dto.diceValue());
        if(dto.damageType() != null) item.setDamageType(dto.damageType());
        if(dto.critMargin() != null) item.setCritMargin(dto.critMargin());
        if(dto.weaponGroup() != null) item.setWeaponGroup(weaponGroupRepository.findByName(dto.weaponGroup()).orElseThrow());
        if(dto.weaponProperties() != null) item.getWeaponProperties().add(weaponPropertiesRepository.findByName(dto.weaponProperties()).orElseThrow());

        return weaponCharacterRepository.save(item);
    }

    @Transactional
    public CharacterItem updateShield(OAuth2User oAuth2User, CharacterEntity character, ShieldItemDto dto, Long itemId) {

        validateCharacterFromUser(oAuth2User, character);

        ShieldCharacter item = shieldCharacterRepository.findById(itemId).orElseThrow();
        validadeItemFromCharacter(character, item);

        if(dto.name() != null) item.setName(dto.name());
        if(dto.description() != null) item.setDescription(dto.description());
        if(dto.cost() != null) item.setCost(dto.cost());
        if(dto.weight() != null) item.setWeight(dto.weight());
        if(dto.bonusArmor() != null) item.setBonusArmor(dto.bonusArmor());
        if(dto.penalty() != null) item.setPenalty(dto.penalty());

        return shieldCharacterRepository.save(item);
    }

    @Transactional
    public CharacterItem updateUniform(OAuth2User oAuth2User, CharacterEntity character, UniformItemDto dto, Long itemId) {
        validateCharacterFromUser(oAuth2User, character);

        UniformCharacter item = uniformCharacterRepository.findById(itemId).orElseThrow();
        validadeItemFromCharacter(character, item);

        if(dto.name() != null) item.setName(dto.name());
        if(dto.description() != null) item.setDescription(dto.description());
        if(dto.cost() != null) item.setCost(dto.cost());
        if(dto.weight() != null) item.setWeight(dto.weight());
        if(dto.bonusArmor() != null) item.setBonusArmor(dto.bonusArmor());
        if(dto.penalty() != null) item.setPenalty(dto.penalty());

        return uniformCharacterRepository.save(item);
    }

    @Transactional
    public CharacterItem updateSpecialItem(OAuth2User oAuth2User, CharacterEntity character, SpecialItemDto dto, Long itemId) {
        validateCharacterFromUser(oAuth2User, character);

        SpecialItemCharacter item = specialItemsCharacterRepository.findById(itemId).orElseThrow();
        validadeItemFromCharacter(character, item);

        if(dto.name() != null) item.setName(dto.name());
        if(dto.description() != null) item.setDescription(dto.description());
        if(dto.cost() != null) item.setCost(dto.cost());
        if(dto.weight() != null) item.setWeight(dto.weight());
        if(dto.specialItemCategory() != null) item.setSpecialItemCategory(dto.specialItemCategory());

        return specialItemsCharacterRepository.save(item);
    }

    @Transactional
    public void deleteItem(OAuth2User oAuth2User, CharacterEntity character, Long itemId) {
        validateCharacterFromUser(oAuth2User, character);

        CharacterItem item = characterItemRepository.findById(itemId).orElseThrow();

        character.getInventory().remove(item);
        characterItemRepository.delete(item);
    }

    public List<CharacterItem> showInventory(OAuth2User oAuth2User, CharacterEntity character) {
        validateCharacterFromUser(oAuth2User, character);

        return character.getInventory();
    }
}

