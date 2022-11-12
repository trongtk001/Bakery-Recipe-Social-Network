package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.username = ?1")
    Optional<Member> findOneByUsername(String username);

    Optional<Member> findOneByEmail(String email);

    @Query("select m from Member m where m.name like concat('%', ?1, '%')")
    Page<Member> findAllByNameContaining(String name, Pageable pageable);

    Member findMemberById(Long id);


    Member findMemberByVerificationCodeOrUsername(String code, String username);

    @Query("select m from Member m where m.status = ?1")
    List<Member> findAllByStatus(Byte status);

    @Modifying
    @Transactional
    @Query(nativeQuery=true,value = "select top 15 m.member_id, m.username,m.name,m.avatar,m.dob,m.email,m.status,m.password,m.time_check,m.verification_code,COUNT(p.post_id) as sl from Member m , Post p where m.member_id = p.member_id\n" +
            "group by m.member_id, m.username,m.name,m.avatar,m.dob,m.email,m.status,m.password,m.time_check,m.verification_code\n" +
            "order by COUNT(p.post_id) DESC")
    List<Member> findTopByCountPost();
}