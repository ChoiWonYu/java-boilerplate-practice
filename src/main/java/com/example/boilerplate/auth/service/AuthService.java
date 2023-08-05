package com.example.boilerplate.auth.service;

import com.example.boilerplate.auth.controller.dto.SigninRequest;
import com.example.boilerplate.auth.controller.dto.SignupRequest;

public interface AuthService {

  public String register(SignupRequest signupRequest);

  public String login(SigninRequest signinRequest);

}
