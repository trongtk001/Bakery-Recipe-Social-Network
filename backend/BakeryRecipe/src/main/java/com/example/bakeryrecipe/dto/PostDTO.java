package com.example.bakeryrecipe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDTO implements Serializable {
    private  Long id;
    private  MemberDTO member;
    private  Instant createDate;
    private  String postBody;

    public PostDTO() {
    }

    public PostDTO(Long id, MemberDTO member, Instant createDate, String postBody) {
        this.id = id;
        this.member = member;
        this.createDate = createDate;
        this.postBody = postBody;
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
        this.member = new MemberDTO(member.getId());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDTO entity = (PostDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.member, entity.member) &&
                Objects.equals(this.createDate, entity.createDate) &&
                Objects.equals(this.postBody, entity.postBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, member, createDate, postBody);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "member = " + member + ", " +
                "createDate = " + createDate + ", " +
                "postBody = " + postBody + ")";
    }
}
