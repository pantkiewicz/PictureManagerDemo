package com.pantkiewicz.demo.picturemanager.common.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationErrorDTO {
    private String itemname;
    private String errorcode;
}
