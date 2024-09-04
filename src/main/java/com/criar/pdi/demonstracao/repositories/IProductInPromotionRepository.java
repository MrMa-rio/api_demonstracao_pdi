package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.ProductInPromotion.ProductInPromotion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface IProductInPromotionRepository extends JpaRepository<ProductInPromotion, Integer> {

    Optional<ProductInPromotion> findByProductID (Integer productID);
}
