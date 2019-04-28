package com.pantkiewicz.demo.picturemanager.user.controller;

import com.pantkiewicz.demo.picturemanager.user.dto.LoginRequestDTO;
import com.pantkiewicz.demo.picturemanager.user.dto.LoginResponseDTO;
import com.pantkiewicz.demo.picturemanager.user.dto.LoginResultValidator;
import com.pantkiewicz.demo.picturemanager.user.dto.UserResponseMapper;
import com.pantkiewicz.demo.picturemanager.user.service.LoginResult;
import com.pantkiewicz.demo.picturemanager.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginResultValidator resultValidator;

    @Autowired
    UserResponseMapper userResponseMapper;

    @PostMapping("login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequest,
                                                  BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return new ResponseEntity(userResponseMapper.buildErrorResponse(bindingResult), HttpStatus.BAD_REQUEST);
        }
        LoginResult result = userService.tryLogIn(loginRequest.getLoginID(), loginRequest.getPass());
        resultValidator.validate(result, bindingResult);

        if(result.isSuccessful()) {
            return ResponseEntity.ok(userResponseMapper.buildSuccessfulResponse(result));
        }

        return new ResponseEntity(userResponseMapper.buildErrorResponse(bindingResult), HttpStatus.BAD_REQUEST);
    }
}
