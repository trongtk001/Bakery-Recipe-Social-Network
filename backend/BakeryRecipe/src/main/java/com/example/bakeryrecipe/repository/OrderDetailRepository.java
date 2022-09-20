package com.example.bakeryrecipe.repository;

import com.example.bakeryrecipe.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}