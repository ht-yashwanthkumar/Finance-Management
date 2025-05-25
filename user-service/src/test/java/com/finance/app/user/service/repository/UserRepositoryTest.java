package com.finance.app.user.service.repository;

import com.finance.app.user.service.PayloadHelper;
import com.finance.app.user.service.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveUser() {
        User savedUser = userRepository.save(PayloadHelper.mockUser(101));
        assertNotNull(savedUser.getPkUserId());
    }

    @Test
    void testFindById_WhenExists() {
        User savedUser = userRepository.save(PayloadHelper.mockUser(101));
        Optional<User> userOptional = userRepository.findById(savedUser.getPkUserId());
        assertTrue(userOptional.isPresent());
    }

    @Test
    void testFindById_WhenNotExists() {
        Optional<User> optionalUser = userRepository.findById(999L);
        assertFalse(optionalUser.isPresent());
    }

    @Test
    void testFindAll() {
        userRepository.save(PayloadHelper.mockUser(101));
        List<User> userList = userRepository.findAll();
        assertFalse(userList.isEmpty());
    }

    @Test
    void testDeleteUser_WhenUserNotExists() {
        boolean userNotExist = userRepository.existsById(999L);
        assertFalse(userNotExist);
    }

    @Test
    void testDeleteUser_WhenUserExists() {
        userRepository.save(PayloadHelper.mockUser(101));
        userRepository.deleteById(101l);
        Optional<User> byId = userRepository.findById(101l);
        assertTrue(byId.isEmpty());
    }
}
