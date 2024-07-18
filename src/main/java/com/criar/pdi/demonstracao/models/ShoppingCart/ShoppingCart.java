package com.criar.pdi.demonstracao.models.ShoppingCart;

import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartCommonDTO;
import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartDTO;
import com.criar.pdi.demonstracao.DTOs.ShoppingCart.ShoppingCartUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "shopping_carts_tbl")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String ID;
    @Column(name = "user_id")
    private String userID;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;


    public ShoppingCart(ShoppingCartDTO shoppingCartDTO){
        this.ID = shoppingCartDTO.ID();
        this.userID = shoppingCartDTO.userID();
    }
    public ShoppingCartCommonDTO getCommonDTO(){
        return new ShoppingCartCommonDTO(
                this.ID,
                this.userID,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void update(ShoppingCartUpdateDTO shoppingCartUpdateDTO){
        this.setUpdateDate();
        if(shoppingCartUpdateDTO.userID() != null){
            this.userID = shoppingCartUpdateDTO.userID();
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
