package com.criar.pdi.demonstracao.models.Product;

import com.criar.pdi.demonstracao.DTOs.Product.ProductDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "products_tbl")
@NoArgsConstructor
@Getter
public class Product {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID;
    private String name;
    private String description;
    private Double price;
    private Integer quantity;
    private String category;
    private String store;
    private String images;
    private String specification;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Product(ProductDTO productDTO) {
    }

    public ProductDTO getCommonDTO() {
        return new ProductDTO(
                this.ID,
                this.name,
                this.description,
                this.price,
                this.quantity,
                this.category,
                this.store,
                this.images,
                this.specification,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void setExclusionDate() {
    }

    public boolean isInactive() {
    }

    public void update(ProductDTO productDTO) {
    }

    public void setInclusionDate() {
    }
}
