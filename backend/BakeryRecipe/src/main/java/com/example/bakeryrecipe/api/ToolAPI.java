package com.example.bakeryrecipe.api;
import com.example.bakeryrecipe.dto.ToolDTO;
import com.example.bakeryrecipe.service.ToolService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("tool")
@PreAuthorize("permitAll()")
public class ToolAPI {

    private final ToolService toolService;

    public ToolAPI(ToolService toolService) {
        this.toolService = toolService;
    }


    @PostMapping("")
    public ToolDTO createTool(@RequestBody ToolDTO dto){
        return toolService.save(dto);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("isAuthenticated()")
    public ToolDTO deleteTool(@PathVariable("id") long id) { return toolService.delete(id); }

    @GetMapping("")
    public List<ToolDTO> getAllTools() {
        return toolService.findAllTool();
    }

    @GetMapping("/id")
    public List<Long> getAllToolIds() {
        return toolService.findAllToolIds();
    }

    @GetMapping("{id}")
    public ToolDTO getTool(@PathVariable("id") long id) {
        return toolService.search(id);
    }

    @PutMapping("{id}")
    public ToolDTO editTool(@PathVariable("id") long id, @RequestBody ToolDTO dto) {
        dto.setId(id);
        return toolService.update(dto);
    }
}
