package com.example.bakeryrecipe.api;


import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.service.IngredientService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("ingredient")
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

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public IngredientDTO deleteIngredient(@PathVariable("id") long id) { return ingredientService.delete(id); }

    @GetMapping("")
    public List<IngredientDTO> getIngredients() {
        return ingredientService.findAllIngredients();
    }

    @GetMapping("{id}")
    public IngredientDTO getIngredients(@PathVariable("id") long id) {
        return ingredientService.search(id);
    }

    @PutMapping("{id}")
    public IngredientDTO editIngredient(@PathVariable("id") long id, @RequestBody IngredientDTO ingredientDTO) {
        ingredientDTO.setId(id);
        return ingredientService.update(ingredientDTO);
    }

}
