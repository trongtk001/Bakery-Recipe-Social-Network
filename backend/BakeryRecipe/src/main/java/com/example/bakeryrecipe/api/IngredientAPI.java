package com.example.bakeryrecipe.api;


import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.service.IngredientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/ingredient")
@PreAuthorize("permitAll()")
public class IngredientAPI {

    private final IngredientService ingredientService;

    public IngredientAPI(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @PostMapping("")
    public IngredientDTO createNewIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return ingredientService.save(ingredientDTO);
    }

    @GetMapping("")
    public List<IngredientDTO> getIngredient() {
        return ingredientService.findAllIngredients();
    }



}
