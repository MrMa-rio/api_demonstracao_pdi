package com.criar.pdi.demonstracao.specifications.Products;

import com.criar.pdi.demonstracao.DTOs.Product.ProductSearchDTO;
import com.criar.pdi.demonstracao.models.Product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification implements Specification<Product> {

    private final String name;
    private final String description;
    private final Double price;
    private final Integer quantity;
    private final Integer category;
    private final Integer storeID;
    private final String images;
    private final String specification;

    public ProductSpecification(ProductSearchDTO productSearchDTO) {
        this.name = productSearchDTO.name();
        this.description = productSearchDTO.description();
        this.price = productSearchDTO.price();
        this.quantity = productSearchDTO.quantity();
        this.category = productSearchDTO.category();
        this.storeID = productSearchDTO.storeID();
        this.images = productSearchDTO.images();
        this.specification = productSearchDTO.specification();
    }

    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + this.name + "%"));
        }

        if (description != null) {
            predicates.add(criteriaBuilder.like(root.get("description"), "%" + this.description + "%"));
        }
        if (price != null) {
            predicates.add(criteriaBuilder.equal(root.get("price"), this.price));
        }
        if (quantity != null) {
            predicates.add(criteriaBuilder.equal(root.get("quantity"), this.quantity));
        }
        if (category != null) {
            predicates.add(criteriaBuilder.equal(root.get("category"), this.category));
        }
        if (storeID != null) {
            predicates.add(criteriaBuilder.equal(root.get("store"), this.storeID));
        }
        if (images != null) {
            predicates.add(criteriaBuilder.like(root.get("images"), "%" + this.images + "%"));
        }

        if (specification != null) {
            predicates.add(criteriaBuilder.like(root.get("specification"), "%" + this.specification + "%"));
        }


        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
