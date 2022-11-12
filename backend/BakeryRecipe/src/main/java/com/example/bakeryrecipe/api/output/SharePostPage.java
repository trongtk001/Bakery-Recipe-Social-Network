package com.example.bakeryrecipe.api.output;

import com.example.bakeryrecipe.dto.SharePostDTO;

import java.util.List;

public class SharePostPage extends PageResponse<SharePostDTO>{

    public SharePostPage() {
    }

    public SharePostPage(int page, int size, int totalPage, List<SharePostDTO> list) {
        super(page, size, totalPage, list);
    }
}
