package com.finance.app.user.service.controller;

import com.finance.app.user.service.dto.ResponseBody;
import com.finance.app.user.service.dto.UserDto;
import com.finance.app.user.service.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Operation(summary = "Get All Users Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Users Details", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while fetching Users details")})

    @GetMapping
    public ResponseEntity<ResponseBody<List<UserDto>>> fetchAllUsersDetails() {
        LOGGER.info("Entered into fetchAllUsersDetails method");
        List<UserDto> users = userService.fetchAllUsersDetails();
        return !users.isEmpty()
                ? new ResponseEntity<ResponseBody<List<UserDto>>>(
                ResponseBody.of("User information retrieved Successfully", users), HttpStatus.OK)
                : new ResponseEntity<ResponseBody<List<UserDto>>>(ResponseBody.of("No User information found", users),
                HttpStatus.OK);
    }

    @Operation(summary = "Save User Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User saved successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while saving User")})
    @PostMapping
    public ResponseEntity<ResponseBody<Long>> saveUser(@Valid @RequestBody UserDto userDto) {
        LOGGER.info("Entered into saveUser method");
        return new ResponseEntity<ResponseBody<Long>>(
                ResponseBody.of("User created Successfully", userService.saveUser(userDto)), HttpStatus.OK);
    }

    @Operation(summary = "Get User Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User Details", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while fetching User details")})

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseBody<UserDto>> fetchUserDetails(@PathVariable("userId") Long userId) {
        LOGGER.info("Entered into fetchUserDetails method");
        Optional<UserDto> optionalUserDto = userService.fetchUserDetails(userId);
        return optionalUserDto.map(userDto -> new ResponseEntity<>(
                ResponseBody.of("User information retrieved Successfully", userDto), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(ResponseBody.of("User not found for the given ID: " + userId, null), HttpStatus.NOT_FOUND));
    }

    @Operation(summary = "Update User Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User details updated successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while updating User details")})

    @PutMapping("/{userId}")
    public ResponseEntity<ResponseBody<Long>> updateUserDetails(@PathVariable("userId") Long userId,
                                                                @RequestBody UserDto userDto) {
        LOGGER.info("Entered into updateUserDetails method");
        return new ResponseEntity<ResponseBody<Long>>(
                ResponseBody.of("User updated Successfully", userService.updateUser(userId, userDto)), HttpStatus.OK);
    }

    @Operation(summary = "Delete User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User deleted successfully", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))}),
            @ApiResponse(responseCode = "500", description = "Service failed due to internal server error while deleting User details")})

    @DeleteMapping("/{userId}")
    public ResponseEntity<ResponseBody<Void>> deleteUser(@PathVariable("userId") Long userId) {
        LOGGER.info("Entered into deleteUser method");
        userService.deleteUser(userId);
        return new ResponseEntity<ResponseBody<Void>>(
                ResponseBody.of("User deleted Successfully"), HttpStatus.OK);
    }
}
