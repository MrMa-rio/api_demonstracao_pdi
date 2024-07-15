package com.criar.pdi.demonstracao.models.Store;

import com.criar.pdi.demonstracao.DTOs.Store.StoreCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreDTO;
import com.criar.pdi.demonstracao.DTOs.Store.StoreUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "stores_tbl")
@Entity
@Getter
@NoArgsConstructor
public class Store {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID;
    private String name;
    @Column(name = "owner_id")
    private String ownerID;
    private String description;
    private String address;
    private String phone;
    private String region;
    private String cnpj;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Store(StoreDTO storeDTO){
        this.ID = storeDTO.ID();
        this.name = storeDTO.name();
        this.ownerID = storeDTO.ownerID();
        this.description = storeDTO.description();
        this.address = storeDTO.address();
        this.phone = storeDTO.phone();
        this.region = storeDTO.region();
        this.cnpj = storeDTO.cnpj();
    }

    public StoreCommonDTO getCommonDTO(){
        return new StoreCommonDTO(
                this.ID,
                this.name,
                this.ownerID,
                this.description,
                this.address,
                this.phone,
                this.region,
                this.cnpj,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void setInclusionDate() {
        this.inclusionDate = LocalDateTime.now();
    }

    public void update(StoreUpdateDTO storeUpdateDTO) {
        this.setUpdateDate();

        if(storeUpdateDTO.name() != null){
            this.name = storeUpdateDTO.name();
        }
        if (storeUpdateDTO.address() != null){
            this.address = storeUpdateDTO.address();
        }
        if(storeUpdateDTO.cnpj() != null){
            this.cnpj = storeUpdateDTO.cnpj();
        }
        if(storeUpdateDTO.ownerID() != null){
            this.ownerID = storeUpdateDTO.ownerID();
        }
        if(storeUpdateDTO.description() != null){
            this.description = storeUpdateDTO.description();
        }
        if(storeUpdateDTO.phone() != null){
            this.phone = storeUpdateDTO.phone();
        }
        if(storeUpdateDTO.region() != null){
            this.region = storeUpdateDTO.region();
        }
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
