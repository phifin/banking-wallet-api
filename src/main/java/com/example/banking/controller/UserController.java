package com.example.banking.controller;

import com.example.banking.dto.request.CreateUserRequest;
import com.example.banking.dto.response.ApiResponse;
import com.example.banking.dto.response.UserResponse;
import com.example.banking.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ApiResponse<List<UserResponse>> getUsersList() {
        List<UserResponse> users = userService.getUsers();
        return new ApiResponse<List<UserResponse>>(true, "Users retrieved successfully", users);
    }

    @GetMapping("/{userId}")
    public ApiResponse<UserResponse> getUserById(@PathVariable("userId") Long id) {
        UserResponse user = userService.getUserById(id);
        return new ApiResponse<UserResponse>(true, "User retrieved successfully", user);
    }

    @PostMapping
    public ApiResponse<UserResponse> createNewUser(@RequestBody CreateUserRequest request) {
        UserResponse userData = userService.createUser(request);
        return new ApiResponse<UserResponse>(true, "User created successfully", userData);
    }
}
