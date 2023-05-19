package com.bjitacademy.securityModule.controllers;

import com.bjitacademy.securityModule.model.UserRequestModel;
import com.bjitacademy.securityModule.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UserRequestModel requestModel){
        return userService.register(requestModel);
    }

    @GetMapping("/update")
    public String update(){
        return "update api";
    }

    @GetMapping("/delete")
    public String delete(){
        return "Delete api";
    }
}
