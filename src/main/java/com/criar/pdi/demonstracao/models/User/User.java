package com.criar.pdi.demonstracao.models.User;

import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.models.Adress.Adress;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "users_tbl")
@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id @Column(name = "id") @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID;
    private String name;
    private String fullName;
    private String cpf;
    private String email;
    private String password;
    private Integer adress;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;
    private UserAccessLevel userAccessLevel;


    public User(UserDTO userDTO){
        this.ID = userDTO.ID();
        this.name = userDTO.name();
        this.fullName = userDTO.fullName();
        this.email = userDTO.email();
        this.cpf = userDTO.cpf();
        this.password = userDTO.password();
        this.adress = userDTO.adress();
        this.inclusionDate = userDTO.inclusionDate();
        this.updatedDate = userDTO.updatedDate();
        this.exclusionDate = userDTO.exclusionDate();
        this.userAccessLevel = userDTO.userAccessLevel();
    }
}
