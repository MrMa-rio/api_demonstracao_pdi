package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReviewRepository extends JpaRepository<Review, Integer> {
}
