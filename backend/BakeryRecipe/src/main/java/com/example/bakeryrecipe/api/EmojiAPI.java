package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.dto.EmojiDTO;
import com.example.bakeryrecipe.service.EmojiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/emoji")
@PreAuthorize("permitAll()")
public class EmojiAPI {
    @Autowired
    EmojiService emojiService;

    @PostMapping("")
    public EmojiDTO createNewLike(@RequestBody EmojiDTO dto){
        return emojiService.save(dto);
    }

    @GetMapping("")
    public List<EmojiDTO> getEmojiByPost(@RequestParam("id") Long id){
        return emojiService.findAllByPost(id);
    }
}
