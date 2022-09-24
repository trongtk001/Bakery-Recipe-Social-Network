package com.example.bakeryrecipe.dto;

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

    public Long getId() {
        return id;
    }

    public Instant getCreateDate() {
        return createDate;
    }

    public String getPostBody() {
        return postBody;
    }

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
    }
}
