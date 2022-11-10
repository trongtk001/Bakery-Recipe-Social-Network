package com.example.bakeryrecipe.api.output;

import java.util.List;

public abstract class PageResponse<T> {

    private int page;
    private int size;
    private int totalPage;
    private List<T> list;

    public PageResponse() {
    }

    public PageResponse(int page, int size, int totalPage, List<T> list) {
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
