package com.finance.app.user.service;

import com.finance.app.user.service.dto.UserDto;
import com.finance.app.user.service.entity.User;

public class PayloadHelper {
    public static UserDto mockUserDto() {
        UserDto userDto = new UserDto();
        userDto.setTitle("MR");
        userDto.setFirstName("Chris");
        userDto.setLastName("Hemsworth");
        userDto.setEmail("chris.hemsworth@mail.com");
        userDto.setPhoneNumber("+971582279099");
        return userDto;
    }

    public static User mockUser(long id) {
        User user = new User();
        user.setPkUserId(id);
        user.setTitle("Mr");
        user.setFirstName("Chris");
        user.setLastName("Hemsworth");
        user.setEmail("chris.hemsworth@mail.com");
        user.setPhoneNumber("+971582279099");
        return user;
    }
}
