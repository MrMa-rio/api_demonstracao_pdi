package com.criar.pdi.demonstracao.repositories;


import com.criar.pdi.demonstracao.models.Payment.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPaymentRepository extends JpaRepository<Payment, Integer> {
}
