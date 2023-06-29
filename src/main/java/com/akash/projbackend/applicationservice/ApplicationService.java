package com.akash.projbackend.applicationservice;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.akash.projbackend.applicationrepository.ApplicationRepository;
import com.akash.projbackend.entity.User;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	@Transactional
	public ResponseEntity<String> registerUser(String username, String password) {
		try {
			applicationRepository.registerUser(username, password);
			return ResponseEntity.ok("User registered successfully");
		} catch (DataIntegrityViolationException ex) {
			return ResponseEntity.badRequest().body("Username already exists");
		}
	}

	@Transactional
	public ResponseEntity<String> loginUser(String username, String password) {
		User user = applicationRepository.findByUsernameAndPassword(username, password);
		if (user != null) {
			// String storedPassword = user.getPassword();
			return ResponseEntity.ok("User logged in successfully");
		}

		return ResponseEntity.badRequest().body("Invalid username or password");
	}
}
