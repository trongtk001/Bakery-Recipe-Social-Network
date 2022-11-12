package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.authentication.UserDetailsImpl;
import com.example.bakeryrecipe.dto.CommentDTO;
import com.example.bakeryrecipe.entity.Comment;
import com.example.bakeryrecipe.mapper.CommentMapper;
import com.example.bakeryrecipe.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static java.util.Objects.isNull;

@Service
public class CommentService implements BaseService<CommentDTO> {

    private final CommentRepository commentRepository;

    private final CommentMapper mapper;

    private final MemberService memberService;

    private final PostService postService;

    public CommentService(CommentRepository commentRepository, CommentMapper mapper, MemberService memberService, PostService postService) {
        this.commentRepository = commentRepository;
        this.mapper = mapper;
        this.memberService = memberService;
        this.postService = postService;
    }

    @Override
    public CommentDTO save(CommentDTO dto) {
        Comment commentEntity;
        if(memberService.searchEntity(dto.getMember().getId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this member");
        }
        if(postService.searchEntity(dto.getPost().getId()) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this post");
        }
        commentEntity = mapper.toEntity(dto);
        commentEntity = commentRepository.save(commentEntity);
        return mapper.toDTO(commentEntity);
    }

    @Override
    public CommentDTO update(CommentDTO dto) {
        Comment commentEntity;
        commentEntity = commentRepository.findById(dto.getId()).orElse(null);
        UserDetailsImpl userDetails = (UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (isNull(commentEntity)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this comment");
        }

        if (!commentEntity.getMember().getId().equals(userDetails.getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Not owner");
        }

        mapper.toEntity(dto, commentEntity);
        commentEntity = commentRepository.save(commentEntity);

        return mapper.toDTO(commentEntity);
    }

    public CommentDTO delete(long id) {
        Comment commentEntity = commentRepository.findById(id).orElse(null);
        if (commentEntity != null) {
            commentRepository.delete(commentEntity);
            return mapper.toDTO(commentEntity);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this comment");
        }
    }

    public CommentDTO search(Long id) {
        return null;
    }

    public Page<CommentDTO> searchByPost(Long id, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllByPostId(id, pageable);
        return new PageImpl<>(mapper.toDTOList(comments.getContent()), pageable, comments.getTotalElements());
    }
}
