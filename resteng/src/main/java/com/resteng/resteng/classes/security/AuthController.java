package com.resteng.resteng.classes.security;

import java.util.HashSet;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resteng.resteng.classes.entity.Role;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<JWTResponseDto> login(@RequestBody LoginRequestDto loginRequest) {

        JWTResponseDto jwtResponseDto = authService.login(loginRequest.getLogin(), loginRequest.getPassword());

        return ResponseEntity.ok(jwtResponseDto);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AccessTokenDto> refreshAccessToken(@RequestParam String refreshToken) {
        AccessTokenDto dto = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody String refreshToken) {
        authService.logoutUser(refreshToken);
        return ResponseEntity.ok("Signed out");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest signupRequest) {

        return authService.registerUser(signupRequest);
    }

}
