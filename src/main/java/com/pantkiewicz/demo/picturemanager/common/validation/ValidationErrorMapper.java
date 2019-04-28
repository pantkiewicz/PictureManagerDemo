package com.pantkiewicz.demo.picturemanager.common.validation;

import com.pantkiewicz.demo.picturemanager.common.validation.dto.ValidationErrorDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

@Component
public class ValidationErrorMapper {
    public ValidationErrorDTO mapToValidationErrorDTO(ObjectError objectError) {
        if(objectError instanceof FieldError) {
            FieldError fieldError = (FieldError)objectError;
            return new ValidationErrorDTO(fieldError.getField(), objectError.getDefaultMessage());
        }
        return new ValidationErrorDTO(objectError.getObjectName(), objectError.getDefaultMessage());
    }
}
