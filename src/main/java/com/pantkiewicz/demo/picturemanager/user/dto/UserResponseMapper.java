package com.pantkiewicz.demo.picturemanager.user.dto;

import com.google.common.collect.Lists;
import com.pantkiewicz.demo.picturemanager.common.validation.ValidationErrorMapper;
import com.pantkiewicz.demo.picturemanager.user.service.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Component
public class UserResponseMapper {
    private static final String OK_RESULT = "OK";
    private static final String ERROR_RESULT = "NG";
    private static final String FAILED_VALIDATION_MESSAGE = "Input parameters validation failed";

    @Autowired
    ValidationErrorMapper validationErrorMapper;

    public LoginResponseDTO buildSuccessfulResponse(LoginResult loginResult) {
        LoginResponseDTO response = new LoginResponseDTO();
        response.setResult(OK_RESULT);
        response.setStatusMessage(loginResult.getStatus().getMessage());
        response.setUserID(loginResult.getUserId().map(String::valueOf).orElse(""));
        return response;
    }

    public LoginResponseDTO buildErrorResponse(BindingResult result) {
        LoginResponseDTO response = new LoginResponseDTO();
        response.setResult(ERROR_RESULT);
        response.setStatusMessage(FAILED_VALIDATION_MESSAGE);
        response.setErrors(Lists.transform(result.getAllErrors(), validationErrorMapper::mapToValidationErrorDTO));
        return response;
    }
}
