package com.example.bakeryrecipe.service;

import com.example.bakeryrecipe.dto.SharePostDTO;
import com.example.bakeryrecipe.entity.SharePost;
import com.example.bakeryrecipe.mapper.SharePostMapper;
import com.example.bakeryrecipe.repository.SharePostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static java.util.Objects.isNull;


@Service
public class SharePostService implements BaseService<SharePostDTO>{

    private final SharePostMapper mapper;

    private final SharePostRepository repository;

    public SharePostService(SharePostMapper mapper, SharePostRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public SharePostDTO save(SharePostDTO dto) {
        if (isNull(repository.findByMember_IdAndPost_Id(dto.getMemberID(), dto.getPostID()))) {
            SharePost entity = mapper.toEntity(dto);
            entity = repository.save(entity);
            return mapper.toDTO(entity);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This share already exists");
        }
    }

    @Override
    public SharePostDTO update(SharePostDTO dto) {
        return null;
    }

    @Override
    public SharePostDTO delete(long id) {
        SharePost entity = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Not found this share"));
        repository.delete(entity);
        return mapper.toDTO(entity);
    }

    public Page<SharePostDTO> getShareByMemberID(long id, Pageable pageable) {
        Page<SharePost> page = repository.findAllByMember_Id(id, pageable);
        return new PageImpl<>(mapper.toDTOList(page.getContent()), pageable, page.getTotalElements());
    }
}
