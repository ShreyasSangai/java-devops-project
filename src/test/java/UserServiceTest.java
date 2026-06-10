package com.devops.app;

import com.devops.app.model.User;
import com.devops.app.repository.UserRepository;
import com.devops.app.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @InjectMocks private UserService userService;

    @Test
    void testRegisterUser_Success() {
        when(passwordEncoder.encode("pass123")).thenReturn("$2a$encoded");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArgument(0));

        User result = userService.registerUser("shreyas", "shreyas@test.com", "pass123");

        assertNotNull(result);
        assertEquals("shreyas", result.getUsername());
        assertEquals("$2a$encoded", result.getPassword());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testIsUsernameExists_True() {
        when(userRepository.existsByUsername("shreyas")).thenReturn(true);
        assertTrue(userService.isUsernameExists("shreyas"));
    }

    @Test
    void testIsEmailExists_False() {
        when(userRepository.existsByEmail("new@test.com")).thenReturn(false);
        assertFalse(userService.isEmailExists("new@test.com"));
    }
}