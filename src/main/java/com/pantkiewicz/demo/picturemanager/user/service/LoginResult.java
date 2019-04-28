package com.pantkiewicz.demo.picturemanager.user.service;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Optional;

@EqualsAndHashCode
@Getter
public class LoginResult {
    private final Optional<Long> userId;
    private final LoginStatus status;

    private LoginResult(Optional<Long> userId, LoginStatus status) {
        this.userId = userId;
        this.status = status;
    }

    public boolean isSuccessful() {
        return status.equals(LoginStatus.SUCCESS);
    }

    public static LoginResult success(Long userId) {
        return new LoginResult(Optional.of(userId), LoginStatus.SUCCESS);
    }

    public static LoginResult notFound() {
        return new LoginResult(Optional.empty(), LoginStatus.USER_NOT_FOUND);
    }

    public static LoginResult incorrectPassword() { return new LoginResult(Optional.empty(), LoginStatus.INCORRECT_PASSWORD);
    }
}
