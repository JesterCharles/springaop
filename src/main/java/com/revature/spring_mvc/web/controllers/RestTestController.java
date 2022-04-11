package com.revature.spring_mvc.web.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.revature.spring_mvc.web.util.Secured;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.spring_mvc.services.UserService;
import com.revature.spring_mvc.web.dtos.LoginRequest;
import com.revature.spring_mvc.web.dtos.LoginSuccessResponse;
import com.revature.spring_mvc.web.dtos.SignUp;

@RestController
@RequestMapping("/user")
public class RestTestController {

	 private final UserService userService;

	    @Autowired
	    public RestTestController(UserService userService) {
	        this.userService = userService;
	    }
	
	  @PostMapping(value = "/login", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	    public LoginSuccessResponse test9(@RequestBody LoginRequest loginRequest) {
	        System.out.println(loginRequest);
			return userService.login(loginRequest.getUsername(), loginRequest.getPassword());
	    }
	  
	  @PostMapping(value="/register", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	    public void test9(@RequestBody SignUp signUp) {
			userService.registerNewUser(signUp);
	    }

		@Secured(allowedUsers = {"cjester"})
	  @GetMapping("/protectedEndpoint")
	  public String protectedEndpoint() {
		  return "look at the protected endpoint call from";
	  }

	  @Secured(allowedUsers = {"chil"})
	  @GetMapping("/sickBoi")
		public String sickBoi(){
			return "Brandon the sick boi made me cover AOP, may he get better!";
	  }
}
