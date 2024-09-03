package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.CouponRedemption.CouponRedemption;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICouponRedemptionRepository extends JpaRepository<CouponRedemption, Integer> {
    Page<CouponRedemption> findAllByUserIdAndCouponId(String userId, String couponId, Pageable pageable);

    Optional<CouponRedemption> findByUserIdAndCouponId(String userId, String couponId);
}
