package com.example.bakeryrecipe;

import com.example.bakeryrecipe.repository.CommentRepository;
import com.example.bakeryrecipe.repository.MemberRepository;
import com.example.bakeryrecipe.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BakeryRecipeApplicationTests {

    @Autowired
    CommentRepository commentRepository;

}
