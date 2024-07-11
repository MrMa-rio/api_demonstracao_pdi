package com.criar.pdi.demonstracao.models.Adress;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "adress_tbl")
@Entity
@Getter
@NoArgsConstructor
public class Adress {

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
