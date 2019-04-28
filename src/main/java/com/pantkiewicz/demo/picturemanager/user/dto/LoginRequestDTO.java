package com.pantkiewicz.demo.picturemanager.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class LoginRequestDTO {
    @NotNull
    @Size(min = 1, max = 11)
    private String loginID;

    @NotNull
    @Size(min = 1, max = 11)
    private String pass;
}
