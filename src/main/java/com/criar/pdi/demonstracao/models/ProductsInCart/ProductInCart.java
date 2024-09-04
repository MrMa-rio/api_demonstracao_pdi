package com.criar.pdi.demonstracao.models.ProductsInCart;

import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "products_in_cart_tbl")
public class ProductInCart {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String ID;
    @Column(name = "product_id")
    private String productID;
    private String quantity;
    private Double price;
    @Column(name = "shopping_cart_id")
    private String shoppingCartID;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public ProductInCart(ProductInCartDTO productInCartDTO){
        this.ID = productInCartDTO.ID();
        this.quantity = productInCartDTO.quantity();
        this.price = productInCartDTO.price();
        this.productID = productInCartDTO.productID();
        this.shoppingCartID = productInCartDTO.shoppingCartID();
    }

    public ProductInCartCommonDTO getCommonDTO(){
        return new ProductInCartCommonDTO(
                this.ID,
                this.productID,
                this.quantity,
                this.price,
                this.shoppingCartID,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }
    public void update(ProductInCartUpdateDTO productInCartUpdateDTO){
        setUpdateDate();
        if(productInCartUpdateDTO.productID() != null){
            this.productID = productInCartUpdateDTO.productID();
        }
        if (productInCartUpdateDTO.shoppingCartID() != null){
            this.shoppingCartID = productInCartUpdateDTO.shoppingCartID();
        }
        if (productInCartUpdateDTO.quantity() != null){
            this.quantity = productInCartUpdateDTO.quantity();
        }
        if (productInCartUpdateDTO.price() != null){
            this.price = productInCartUpdateDTO.price();
        }
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
