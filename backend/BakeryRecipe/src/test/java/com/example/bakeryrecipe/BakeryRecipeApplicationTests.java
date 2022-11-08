package com.example.bakeryrecipe;

import com.example.bakeryrecipe.dto.PostDTO;
import com.example.bakeryrecipe.entity.Post;
import com.example.bakeryrecipe.repository.CommentRepository;
import com.example.bakeryrecipe.repository.PostRepository;
import com.example.bakeryrecipe.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BakeryRecipeApplicationTests {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Test
    @Transactional
    void test() {
        PostDTO postDTO = postService.search(41L);
    }

}
