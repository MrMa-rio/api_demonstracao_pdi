package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductRepository extends JpaRepository<Product, Integer> {
}
