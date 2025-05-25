package com.finance.app.user.service.service;

import com.finance.app.user.service.dto.UserDto;
import com.finance.app.user.service.entity.User;
import com.finance.app.user.service.exception.dto.UserNotFoundException;
import com.finance.app.user.service.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> fetchAllUsersDetails() {
        LOGGER.debug("Entered into fetchAllUsersDetails method");
        return userRepository.findAll().stream().map(this::copyBeans).collect(Collectors.toList());
    }

    public Optional<UserDto> fetchUserDetails(Long userId) {
        LOGGER.debug("Entered into fetchUserDetails method with userId: {}", userId);
        return userRepository.findById(userId).map(this::copyBeans);
    }

    @Transactional
    public Long saveUser(UserDto userDto) {
        LOGGER.debug("Entered into saveUser method");

        User user = new User();
        user.setTitle(userDto.getTitle());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return userRepository.save(user).getPkUserId();
    }

    @Transactional
    public Long updateUser(Long userId, UserDto userDto) {
        LOGGER.debug("Entered into updateUser method");

        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found for the given ID"));

        user.setTitle(userDto.getTitle());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPhoneNumber(userDto.getPhoneNumber());
        return userRepository.save(user).getPkUserId();
    }

    @Transactional
    public void deleteUser(Long userId) {
        LOGGER.debug("Entered into deleteUser method");

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(HttpStatus.NOT_FOUND, "User not found for the given ID");
        }
        userRepository.deleteById(userId);
    }

    private UserDto copyBeans(User user) {
        return new ModelMapper().map(user, UserDto.class);
    }
}
