package com.criar.pdi.demonstracao.services;


import com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException.UserIdentifyException;
import com.criar.pdi.demonstracao.models.User.User;
import com.criar.pdi.demonstracao.repositories.IUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserService userService;
    @Mock
    IUserRepository iUserRepository;
    @Mock
    User userMock;

    @Test
    @DisplayName("")
    void shouldValidateThrowIdentifierInvalid() {

        //ARRANGE
       when(iUserRepository.findById(any())).thenThrow(NumberFormatException.class);
        //ACT
        var result = userService.getUserByID(any());
        //ASSERT
        assertThrows(NumberFormatException.class, () -> {
            iUserRepository.findById(any());
        });
    }
}
