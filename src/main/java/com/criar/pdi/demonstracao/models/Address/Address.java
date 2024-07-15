package com.criar.pdi.demonstracao.models.Address;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
}
