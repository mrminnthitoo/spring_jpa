package com.minnthitoo.spring_jpa.controller.api;

import com.minnthitoo.spring_jpa.model.dto.UserDto;
import com.minnthitoo.spring_jpa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void register(@RequestBody UserDto userDto) throws Exception{
        this.authService.register(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authService.login(userDto));
    }

}
