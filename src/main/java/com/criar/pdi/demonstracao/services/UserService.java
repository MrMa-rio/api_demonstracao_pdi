package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.User.UserCommonDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.User.UserDuplicateDataException.UserDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.User.UserGenericException.UserGenericException;
import com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException.UserIdentifyException;
import com.criar.pdi.demonstracao.exceptions.User.UserNotFoundException.UserNotFoundException;
import com.criar.pdi.demonstracao.models.User.User;
import com.criar.pdi.demonstracao.models.User.UserAccessLevel;
import com.criar.pdi.demonstracao.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    public UserCommonDTO getUserByID(String userID) {
        try {
            User user = iUserRepository.findById(Integer.valueOf(userID)).orElseThrow();
            return user.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException();
        } catch (NumberFormatException e) {
            throw new UserIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Page<UserCommonDTO> getUsers(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = iUserRepository.findAll(pageable);
        List<UserCommonDTO> userCommonDTOList = userPage.getContent().stream()
                .map(User::getCommonDTO).toList();
        return new PageImpl<>(userCommonDTOList, pageable, userPage.getTotalElements());
    }

    public Page<UserCommonDTO> getUsersByName(int page, int size, String name){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = iUserRepository.findAllByNameContains(name, pageable);
        List<UserCommonDTO> userCommonDTOList = userPage.getContent().stream()
                .map(User::getCommonDTO).toList();
        return new PageImpl<>(userCommonDTOList, pageable, userPage.getTotalElements());
    }
    public Page<UserCommonDTO> getUsersByUserAccessLevel(int page, int size, String userAccessLevel){
        Pageable pageable = PageRequest.of(page, size);
        Page<User> userPage = iUserRepository.findAllByUserAccessLevel(UserAccessLevel.get(Integer.valueOf(userAccessLevel)), pageable); //refatorar
        List<UserCommonDTO> userCommonDTOList = userPage.getContent().stream()
                .map(User::getCommonDTO).toList();
        return new PageImpl<>(userCommonDTOList, pageable, userPage.getTotalElements());
    }

    public UserCommonDTO setUser(UserDTO userDTO) {
        try {
            User user = new User(userDTO);
            user.setInclusionDate();
            iUserRepository.save(user);
            return user.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new UserDuplicateDataException();
        }
        catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public UserCommonDTO updateUser(UserUpdateDTO userUpdateDTO) {
        try {
            User user = iUserRepository.findById(Integer.valueOf(userUpdateDTO.ID())).orElseThrow();
            user.update(userUpdateDTO);
            iUserRepository.saveAndFlush(user);
            return getUserByID(userUpdateDTO.ID());
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteUser(String userID){
        try{
            User user = iUserRepository.findById(Integer.valueOf(userID)).orElseThrow();
            if(user.isInactive()){
                throw new UserGenericException("ESTE USUARIO JA ESTA INATIVADO!!");
            }
            user.setExclusionDate();
            iUserRepository.saveAndFlush(user);
        } catch (NoSuchElementException e){
            throw new UserNotFoundException();
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
