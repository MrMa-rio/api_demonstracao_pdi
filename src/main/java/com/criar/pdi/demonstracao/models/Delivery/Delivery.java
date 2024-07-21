package com.criar.pdi.demonstracao.models.Delivery;

import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryDTO;
import com.criar.pdi.demonstracao.DTOs.Delivery.DeliveryUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_tbl")
@NoArgsConstructor
@Getter
public class Delivery {
    @Column(name = "id")
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID;
    @Column(name = "order_id")
    private String orderID;
    private String status;
    private LocalDateTime shippingDate;
    private LocalDateTime deliveryDate;
    private String deliveryService;
    private String trackingNumber;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Delivery(DeliveryDTO deliveryDTO){
        this.ID = deliveryDTO.ID();
        this.orderID = deliveryDTO.orderID();
        this.status = deliveryDTO.status();
        this.shippingDate = deliveryDTO.shippingDate();
        this.deliveryDate = deliveryDTO.deliveryDate();
        this.deliveryService = deliveryDTO.deliveryService();
        this.trackingNumber = deliveryDTO.trackingNumber();
    }

    public DeliveryCommonDTO getCommonDTO(){
        return new DeliveryCommonDTO(
                this.ID,
                this.orderID,
                this.status,
                this.shippingDate,
                this.deliveryDate,
                this.deliveryService,
                this.trackingNumber,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void update(DeliveryUpdateDTO deliveryUpdateDTO){
        setUpdateDate();
        if(deliveryUpdateDTO.orderID() != null){
            this.orderID = deliveryUpdateDTO.orderID();
        }
        if(deliveryUpdateDTO.status() != null){
            this.status = deliveryUpdateDTO.status();
        }
        if(deliveryUpdateDTO.shippingDate() != null){
            this.shippingDate = deliveryUpdateDTO.shippingDate();
        }
        if(deliveryUpdateDTO.deliveryDate() != null){
            this.deliveryDate = deliveryUpdateDTO.deliveryDate();
        }
        if(deliveryUpdateDTO.deliveryService() != null){
            this.deliveryService = deliveryUpdateDTO.deliveryService();
        }
        if(deliveryUpdateDTO.trackingNumber() != null){
            this.trackingNumber = deliveryUpdateDTO.trackingNumber();
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
