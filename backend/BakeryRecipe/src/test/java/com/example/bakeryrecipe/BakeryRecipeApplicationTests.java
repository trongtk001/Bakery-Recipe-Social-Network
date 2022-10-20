package com.example.bakeryrecipe;

import com.example.bakeryrecipe.dto.EmojiDTO;
import com.example.bakeryrecipe.entity.Emoji;
import com.example.bakeryrecipe.service.EmojiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BakeryRecipeApplicationTests {

    @Autowired
    EmojiService emojiService;
    @Test
    void contextLoads() {
         List<EmojiDTO> emoji = emojiService.findAllByPost(1L);
    }

}
