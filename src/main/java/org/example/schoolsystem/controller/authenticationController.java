package org.example.schoolsystem.controller;

import jakarta.validation.Valid;
import org.example.schoolsystem.dto.AuthorizationDTO;
import org.example.schoolsystem.dto.RegisterDTO;
import org.example.schoolsystem.model.user.UserModel;
import org.example.schoolsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/auth")
public class authenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthorizationDTO authorizationDTO){
        var userNamePassword = new UsernamePasswordAuthenticationToken(authorizationDTO.getUsername(), authorizationDTO.getPassword());
        var auth = authenticationManager.authenticate(userNamePassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO){
        if(userRepository.findByUsername(registerDTO.getUsername()) != null){
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.getPassword());
        UserModel user = new UserModel(registerDTO.getUsername(), encryptedPassword, registerDTO.getRole());
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
