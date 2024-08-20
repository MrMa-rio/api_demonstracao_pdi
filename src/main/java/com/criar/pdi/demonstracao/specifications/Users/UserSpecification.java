package com.criar.pdi.demonstracao.specifications.Users;

import com.criar.pdi.demonstracao.DTOs.User.UserSearchDTO;
import com.criar.pdi.demonstracao.models.User.User;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification implements Specification<User> {

    private final String name;
    private final String fullName;
    private final String email;
    private final String cpf;
    private final Integer userAccessLevel;

    public UserSpecification(UserSearchDTO userSearchDTO) {
        this.name = userSearchDTO.name();
        this.fullName = userSearchDTO.fullName();
        this.email = userSearchDTO.email();
        this.cpf = userSearchDTO.cpf();
        this.userAccessLevel = userSearchDTO.userAccessLevel();
    }

    @Override
    public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(criteriaBuilder.like(root.get("name"), "%" + this.name + "%"));
        }

        if (fullName != null) {
            predicates.add(criteriaBuilder.like(root.get("fullName"), "%" + this.fullName + "%"));
        }
        if (email != null) {
            predicates.add(criteriaBuilder.like(root.get("email"), "%" + this.email + "%"));
        }
        if (cpf != null) {
            predicates.add(criteriaBuilder.like(root.get("cpf"), "%" + this.cpf + "%"));
        }

        if (userAccessLevel != null) {
            predicates.add(criteriaBuilder.equal(root.get("userAccessLevel"), this.userAccessLevel));
        }
        return criteriaBuilder.and(predicates.toArray(Predicate[]::new));
    }
}
