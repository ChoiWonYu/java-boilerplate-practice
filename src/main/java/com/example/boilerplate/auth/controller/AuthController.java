package com.example.boilerplate.auth.controller;

import com.example.boilerplate.auth.controller.dto.SigninRequest;
import com.example.boilerplate.auth.controller.dto.SignupRequest;
import com.example.boilerplate.auth.service.AuthService;
import com.example.boilerplate.auth.service.JwtAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

  private final JwtAuthService jwtAuthService;

  @PostMapping("/signup")
  public String register(@Valid @RequestBody SignupRequest signupRequest) {
    return jwtAuthService.register(signupRequest);
  }

  @PostMapping("/signin")
  public String login(@Valid @RequestBody SigninRequest signinRequest) {
    return jwtAuthService.login(signinRequest);
  }
}
