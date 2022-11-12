package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import com.example.bakeryrecipe.mapper.IngredientMapper;
import com.example.bakeryrecipe.repository.IngredientRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

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
        ingredient = ingredientRepository.findByIngredients(dto.getIngredients()).orElse(null);

        if (nonNull(ingredient)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already have this ingredient");

        }

        ingredient = mapper.toEntity(dto);
        ingredient = ingredientRepository.save(ingredient);
        return mapper.toDTO(ingredient);
    }

    @Override
    public IngredientDTO update(IngredientDTO dto) {
        Ingredient ingredient;

        if(isNull(dto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID is empty");
        }

        ingredient = ingredientRepository.findById(dto.getId()).orElse(null);
        if (isNull(ingredient)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this ingredient");
        }

        mapper.toEntity(dto, ingredient);
        ingredient = ingredientRepository.save(ingredient);
        return mapper.toDTO(ingredient);
    }

    public IngredientDTO delete(long id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if (isNull(ingredient)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this ingredient");
        }

        ingredientRepository.delete(ingredient);
        return mapper.toDTO(ingredient);
    }

    public IngredientDTO search(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id).orElse(null);
        if (isNull(ingredient)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this ingredient");
        }
        return mapper.toDTO(ingredient);
    }

    public List<IngredientDTO> searchByName(String name) {
        return mapper.toDTOList(ingredientRepository.searchAllByIngredientsContaining(name));
    }

    public List<IngredientDTO> findAllIngredients() {
        return mapper.toDTOList(ingredientRepository.findAll());
    }
}
