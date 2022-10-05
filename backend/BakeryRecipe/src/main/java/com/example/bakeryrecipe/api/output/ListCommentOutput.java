package com.example.bakeryrecipe.api.output;

import com.example.bakeryrecipe.dto.CommentDTO;

import java.util.ArrayList;
import java.util.List;

public class ListCommentOutput {
    private int page;
    private int size;
    private int totalPage;
    private List<CommentDTO> list = new ArrayList<>();

    public ListCommentOutput() {
    }

    public ListCommentOutput(int page, int size, int totalPage, List<CommentDTO> list) {
        this.page = page;
        this.size = size;
        this.totalPage = totalPage;
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<CommentDTO> getList() {
        return list;
    }

    public void setList(List<CommentDTO> list) {
        this.list = list;
    }
}
