package com.criar.pdi.demonstracao.models.Payment;


import com.criar.pdi.demonstracao.DTOs.Payment.PaymentCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Payment.PaymentDTO;
import com.criar.pdi.demonstracao.DTOs.Payment.PaymentUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Table(name = "payments_tbl")
public class Payment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String ID;
    @Column(name = "order_id")
    private String orderID;
    private String methodPayment;
    private String status;
    private String valuePayment;
    private LocalDateTime paymentDate;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Payment(PaymentDTO paymentDTO){
        this.ID = paymentDTO.ID();
        this.orderID = paymentDTO.orderID();
        this.methodPayment = paymentDTO.methodPayment();
        this.status = paymentDTO.status();
        this.valuePayment = paymentDTO.valuePayment();
        this.paymentDate = paymentDTO.paymentDate();
    }
    public PaymentCommonDTO getCommonDTO(){
        return new PaymentCommonDTO(
                this.ID,
                this.orderID,
                this.methodPayment,
                this.status,
                this.valuePayment,
                this.paymentDate,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void update(PaymentUpdateDTO paymentUpdateDTO){
        setUpdateDate();
        if(paymentUpdateDTO.status() != null){
            this.status = paymentUpdateDTO.status();
        }
        if(paymentUpdateDTO.methodPayment() != null){
            this.methodPayment = paymentUpdateDTO.methodPayment();
        }
        if(paymentUpdateDTO.valuePayment() != null){
            this.valuePayment = paymentUpdateDTO.valuePayment();
        }
        if(paymentUpdateDTO.paymentDate() != null){
            this.paymentDate = paymentUpdateDTO.paymentDate();
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
