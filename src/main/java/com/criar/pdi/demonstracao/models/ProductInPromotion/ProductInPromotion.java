package com.criar.pdi.demonstracao.models.ProductInPromotion;

import com.criar.pdi.demonstracao.DTOs.ProductInPromotion.ProductInPromotionCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInPromotion.ProductInPromotionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "products_in_promotion_tbl")
@Getter
@NoArgsConstructor
public class ProductInPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;
    @Column(name = "product_id")
    private Integer productID;
    private Double price;
    @Column(name = "store_id")
    private Integer storeID;
    private LocalDate expirationDate;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public ProductInPromotion(ProductInPromotionDTO productInPromotionDTO) {
        this.ID = productInPromotionDTO.ID();
        this.productID = productInPromotionDTO.productID();
        this.price = productInPromotionDTO.price();
        this.storeID = productInPromotionDTO.storeID();
        this.expirationDate = productInPromotionDTO.expirationDate();
        this.eventStartDate = productInPromotionDTO.eventStartDate();
        this.eventEndDate = productInPromotionDTO.eventEndDate();
    }

    public ProductInPromotionCommonDTO getCommonDTO() {
        return new ProductInPromotionCommonDTO(
                this.ID,
                this.productID,
                this.price,
                this.storeID,
                this.expirationDate,
                this.eventStartDate,
                this.eventEndDate,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }
    public void setInclusionDate() {
        this.inclusionDate = LocalDateTime.now();
    }

    private void setUpdateDate() {
        this.updatedDate = LocalDateTime.now();
    }

    public void setExclusionDate() {
        this.exclusionDate = LocalDateTime.now();
    }

    public boolean isInactive() {
        return this.exclusionDate != null;
    }

}
