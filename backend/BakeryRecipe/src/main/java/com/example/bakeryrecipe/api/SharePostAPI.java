package com.example.bakeryrecipe.api;

import com.example.bakeryrecipe.api.output.PageResponse;
import com.example.bakeryrecipe.api.output.SharePostPage;
import com.example.bakeryrecipe.dto.SharePostDTO;
import com.example.bakeryrecipe.service.SharePostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("share")
@PreAuthorize("permitAll()")
public class SharePostAPI {

    private final SharePostService sharePostService;

    public SharePostAPI(SharePostService sharePostService) {
        this.sharePostService = sharePostService;
    }

    @GetMapping("user/{id}")
    public PageResponse<SharePostDTO> getSharePost(@PathVariable("id") long id, @RequestParam("page") int page, @RequestParam("size") int size) {
        Page<SharePostDTO> postDTOS = sharePostService.getShareByMemberID(id, PageRequest.of(page - 1, size));
        return new SharePostPage(page, size, postDTOS.getTotalPages(), postDTOS.getContent());
    }

    @PostMapping
    public SharePostDTO sharePost(@RequestBody SharePostDTO sharePostDTO) {
        return sharePostService.save(sharePostDTO);
    }

    @DeleteMapping("{id}")
    public SharePostDTO deleteShare(@PathVariable("id") long id) {
        return sharePostService.delete(id);
    }
}
