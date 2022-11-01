package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    @Query("select m from Message m " +
            "where (m.memberSender.id = ?1 and m.memberReceiver.id = ?2) or (m.memberReceiver.id = ?1 and m.memberSender.id = ?2)")
    List<Message> findAllByMemberSender_IdAndMemberReceiver_IdOrMemberReceiver_IdAndMemberSender_Id(long senderId, long receiverID, Pageable pageable);
}