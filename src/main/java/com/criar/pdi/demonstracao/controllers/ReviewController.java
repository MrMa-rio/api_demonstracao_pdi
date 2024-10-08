package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.Review.ReviewCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Review.ReviewDTO;
import com.criar.pdi.demonstracao.DTOs.Review.ReviewUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Review.ReviewDuplicateDataException.ReviewDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Review.ReviewGenericException.ReviewGenericException;
import com.criar.pdi.demonstracao.exceptions.Review.ReviewIdentifyException.ReviewIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Review.ReviewNotFoundException.ReviewNotFoundException;
import com.criar.pdi.demonstracao.services.ReviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Review")
public class ReviewController {
    @Autowired
    ReviewService reviewService;

    @GetMapping("/{reviewID}")
    public ResponseEntity<ResponseBody> getReview(@PathVariable @Valid String reviewID) {
        try {
            return ResponseEntity.ok(new ResponseBody(200, reviewService.getReviewByID(reviewID)));
        } catch (ReviewNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (ReviewIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @PostMapping
    public ResponseEntity<?> setReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        try {
            ReviewCommonDTO reviewCommonDTO = reviewService.setReview(reviewDTO);
            return ResponseEntity.ok(new ResponseBody(200, reviewCommonDTO));
        } catch (ReviewDuplicateDataException e) {
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateReview(@RequestBody @Valid ReviewUpdateDTO reviewUpdateDTO) {
        try {
            ReviewCommonDTO reviewCommonDTO = reviewService.updateReview(reviewUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, reviewCommonDTO));
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }

    @DeleteMapping("/{reviewID}")
    public ResponseEntity<ResponseBody> deleteLogicalReview(
            @PathVariable String reviewID
    ) {
        try {
            reviewService.deleteReview(reviewID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("COMENTARIO INATIVADO COM SUCESSO!!")));
        } catch (ReviewNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (ReviewGenericException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
