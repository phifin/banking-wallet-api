package com.example.banking.controller;

import com.example.banking.dto.request.CreateUserRequest;
import com.example.banking.dto.response.ApiResponse;
import com.example.banking.dto.response.UserResponse;
import com.example.banking.service.UserService;
import org.springframework.web.bind.annotation.*;

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
        return new ApiResponse<List<UserResponse>>(true, "Get users list succesfully",users);
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse user = userService.getUserById(id);
        return new ApiResponse<UserResponse>(true, "Get user successfully",user);
    }

    @PostMapping
    public ApiResponse<UserResponse> createNewUser(@RequestBody CreateUserRequest request) {
        UserResponse userData = userService.createUser(request);
        return new ApiResponse<UserResponse>(true, "User created successfully", userData);
    }
}
