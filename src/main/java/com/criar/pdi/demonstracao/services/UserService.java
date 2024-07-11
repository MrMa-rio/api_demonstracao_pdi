package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.exceptions.User.UserDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException.UserIdentifyException;
import com.criar.pdi.demonstracao.exceptions.User.UserNotFoundException.UserNotFoundException;
import com.criar.pdi.demonstracao.models.User.User;
import com.criar.pdi.demonstracao.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService {
    @Autowired
    private IUserRepository iUserRepository;

    public User getUserByID(String userID){
        try{
            return iUserRepository.findById(Integer.valueOf(userID)).orElseThrow();
        }
        catch (NoSuchElementException e){
            throw new UserNotFoundException();
        }
        catch (NumberFormatException e){
            throw new UserIdentifyException();
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    public void setUser(UserDTO userDTO) {
        try {
            User user = new User(userDTO);
            iUserRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new UserDuplicateDataException();
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
}
