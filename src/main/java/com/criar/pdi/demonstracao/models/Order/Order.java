package com.criar.pdi.demonstracao.models.Order;

import com.criar.pdi.demonstracao.DTOs.Order.OrderCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Order.OrderDTO;
import com.criar.pdi.demonstracao.DTOs.Order.OrderUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders_tbl")
@Getter
@NoArgsConstructor
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String ID;
    @Column(name = "user_id")
    private String userID;
    private String shoppingCart;
    private String deliveryAddress;
    private String status;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Order(OrderDTO orderDTO){
        this.ID = orderDTO.ID();
        this.userID = orderDTO.userID();
        this.shoppingCart = orderDTO.shoppingCart();
        this.deliveryAddress = orderDTO.deliveryAddress();
        this.status = orderDTO.status();
    }

    public OrderCommonDTO getCommonDTO(){
        return new OrderCommonDTO(
                this.ID,
                this.userID,
                this.shoppingCart,
                this.deliveryAddress,
                this.status,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }
    public void update(OrderUpdateDTO orderUpdateDTO){
        setUpdateDate();
        if(orderUpdateDTO.status() != null){
            this.status = orderUpdateDTO.status() ;
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
