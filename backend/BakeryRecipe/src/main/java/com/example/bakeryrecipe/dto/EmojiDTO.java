package com.example.bakeryrecipe.dto;


import java.io.Serializable;

public class EmojiDTO implements Serializable {

    private Long id;

    private MemberDTO member;

    private PostDTO post;

    public EmojiDTO() {
    }

    public EmojiDTO(Long id, MemberDTO member, PostDTO post) {
        this.id = id;
        this.member = member;
        this.post = post;
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

    public PostDTO getPost() {
        return post;
    }

    public void setPost(PostDTO post) {
        this.post = post;
    }
}
