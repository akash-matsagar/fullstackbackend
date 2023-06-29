package com.akash.projbackend.applicationcontroller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.akash.projbackend.applicationrepository.ApplicationRepository;
import com.akash.projbackend.applicationservice.ApplicationService;
import com.akash.projbackend.entity.User;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
public class CommonController {

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ApplicationRepository applicationRepository;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@Valid @RequestBody User request) {
		String username = request.getUsername();
		boolean flag = false;
		System.out.println(username);
		User user = null;
		 user = applicationRepository.findByUsername(username);
		 if(user!=null) {
			 System.out.println("This username already exists!");
		 }else {
			 applicationService.registerUser(request.getUsername(), request.getPassword());
			 flag = true;
		 }
			
		if(flag==true)		
			return ResponseEntity.ok("Success");
		
		else
			return ResponseEntity.ok("Fail");
		
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User request) {
		return applicationService.loginUser(request.getUsername(), request.getPassword());
	}

}
