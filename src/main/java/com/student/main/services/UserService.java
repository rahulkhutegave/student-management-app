package com.student.main.services;

import com.student.main.entities.User;
import com.student.main.models.UserDTO;
import com.student.main.response.UserResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    static String getSchemaName(String email){
        return email.substring(email.indexOf("@") + 1, email.lastIndexOf("."));
    }

    void updateUserRole(Long userId, String roleName);

    List<UserResponse> getAllUsers(@AuthenticationPrincipal UserDetails userDetails);

    UserDTO getUserById(Long id);

    User findByUsername(String username);

    void generatePasswordResetToken(String email);

    void resetPassword(String token, String newPassword);
}