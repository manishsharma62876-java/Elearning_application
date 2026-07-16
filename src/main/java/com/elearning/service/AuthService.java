package com.elearning.service;

import com.elearning.dto.request.LoginRequest;
import com.elearning.dto.request.SignupRequest;
import com.elearning.dto.response.LoginResponse;

public interface AuthService {

	 String signup(SignupRequest request);
	 
	 LoginResponse login(LoginRequest request);
}
