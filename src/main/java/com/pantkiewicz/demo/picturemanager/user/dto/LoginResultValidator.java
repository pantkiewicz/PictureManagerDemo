package com.pantkiewicz.demo.picturemanager.user.dto;

import com.pantkiewicz.demo.picturemanager.user.service.LoginResult;
import com.pantkiewicz.demo.picturemanager.user.service.LoginStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

@Component
public class LoginResultValidator {

    public void validate(LoginResult loginResult, BindingResult bindingResult) {
        switch(loginResult.getStatus()) {
            case USER_NOT_FOUND:
                bindingResult.addError(new ObjectError("loginID", LoginStatus.USER_NOT_FOUND.getMessage()));
                break;
            case INCORRECT_PASSWORD:
                bindingResult.addError(new ObjectError("pass", LoginStatus.INCORRECT_PASSWORD.getMessage()));
        }
    }
}
