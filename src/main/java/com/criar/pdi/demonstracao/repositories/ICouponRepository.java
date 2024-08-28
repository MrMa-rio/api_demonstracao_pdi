package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICouponRepository extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findByCouponCode(String integer);
}
