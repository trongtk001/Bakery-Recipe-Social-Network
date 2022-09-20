package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}