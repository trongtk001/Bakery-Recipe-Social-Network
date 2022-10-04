package com.example.bakeryrecipe.dto;


import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDTO {
    private Long id;

}
