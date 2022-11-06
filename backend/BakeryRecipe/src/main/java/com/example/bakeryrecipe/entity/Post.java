package com.example.bakeryrecipe.entity;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.time.Instant;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@FilterDef(name = "likeFilter")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @CreatedDate
    @Column(name = "create_date", nullable = false)
    private Instant createDate;

    @Column(name = "post_body", columnDefinition = "nvarchar(3000)",nullable = false)
    private String postBody;

    @OneToOne(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Recipe recipe;

    @Filter(name = "likeFilter", condition = "status = 1")
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Like> likes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
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

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}