package com.example.bakeryrecipe.api.output;

import com.example.bakeryrecipe.dto.PostDTO;

import java.util.ArrayList;
import java.util.List;

public class ListPostOutput {
    private int page;
    private int size;
    private int totalPage;
    private List<PostDTO> list;

    public ListPostOutput() {
    }

    public ListPostOutput(int page, int size, int totalPage, List<PostDTO> list) {
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

    public List<PostDTO> getList() {
        return list;
    }

    public void setList(List<PostDTO> list) {
        this.list = list;
    }
}
