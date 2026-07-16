package com.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.dto.request.LoginRequest;
import com.elearning.dto.request.SignupRequest;
import com.elearning.dto.response.LoginResponse;
import com.elearning.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

	private final AuthService authService;

	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
		return ResponseEntity.ok(authService.signup(request));
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
}
