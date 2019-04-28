package com.pantkiewicz.demo.picturemanager.user.dto;

import com.google.common.collect.Lists;
import com.pantkiewicz.demo.picturemanager.common.validation.dto.ValidationErrorDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class LoginResponseDTO {
    private String result;
    private String statusMessage;
    private String userID;
    private List<ValidationErrorDTO> errors = Lists.newArrayList();
}
