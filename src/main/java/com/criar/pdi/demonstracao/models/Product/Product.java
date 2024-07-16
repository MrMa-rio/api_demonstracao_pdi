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
        this.ID = productDTO.ID();
        this.name = productDTO.name();
        this.description = productDTO.description();
        this.price = productDTO.price();
        this.quantity = productDTO.quantity();
        this.category = productDTO.category();
        this.store = productDTO.store();
        this.images = productDTO.images();
        this.specification = productDTO.specification();
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

    public void setInclusionDate() {
        this.inclusionDate = LocalDateTime.now();
    }

    public void setUpdateDate() {
        this.updatedDate = this.inclusionDate = LocalDateTime.now();
    }

    public void setExclusionDate() {
        this.exclusionDate = LocalDateTime.now();
    }

    public boolean isInactive() {
        return true;
    }

    public void update(ProductDTO productDTO) {
        setUpdateDate();
        if(productDTO.name() != null){
            this.name = productDTO.name();
        }
        if(productDTO.description() != null){
            this.description = productDTO.description();
        }
        if(productDTO.price() != null){
            this.price = productDTO.price();
        }
        if(productDTO.quantity() != null){
            this.quantity = productDTO.quantity();
        }
        if(productDTO.category() != null){
            this.category = productDTO.category();
        }
        if(productDTO.store() != null){
            this.store = productDTO.store();
        }
        if(productDTO.images() != null){
            this.images = productDTO.images();
        }
        if(productDTO.specification() != null){
            this.specification = productDTO.specification();
        }
    }
}
