package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Friend;
import com.example.bakeryrecipe.entity.FriendId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends JpaRepository<Friend, FriendId> {
}