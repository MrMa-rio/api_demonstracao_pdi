package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.DTOs.ProductInOrder.ProductInOrderCommonDTO;
import com.criar.pdi.demonstracao.models.ProductInOrder.ProductInOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface IProductInOrderRepository extends JpaRepository<ProductInOrder, Integer> {
    Page<ProductInOrderCommonDTO> findAllByOrderID(Integer orderID, Pageable pageable);
}
