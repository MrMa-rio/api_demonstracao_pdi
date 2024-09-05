package com.criar.pdi.demonstracao.models.ProductInOrder;

import com.criar.pdi.demonstracao.DTOs.ProductInOrder.ProductInOrderCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInOrder.ProductInOrderDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "products_in_order_tbl")
public class ProductInOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer ID;
    @Column(name = "product_id")
    private Integer productID;
    private Integer quantity;
    private Double price;
    @Column(name = "order_id")
    private Integer orderID;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public ProductInOrder(ProductInOrderDTO productInOrderDTO){
        this.ID = productInOrderDTO.ID();
        this.quantity = productInOrderDTO.quantity();
        this.productID = productInOrderDTO.productID();
        this.orderID = productInOrderDTO.orderID();
        this.price = productInOrderDTO.price();
    }

    public ProductInOrderCommonDTO getCommonDTO(){
        return new ProductInOrderCommonDTO(
                this.ID,
                this.productID,
                this.quantity,
                this.orderID,
                this.price,
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
