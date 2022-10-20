package com.example.bakeryrecipe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

public class PostDTO implements Serializable {
    private  Long id;
    private  MemberDTO member;
    private  Instant createDate;
    private  String postBody;
    private RecipeDTO recipe;
    private List<PostImageDTO> postImages;
    private List<PostVideoDTO> postVideos;

    private long numberLike;
    public PostDTO() {
    }

    public PostDTO(Long id, MemberDTO member, Instant createDate, String postBody, RecipeDTO recipe, List<PostImageDTO> postImages, List<PostVideoDTO> postVideos) {
        this.id = id;
        this.member = member;
        this.createDate = createDate;
        this.postBody = postBody;
        this.recipe = recipe;
        this.postImages = postImages;
        this.postVideos = postVideos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberDTO getMember() {
        return member;
    }

    public void setMember(MemberDTO member) {
        this.member = member;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

    public String getPostBody() {
        return postBody;
    }

    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public RecipeDTO getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeDTO recipe) {
        this.recipe = recipe;
    }

    public List<PostImageDTO> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImageDTO> postImages) {
        this.postImages = postImages;
    }

    public List<PostVideoDTO> getPostVideos() {
        return postVideos;
    }

    public void setPostVideos(List<PostVideoDTO> postVideos) {
        this.postVideos = postVideos;
    }

    public long getNumberLike() {
        return numberLike;
    }

    public void setNumberLike(long numberLike) {
        this.numberLike = numberLike;
    }
}
