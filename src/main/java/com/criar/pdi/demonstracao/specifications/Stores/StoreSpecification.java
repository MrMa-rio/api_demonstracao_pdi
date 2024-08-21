package com.criar.pdi.demonstracao.specifications.Stores;

import com.criar.pdi.demonstracao.DTOs.Store.StoreSearchDTO;
import com.criar.pdi.demonstracao.models.Store.Store;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class StoreSpecification implements Specification<Store> {

    private final String name;
    private final Integer ownerID;
    private final String description;
    private final Integer address;
    private final Integer region;
    private final Double ratingStar;
    private final String cnpj;

    public StoreSpecification(StoreSearchDTO storeSearchDTO) {
        this.name = storeSearchDTO.name();
        this.ownerID = storeSearchDTO.ownerID();
        this.description = storeSearchDTO.description();
        this.address = storeSearchDTO.address();
        this.region = storeSearchDTO.region();
        this.ratingStar = storeSearchDTO.ratingStar();
        this.cnpj = storeSearchDTO.cnpj();
    }


    @Override
    public Predicate toPredicate(Root<Store> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + this.name + "%"));
        }
        if (ownerID != null) {
            predicates.add(criteriaBuilder.equal(root.get("ownerID"), this.ownerID));
        }
        if (description != null) {
            predicates.add(criteriaBuilder.like(root.get("description"), "%" + this.description + "%"));
        }
        if (address != null) {
            predicates.add(criteriaBuilder.equal(root.get("address"), this.address));
        }
        if (region != null) {
            predicates.add(criteriaBuilder.equal(root.get("region"), this.region));
        }
        if (ratingStar != null) {
            predicates.add(criteriaBuilder.equal(root.get("ratingStar"), this.ratingStar));
        }
        if (cnpj != null) {
            predicates.add(criteriaBuilder.like(root.get("cnpj"), "%" + this.cnpj + "%"));
        }

        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
