package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Delivery.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IDeliveryRepository extends JpaRepository<Delivery, Integer> {
}
