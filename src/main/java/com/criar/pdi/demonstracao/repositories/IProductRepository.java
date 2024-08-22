package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    @Procedure("prc_search_products")
    List<Product> searchProductsByParams(String name,
                                       String description,
                                       String price,
                                       String quantity,
                                       String category,
                                       String storeID,
                                       String images,
                                       String specification); //TODO realizar a paginacao da maneira correta

    Page<Product> findAllByStore(String storeID, Pageable pageable);

    Page<Product> findAllByStoreOrderByRatingStarDesc(String storeID, Pageable pageable);
}
