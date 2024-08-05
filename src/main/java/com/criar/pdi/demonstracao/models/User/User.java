package com.criar.pdi.demonstracao.models.User;

import com.criar.pdi.demonstracao.DTOs.User.UserCommonDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserUpdateDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;

@Table(name = "users_tbl")
@Entity
@Getter
@NoArgsConstructor
public class User implements UserDetails {
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

    public UserCommonDTO getCommonDTO() {
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

    public void setInclusionDate() {
        this.inclusionDate = LocalDateTime.now();
    }

    public void setUpdateDate() {
        this.updatedDate = LocalDateTime.now();
    }
    public void setExclusionDate(){
        this.exclusionDate = LocalDateTime.now();
    }

    public void update(UserUpdateDTO userUpdateDTO) {

        this.setUpdateDate();

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
        if(userUpdateDTO.password() != null){
            this.password = new BCryptPasswordEncoder().encode(userUpdateDTO.password());
        }
        if(userUpdateDTO.userAccessLevel() != null){
            this.userAccessLevel = userUpdateDTO.userAccessLevel();
        }
    }

    public boolean isInactive() {
        return this.exclusionDate != null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
