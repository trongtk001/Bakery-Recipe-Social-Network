package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.ToolDTO;
import com.example.bakeryrecipe.entity.Recipe;
import com.example.bakeryrecipe.entity.RecipeTool;
import com.example.bakeryrecipe.entity.Tool;
import com.example.bakeryrecipe.mapper.ToolMapper;
import com.example.bakeryrecipe.repository.RecipeToolRepository;
import com.example.bakeryrecipe.repository.ToolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecipeToolService {

    private final ToolMapper mapper;
    private final RecipeToolRepository recipeToolRepository;
    private final ToolRepository toolRepository;

    public RecipeToolService(ToolMapper mapper, RecipeToolRepository recipeToolRepository, ToolRepository toolRepository) {
        this.mapper = mapper;
        this.recipeToolRepository = recipeToolRepository;
        this.toolRepository = toolRepository;
    }

    public List<ToolDTO> save(Recipe recipe, List<ToolDTO> toolDTOS) {
        return toolDTOS.stream().map(toolDTO -> {
            Tool tool = toolRepository.findById(toolDTO.getId()).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this tool")
            );
            RecipeTool recipeTool = new RecipeTool(tool, recipe);
            recipeTool = recipeToolRepository.save(recipeTool);
            mapper.toDTO(recipeTool.getTool(), toolDTO);
            return toolDTO;
        }).collect(Collectors.toList());
    }
}
