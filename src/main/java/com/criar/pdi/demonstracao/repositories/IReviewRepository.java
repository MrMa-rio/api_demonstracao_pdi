package com.criar.pdi.demonstracao.repositories;

import com.criar.pdi.demonstracao.models.Review.Review;
import com.criar.pdi.demonstracao.models.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface IReviewRepository extends JpaRepository<Review, Integer> {
}
