package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<User, Integer> {
}
