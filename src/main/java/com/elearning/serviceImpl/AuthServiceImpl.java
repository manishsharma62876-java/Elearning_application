package com.elearning.serviceImpl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elearning.dto.request.LoginRequest;
import com.elearning.dto.request.SignupRequest;
import com.elearning.dto.response.LoginResponse;
import com.elearning.entity.Role;
import com.elearning.entity.User;
import com.elearning.repository.UserRepository;
import com.elearning.security.JwtService;
import com.elearning.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private final JwtService jwtservice;

	@Override
	public String signup(SignupRequest request) {
		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			return "Email already exist";
		}
		User user = new User();

		user.setName(request.getName());
		user.setEmail(request.getEmail());

		// Encrypt password
		user.setPassword(passwordEncoder.encode(request.getPassword()));

		user.setRole(Role.valueOf(request.getRole()));

		userRepository.save(user);

		return "User registered successfully......!!!!!!!!!!!";
		
	}

	@Override
	public LoginResponse login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found::"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		String token = jwtservice.generateToken(user.getEmail());

		return new LoginResponse(token, user.getRole().name());
	}
}
