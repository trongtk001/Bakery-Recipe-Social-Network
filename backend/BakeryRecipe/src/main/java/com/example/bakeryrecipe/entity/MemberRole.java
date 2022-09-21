package com.example.bakeryrecipe.entity;

import javax.persistence.*;

@Entity
@Table(name = "Member_Role")
public class MemberRole {
    @EmbeddedId
    private MemberRoleId id;

    @MapsId("memberId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @MapsId("roleId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    public MemberRole() {
    }

    public MemberRole(Member member, Role role) {
        this.id = new MemberRoleId(member.getId(), role.getId());
        this.member = member;
        this.role = role;
    }

    public MemberRoleId getId() {
        return id;
    }

    public void setId(MemberRoleId id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}