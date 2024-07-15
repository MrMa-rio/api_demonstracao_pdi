package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.User.UserCommonDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserUpdateDTO;
import com.criar.pdi.demonstracao.exceptions.User.UserDuplicateDataException.UserDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException.UserIdentifyException;
import com.criar.pdi.demonstracao.exceptions.User.UserNotFoundException.UserNotFoundException;
import com.criar.pdi.demonstracao.models.User.User;
import com.criar.pdi.demonstracao.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    public UserCommonDTO getUserByID(String userID) {
        try {
            User user = iUserRepository.findById(Integer.valueOf(userID)).orElseThrow();
            return user.getUserCommon();
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
                .map(User::getUserCommon).toList();
        return new PageImpl<>(userCommonDTOList, pageable, userPage.getTotalElements());
    }

    public UserCommonDTO setUser(UserDTO userDTO) {
        try {
            User user = new User(userDTO);
            user.setInclusionDate(LocalDateTime.now());
            iUserRepository.save(user);
            return user.getUserCommon();
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
            user.setExclusionDate();
            iUserRepository.saveAndFlush(user);
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
