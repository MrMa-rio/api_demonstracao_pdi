package com.criar.pdi.demonstracao.models.Address;

import com.criar.pdi.demonstracao.DTOs.Address.AddressCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Address.AddressDTO;
import com.criar.pdi.demonstracao.DTOs.Address.AddressUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "address_tbl")
@Entity
@Getter
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private String ID;
    private String number;
    private String street;
    private String neighborhood;
    @Column(name = "user_id")
    private String userID;
    private String complement;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Address(AddressDTO addressDTO){
        this.ID = addressDTO.ID();
        this.number = addressDTO.number();
        this.street = addressDTO.street();
        this.neighborhood = addressDTO.neighborhood();
        this.userID = addressDTO.userID();
        this.complement = addressDTO.complement();
        this.city = addressDTO.city();
        this.state = addressDTO.state();
        this.zipCode = addressDTO.zipCode();
        this.country = addressDTO.country();
    }

    public AddressCommonDTO getCommonDTO() {
        return new AddressCommonDTO(
                this.ID,
                this.number,
                this.street,
                this.neighborhood,
                this.userID,
                this.complement,
                this.city,
                this.state,
                this.zipCode,
                this.country,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void update(AddressUpdateDTO addressUpdateDTO) {
        setUpdateDate();
        if (addressUpdateDTO.number() != null) {
            this.number = addressUpdateDTO.number();
        }
        if (addressUpdateDTO.street() != null) {
            this.street = addressUpdateDTO.street();
        }
        if (addressUpdateDTO.neighborhood() != null) {
            this.neighborhood = addressUpdateDTO.neighborhood();
        }
        if (addressUpdateDTO.complement() != null) {
            this.complement = addressUpdateDTO.complement();
        }
        if (addressUpdateDTO.city() != null) {
            this.city = addressUpdateDTO.city();
        }
        if (addressUpdateDTO.state() != null) {
            this.state = addressUpdateDTO.state();
        }
        if (addressUpdateDTO.zipCode() != null) {
            this.zipCode = addressUpdateDTO.zipCode();
        }
        if (addressUpdateDTO.country() != null) {
            this.country = addressUpdateDTO.country();
        }

    }

    private void setUpdateDate() {
        this.updatedDate = LocalDateTime.now();
    }

    public void setInclusionDate() {
        this.inclusionDate = LocalDateTime.now();
    }

    public void setExclusionDate() {
        this.exclusionDate = LocalDateTime.now();
    }

    public boolean isInactive() {
        return this.exclusionDate != null;
    }
}
