package com.criar.pdi.demonstracao.models.Product;

import com.criar.pdi.demonstracao.DTOs.Product.ProductCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Product.ProductDTO;
import com.criar.pdi.demonstracao.DTOs.Product.ProductUpdateDTO;
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

    public ProductCommonDTO getCommonDTO() {
        return new ProductCommonDTO(
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
        this.updatedDate = LocalDateTime.now();
    }

    public void setExclusionDate() {
        this.exclusionDate = LocalDateTime.now();
    }

    public boolean isInactive() {
        return this.exclusionDate != null;
    }

    public void update(ProductUpdateDTO productUpdateDTO) {
        setUpdateDate();
        if(productUpdateDTO.name() != null){
            this.name = productUpdateDTO.name();
        }
        if(productUpdateDTO.description() != null){
            this.description = productUpdateDTO.description();
        }
        if(productUpdateDTO.price() != null){
            this.price = Double.valueOf(productUpdateDTO.price());
        }
        if(productUpdateDTO.quantity() != null){
            this.quantity = productUpdateDTO.quantity();
        }
        if(productUpdateDTO.category() != null){
            this.category = productUpdateDTO.category();
        }
        if(productUpdateDTO.store() != null){
            this.store = productUpdateDTO.store();
        }
        if(productUpdateDTO.images() != null){
            this.images = productUpdateDTO.images();
        }
        if(productUpdateDTO.specification() != null){
            this.specification = productUpdateDTO.specification();
        }
    }
}
