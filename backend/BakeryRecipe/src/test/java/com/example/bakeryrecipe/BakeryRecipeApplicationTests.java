package com.example.bakeryrecipe;

import com.example.bakeryrecipe.entity.Comment;
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BakeryRecipeApplicationTests {

    @Autowired
    CommentRepository commentRepository;

}
