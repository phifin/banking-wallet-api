package com.example.banking.service;

import com.example.banking.dto.request.CreateUserRequest;
import com.example.banking.dto.response.UserResponse;
import com.example.banking.exception.BusinessException;
import com.example.banking.exception.ErrorCode;
import com.example.banking.model.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class UserService {
    private final Map<Long, User> users = new LinkedHashMap<>();
    private Long nextId = 1L;

    public UserResponse createUser(CreateUserRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Request body is required");
        }

        String tempEmail = request.getEmail();
        String tempFullName = request.getFullName();
        String tempPhone = request.getPhone();

        if (tempFullName == null || tempFullName.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Full name is required");
        } else {
            tempFullName = tempFullName.trim();
        }

        if (tempEmail == null || tempEmail.isBlank()) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "Email cannot be blank");
        } else {
            tempEmail = tempEmail.trim().toLowerCase(Locale.ROOT);
            if (!tempEmail.contains("@")) {
                throw new BusinessException(ErrorCode.INVALID_REQUEST, "Email is invalid");
            }
        }

        for (User user : users.values()) {
            if (user.getEmail().equals(tempEmail)) {
                throw new BusinessException(ErrorCode.DUPLICATE_EMAIL, "Email already exists");
            }
        }

        if (tempPhone == null || tempPhone.isBlank()) {
            tempPhone = null;
        } else {
            tempPhone = tempPhone.trim();
        }

        Long id = nextId;
        nextId++;
        Instant currentTime = Instant.now();

        User newUser = new User(id, tempFullName, tempEmail, tempPhone, currentTime);
        users.put(id, newUser);

        return new UserResponse(
                newUser.getId(),
                newUser.getFullName(),
                newUser.getEmail(),
                newUser.getPhone(),
                newUser.getCreatedAt()
        );
    }

    public List<UserResponse> getUsers() {
        List<UserResponse> result = new ArrayList<>();

        for (User user : users.values()) {
            UserResponse userResponse = new UserResponse(
                    user.getId(),
                    user.getFullName(),
                    user.getEmail(),
                    user.getPhone(),
                    user.getCreatedAt()
            );

            result.add(userResponse);
        }

        return result;
    }

    public UserResponse getUserById(Long id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.INVALID_REQUEST, "ID is required");
        }

        User user = users.get(id);

        if (user == null) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND, "User not found");
        }
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getPhone(),
                user.getCreatedAt()
        );
    }
}
