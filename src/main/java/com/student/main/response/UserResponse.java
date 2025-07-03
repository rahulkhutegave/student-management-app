package com.student.main.response;

import com.student.main.entities.User;
import com.student.main.models.AppRole;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class UserResponse {

    private Long userId;

    private String username;

    private String email;

    private Boolean accountNonLocked = true;

    private Boolean accountNonExpired = true;

    private Boolean enabled = true;

    private LocalDate accountExpiryDate;

    private Boolean isTwoFactorEnabled = false;

    private String signUpMethod;

    private AppRole role;

    private LocalDate createdDate;

    public UserResponse responseBuilder(User user){
        this.userId = user.getUserId();
        this.accountExpiryDate = user.getAccountExpiryDate();
        this.role = user.getRole().getRoleName();
        this.createdDate = user.getCreatedDate().toLocalDate();
        this.accountNonExpired = user.getAccountNonExpired();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.isTwoFactorEnabled = user.getIsTwoFactorEnabled();
        this.signUpMethod = user.getSignUpMethod();
        this.enabled = user.getEnabled();
        this.accountNonLocked = user.getAccountNonLocked();
        return this;
    }

}
