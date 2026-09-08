package com.fitjournal.api.controller;

import com.fitjournal.api.dto.CadastroDTO;
import com.fitjournal.api.dto.LoginDTO;
import com.fitjournal.api.dto.UserDTO;
import com.fitjournal.api.model.User;
import com.fitjournal.api.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody LoginDTO loginRequest) {
        UserDTO user = userService.login(loginRequest);
        return ResponseEntity.status(200).body(user);
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastro(@Valid @RequestBody CadastroDTO cadastroRequest){
        userService.cadastro(cadastroRequest);
        return ResponseEntity.status(201).build();
    }


}
