package com.finance.app.user.service.service;

import com.finance.app.user.service.PayloadHelper;
import com.finance.app.user.service.dto.UserDto;
import com.finance.app.user.service.entity.User;
import com.finance.app.user.service.exception.dto.UserNotFoundException;
import com.finance.app.user.service.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.finance.app.user.service.PayloadHelper.mockUser;
import static com.finance.app.user.service.PayloadHelper.mockUserDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(mockUser(101)));
        List<UserDto> users = userService.fetchAllUsersDetails();
        assertEquals(1, users.size());
        assertEquals("Chris", users.get(0).getFirstName());
        assertEquals("Hemsworth", users.get(0).getLastName());
        assertEquals("chris.hemsworth@mail.com", users.get(0).getEmail());
        assertEquals("+971582279099", users.get(0).getPhoneNumber());
    }

    @Test
    void testGetAllUsers_Empty_Users() {
        when(userRepository.findAll()).thenReturn(Collections.emptyList());
        List<UserDto> users = userService.fetchAllUsersDetails();
        assertEquals(0, users.size());
    }

    @Test
    void testGetUser_Success() {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockUser(1)));
        Optional<UserDto> userDto = userService.fetchUserDetails(1L);
        assertTrue(userDto.isPresent());
        assertEquals("Chris", userDto.get().getFirstName());
        assertEquals("Hemsworth", userDto.get().getLastName());
        assertEquals("chris.hemsworth@mail.com", userDto.get().getEmail());
        assertEquals("+971582279099", userDto.get().getPhoneNumber());
    }

    @Test
    void testGetUser_When_User_Not_Exists() {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        Optional<UserDto> dto = userService.fetchUserDetails(99L);
        assertTrue(dto.isEmpty());
    }

    @Test
    void testSaveUser() {
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser(101));
        Long userId = userService.saveUser(mockUserDto());
        assertEquals(101L, userId);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(PayloadHelper.mockUser(101l)));
        when(userRepository.save(Mockito.any(User.class))).thenReturn(mockUser(101));
        Long userId = userService.saveUser(mockUserDto());
        assertEquals(101L, userId);
    }

    @Test
    void testDeleteUser_When_User_Not_Exists() {
        when(userRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        UserNotFoundException exception = assertThrows(UserNotFoundException.class,
                () -> userService.deleteUser(99L));
        assertNotNull(exception);
        assertEquals(HttpStatus.NOT_FOUND, exception.getHttpStatus());
    }
}
