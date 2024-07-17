package com.criar.pdi.demonstracao.models.Review;

import com.criar.pdi.demonstracao.DTOs.Review.ReviewCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Review.ReviewDTO;
import com.criar.pdi.demonstracao.DTOs.Review.ReviewUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "products_available_tbl")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String ID;
    @Column(name = "product_id")
    private String productID;
    @Column(name = "store_id")
    private String storeID;
    @Column(name = "user_id")
    private String userID;
    private String ratingStar;
    private String comment;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Review(ReviewDTO reviewDTO) {
        this.ID = reviewDTO.ID();
        this.productID = reviewDTO.productID();
        this.storeID = reviewDTO.storeID();
        this.userID = reviewDTO.userID();
        this.ratingStar = reviewDTO.ratingStar();
        this.comment = reviewDTO.comment();
    }

    public ReviewCommonDTO getCommonDTO() {
        return new ReviewCommonDTO(
                this.ID,
                this.productID,
                this.storeID,
                this.userID,
                this.ratingStar,
                this.comment,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void setInclusionDate() {
        this.inclusionDate = LocalDateTime.now();
    }

    public void setUpdateDate() {
        this.updatedDate = LocalDateTime.now();
    }

    public void setExclusionDate() {
        this.exclusionDate = LocalDateTime.now();
    }

    public boolean isInactive() {
        return this.exclusionDate != null;
    }
    public void update(ReviewUpdateDTO reviewUpdateDTO){
        setUpdateDate();
        if(reviewUpdateDTO.ratingStar() != null){
            this.ratingStar = reviewUpdateDTO.ratingStar();
        }
        if(reviewUpdateDTO.comment() != null){
            this.comment = reviewUpdateDTO.comment();
        }
    }
}
