package com.example.bakeryrecipe.dto;

import java.io.Serializable;
import java.util.Objects;

public class CommentDTO implements Serializable {
    private Long id;
    private String commentDetail;
    private String image;
    private String video;

    public CommentDTO() {
    }

    public CommentDTO(Long id, String commentDetail, String image, String video) {
        this.id = id;
        this.commentDetail = commentDetail;
        this.image = image;
        this.video = video;
    }

    public Long getId() {
        return id;
    }

    public String getCommentDetail() {
        return commentDetail;
    }

    public String getImage() {
        return image;
    }

    public String getVideo() {
        return video;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDTO entity = (CommentDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.commentDetail, entity.commentDetail) &&
                Objects.equals(this.image, entity.image) &&
                Objects.equals(this.video, entity.video);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, commentDetail, image, video);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "commentDetail = " + commentDetail + ", " +
                "image = " + image + ", " +
                "video = " + video + ")";
    }
}
