package com.criar.pdi.demonstracao.models.User;

import com.criar.pdi.demonstracao.DTOs.User.UserCommonDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

@Table(name = "users_tbl")
@Entity
@Getter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID;
    private String name;
    private String fullName;
    private String cpf;
    private String email;
    private String password;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;
    private  UserAccessLevel userAccessLevel;
    

    public User(UserDTO userDTO) {
        this.ID = userDTO.ID();
        this.name = userDTO.name();
        this.fullName = userDTO.fullName();
        this.email = userDTO.email();
        this.cpf = userDTO.cpf();
        this.password = new BCryptPasswordEncoder().encode(userDTO.password());
        this.userAccessLevel = userDTO.userAccessLevel();
    }

    public UserCommonDTO getUserCommon() {
        return new UserCommonDTO(
                this.ID,
                this.name,
                this.fullName,
                this.email,
                this.cpf,
                this.userAccessLevel,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void setInclusionDate(LocalDateTime inclusionDate) {
        this.inclusionDate = inclusionDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updatedDate = updateDate;
    }
    public void setExclusionDate(){
        this.exclusionDate = LocalDateTime.now();
    }

    public void update(UserUpdateDTO userUpdateDTO) {

        this.setUpdateDate(LocalDateTime.now());

        if(userUpdateDTO.name() != null){
            this.name = userUpdateDTO.name();
        }
        if(userUpdateDTO.fullName() != null){
            this.fullName = userUpdateDTO.fullName();
        }
        if(userUpdateDTO.email() != null){
            this.email = userUpdateDTO.email();
        }
        if(userUpdateDTO.cpf() != null){
            this.cpf = userUpdateDTO.cpf();
        }
        if(userUpdateDTO.userAccessLevel() != null){
            this.userAccessLevel = userUpdateDTO.userAccessLevel();
        }
    }
}
