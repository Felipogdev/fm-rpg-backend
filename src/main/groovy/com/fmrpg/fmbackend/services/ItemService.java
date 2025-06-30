package com.fmrpg.fmbackend.services;

import com.fmrpg.fmbackend.entities.*;
import com.fmrpg.fmbackend.entities.characteritempkg.*;
import com.fmrpg.fmbackend.entities.characterpkg.CharacterEntity;
import com.fmrpg.fmbackend.repositories.characteritempkg.*;
import com.fmrpg.fmbackend.repositories.ItemRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final CharacterItemRepository characterItemRepository;
    private final WeaponCharacterRepository weaponCharacterRepository;
    private final UniformCharacterRepository uniformCharacterRepository;
    private final ShieldCharacterRepository shieldCharacterRepository;
    private final SpecialItemsCharacterRepository specialItemsCharacterRepository;

    public ItemService(ItemRepository itemRepository,
                       CharacterItemRepository characterItemRepository, WeaponCharacterRepository weaponCharacterRepository, UniformCharacterRepository uniformCharacterRepository, ShieldCharacterRepository shieldCharacterRepository, SpecialItemsCharacterRepository specialItemsCharacterRepository
    ) {
        this.itemRepository = itemRepository;
        this.characterItemRepository = characterItemRepository;

        this.weaponCharacterRepository = weaponCharacterRepository;
        this.uniformCharacterRepository = uniformCharacterRepository;
        this.shieldCharacterRepository = shieldCharacterRepository;
        this.specialItemsCharacterRepository = specialItemsCharacterRepository;
    }


    @Transactional
    public CharacterItem createItem(CharacterEntity character, String itemName) {

        ItemAbstract item = itemRepository.findByName(itemName);
        if (item == null) {
            throw new IllegalArgumentException("Item '" + itemName + "' não encontrado.");
        }

        if(item instanceof Weapon){
            WeaponCharacter characterItem = new WeaponCharacter(item.getName(),item.getWeight(),item.getCost(),item.getDescription(),new ArrayList<>(((Weapon) item).getDiceQuantity()),new ArrayList<>(((Weapon) item).getDiceValue()),((Weapon) item).getCritMargin(),((Weapon) item).getDamageType(),((Weapon) item).getWeaponGroup(),new ArrayList<>(((Weapon) item).getWeaponProperties()),character);
            weaponCharacterRepository.save(characterItem);
            character.getInventory().add(characterItem);
            return characterItem;
        }

        if(item instanceof Uniform) {
            UniformCharacter characterItem = new UniformCharacter(item.getName(),item.getDescription(),((Uniform) item).getBonusArmor(),((Uniform) item).getPenalty(),item.getWeight(),item.getCost(),character);
            uniformCharacterRepository.save(characterItem);
            character.getInventory().add(characterItem);
            return characterItem;
        }

        if(item instanceof Shield) {
            ShieldCharacter characterItem = new ShieldCharacter(item.getName(),item.getDescription(),((Shield) item).getBonusArmor(),((Shield) item).getPenalty(),item.getWeight(),item.getCost(),character);
            shieldCharacterRepository.save(characterItem);
            character.getInventory().add(characterItem);
            return characterItem;
        }

        if (item instanceof SpecialItems) {
            SpecialItemCharacter characterItem = new SpecialItemCharacter(item.getName(),item.getDescription(),item.getWeight(),item.getCost(),((SpecialItems) item).getSpecialItemCategory(),character);
            specialItemsCharacterRepository.save(characterItem);
            character.getInventory().add(characterItem);
            return characterItem;
        }

        throw new IllegalArgumentException("Tipo de item '" + itemName + "' não é suportado.");
    }
}