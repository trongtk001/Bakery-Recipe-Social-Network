package com.example.bakeryrecipe.api.output;

import com.example.bakeryrecipe.dto.MessageDTO;

import java.util.List;

public class ListMessageOutput {
    private int page;
    private int size;
    private int totalPage;
    private List<MessageDTO> list;

    public ListMessageOutput(int page, int size, int totalPage, List<MessageDTO> list) {
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

    public List<MessageDTO> getList() {
        return list;
    }

    public void setList(List<MessageDTO> list) {
        this.list = list;
    }
}
