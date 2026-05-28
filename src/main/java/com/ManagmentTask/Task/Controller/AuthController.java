package com.ManagmentTask.Task.Controller;

import com.ManagmentTask.Task.DTOModel.LoginDTO;
import com.ManagmentTask.Task.DTOModel.LoginResponseDTO;
import com.ManagmentTask.Task.Security.AuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@RequestBody LoginDTO loginDTO)
    {
        return  ResponseEntity.ok(authService.loginUser(loginDTO));
    }
}
