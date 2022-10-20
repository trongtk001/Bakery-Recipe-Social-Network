package com.example.bakeryrecipe.api;


import com.example.bakeryrecipe.api.output.ListCommentOutput;
import com.example.bakeryrecipe.dto.CommentDTO;
import com.example.bakeryrecipe.service.CommentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/comment")
@PreAuthorize("permitAll()")
public class CommentAPI {

    private final CommentService commentService;

    public CommentAPI(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO) {
        return commentService.save(commentDTO);
    }

    @PutMapping("/{id}")
    public CommentDTO editComment(@PathVariable("id") Long id, @RequestBody CommentDTO commentDTO) {
        commentDTO.setId(id);
        return commentService.save(commentDTO);
    }

    @GetMapping("")
    public ListCommentOutput getCommentByPost(@RequestParam("id") long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.DESC, "createDate"));
        Page<CommentDTO> commentDTOS = commentService.searchByPost(id, pageable);
        return new ListCommentOutput(page, size, commentDTOS.getTotalPages(), commentDTOS.getContent());
    }
}
