package com.example.bakeryrecipe.dto;

<<<<<<< HEAD
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

public class PostDTO implements Serializable {
    private final Long id;
    private final Instant createDate;
    private final String postBody;

    public PostDTO(Long id, Instant createDate, String postBody) {
        this.id = id;
        this.createDate = createDate;
        this.postBody = postBody;
    }
=======
import com.example.bakeryrecipe.entity.Member;
import com.example.bakeryrecipe.entity.PostImage;
import com.example.bakeryrecipe.entity.PostVideo;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

public class PostDTO {

    private Long id;
    private Member member;
    private Instant createDate;
    private String postBody;
    private List<PostImage> postImages;
    private List<PostVideo> postVideos;
>>>>>>> f06ce5723e113d2aa978f5e43cc49a389dc54fb0

    public Long getId() {
        return id;
    }

<<<<<<< HEAD
=======
    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

>>>>>>> f06ce5723e113d2aa978f5e43cc49a389dc54fb0
    public Instant getCreateDate() {
        return createDate;
    }

<<<<<<< HEAD
=======
    public void setCreateDate(Instant createDate) {
        this.createDate = createDate;
    }

>>>>>>> f06ce5723e113d2aa978f5e43cc49a389dc54fb0
    public String getPostBody() {
        return postBody;
    }

<<<<<<< HEAD
    public Long getMember() {
        return createBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDTO entity = (PostDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.createDate, entity.createDate) &&
                Objects.equals(this.postBody, entity.postBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createDate, postBody);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "createDate = " + createDate + ", " +
                "postBody = " + postBody + ")";
=======
    public void setPostBody(String postBody) {
        this.postBody = postBody;
    }

    public List<PostImage> getPostImages() {
        return postImages;
    }

    public void setPostImages(List<PostImage> postImages) {
        this.postImages = postImages;
    }

    public List<PostVideo> getPostVideos() {
        return postVideos;
    }

    public void setPostVideos(List<PostVideo> postVideos) {
        this.postVideos = postVideos;
>>>>>>> f06ce5723e113d2aa978f5e43cc49a389dc54fb0
    }
}
