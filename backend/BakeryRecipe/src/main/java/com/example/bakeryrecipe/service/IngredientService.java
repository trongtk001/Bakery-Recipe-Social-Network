package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import com.example.bakeryrecipe.mapper.IngredientMapper;
import com.example.bakeryrecipe.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class IngredientService implements BaseService<IngredientDTO> {

    private final IngredientRepository ingredientRepository;

    private final IngredientMapper mapper;

    public IngredientService(IngredientRepository ingredientRepository, IngredientMapper mapper) {
        this.ingredientRepository = ingredientRepository;
        this.mapper = mapper;
    }

    @Override
    public IngredientDTO save(IngredientDTO dto) {
        Ingredient ingredient;
        if (dto.getId() == null) {

            ingredient = ingredientRepository.findByIngredients(dto.getIngredients()).orElse(null);
            if (ingredient == null) {
                ingredient = mapper.toEntity(dto);
                ingredient = ingredientRepository.save(ingredient);
            } else {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "Already have this ingredient");
            }
        } else {
            ingredient = ingredientRepository.findById(dto.getId()).orElse(null);
            if (ingredient != null) {
                ingredient = ingredientRepository.save(mapper.toEntity(dto, ingredient));
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this ingredient");
            }
        }
        return mapper.toDTO(ingredient);
    }

    @Override
    public IngredientDTO update(IngredientDTO dto) {
        return null;
    }

    public IngredientDTO delete(long id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if (ingredient != null) {
            ingredientRepository.delete(ingredient);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this ingredient");
        }
        return mapper.toDTO(ingredient);
    }

    public IngredientDTO search(Long id) {
        return mapper.toDTO(ingredientRepository.findById(id).orElse(null));
    }

    public List<IngredientDTO> searchByName(String name) {
        return mapper.toDTOList(ingredientRepository.searchAllByIngredientsContaining(name));
    }

    public List<IngredientDTO> findAllIngredients() {
        return mapper.toDTOList(ingredientRepository.findAll());
    }
}
