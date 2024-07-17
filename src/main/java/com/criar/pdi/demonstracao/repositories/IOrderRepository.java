package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<Order, Integer> {
}
