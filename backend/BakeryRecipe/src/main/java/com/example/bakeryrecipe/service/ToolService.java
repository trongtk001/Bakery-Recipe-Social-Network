package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.IngredientDTO;
import com.example.bakeryrecipe.dto.ToolDTO;
import com.example.bakeryrecipe.entity.Ingredient;
import com.example.bakeryrecipe.entity.Tool;
import com.example.bakeryrecipe.mapper.ToolMapper;
import com.example.bakeryrecipe.repository.ToolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class ToolService  implements BaseService<ToolDTO>{

    private final ToolRepository toolRepository;
    private final ToolMapper mapper;

    public ToolService(ToolRepository toolRepository, ToolMapper mapper) {
        this.toolRepository = toolRepository;
        this.mapper = mapper;
    }


    @Override
    public ToolDTO save(ToolDTO dto) {
        Tool tool;
        tool = toolRepository.findByName(dto.getName()).orElse(null);

        if (nonNull(tool)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Already have this tool");
        }

        tool = mapper.toEntity(dto);
        tool = toolRepository.save(tool);
        return mapper.toDTO(tool);
    }

    @Override
    public ToolDTO update(ToolDTO dto) {
        Tool tool;

        if(isNull(dto.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID is empty");
        }

        tool = toolRepository.findById(dto.getId()).orElse(null);
        if (isNull(tool)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this tool");
        }

        mapper.toEntity(dto, tool);
        tool = toolRepository.save(tool);
        return mapper.toDTO(tool);
    }

    @Override
    public ToolDTO delete(long id) {
        Tool tool = toolRepository.findById(id).orElse(null);
        if (isNull(tool)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this tool");
        }

        toolRepository.delete(tool);
        return mapper.toDTO(tool);
    }

    public ToolDTO search(Long id) {
        Tool tool = toolRepository.findById(id).orElse(null);
        if (isNull(tool)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this tool");
        }
        return mapper.toDTO(tool);
    }
    public List<ToolDTO> findAllTool() {
        return mapper.toDTOList(toolRepository.findAll());
    }

    public List<Long> findAllToolIds(){
        List<Tool> list = toolRepository.findAll();
        List<Long> ids = new ArrayList<>();
        for (Tool t: list) {
            ids.add(t.getId());
        }
        return ids;
    }
}
