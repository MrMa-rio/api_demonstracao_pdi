package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Review.ReviewCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Review.ReviewDTO;
import com.criar.pdi.demonstracao.DTOs.Review.ReviewUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.Review.ReviewDuplicateDataException.ReviewDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Review.ReviewGenericException.ReviewGenericException;
import com.criar.pdi.demonstracao.exceptions.Review.ReviewIdentifyException.ReviewIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Review.ReviewNotFoundException.ReviewNotFoundException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException.StoreGenericException;
import com.criar.pdi.demonstracao.models.Review.Review;
import com.criar.pdi.demonstracao.repositories.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

@Service
public class ReviewService {
    @Autowired
    IReviewRepository iReviewRepository;

    public ReviewCommonDTO getReviewByID(String reviewID) {
        try {
            Review review = iReviewRepository.findById(Integer.valueOf(reviewID)).orElseThrow();
            return review.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new ReviewNotFoundException();
        } catch (NumberFormatException e) {
            throw new ReviewIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public ReviewCommonDTO setReview(ReviewDTO reviewDTO) {
        try {
            Review review = new Review(reviewDTO);
            review.setInclusionDate();
            iReviewRepository.save(review);
            return review.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new ReviewDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ReviewCommonDTO updateReview(ReviewUpdateDTO reviewUpdateDTO) {
        try {
            Review review = iReviewRepository.findById(Integer.valueOf(reviewUpdateDTO.ID())).orElseThrow();
            review.update(reviewUpdateDTO);
            iReviewRepository.saveAndFlush(review);
            return getReviewByID(reviewUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new ReviewNotFoundException();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReview(String reviewID) {
        try {
            Review review = iReviewRepository.findById(Integer.valueOf(reviewID)).orElseThrow();
            if(review.isInactive()){
                throw new ReviewGenericException("ESTE COMENTARIO JA ESTA INATIVADO!!");
            }
            review.setExclusionDate();
            iReviewRepository.saveAndFlush(review);
        } catch (NoSuchElementException e) {
            throw new ReviewNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
