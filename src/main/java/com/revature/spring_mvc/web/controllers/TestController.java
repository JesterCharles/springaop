package com.revature.spring_mvc.web.controllers;

import javax.servlet.http.HttpServletResponse;

import com.revature.spring_mvc.exceptions.AuthenticationException;
import com.revature.spring_mvc.exceptions.ResourcePersistenceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpServerErrorException;

import com.revature.spring_mvc.exceptions.InvalidRequestException;
import com.revature.spring_mvc.exceptions.TeapotException;
import com.revature.spring_mvc.web.dtos.LoginRequest;
import com.revature.spring_mvc.web.dtos.LoginSuccessResponse;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping(method = RequestMethod.GET, value = "/test1")
	public @ResponseBody String test1() {
		return "/spring-aop/test/test1 works!";
	}

	@GetMapping("/test2")
	public @ResponseBody String test2() {
		return "/spring-aop/test/test2 works!";
	}

	@GetMapping("/test3")
	public @ResponseBody String test3(@RequestParam String someValue,
			@RequestParam(value = "anotherValue", required = false) String whatevs) {
		return "/spring-aop/test/test3 works! Provided values " + someValue + " and " + whatevs;
	}

	@GetMapping("/test4/{someValue}/{anotherValue}")
	public @ResponseBody String test4(@PathVariable String someValue,
			@PathVariable(value = "anotherValue", required = false) String whatevs) {
		return "/spring-aop/test/test4 works! Provided values " + someValue + " and " + whatevs;
	}

	@GetMapping("/test5")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void test5() {
		System.out.println("Not return to request but to the web server output");
	}

	@GetMapping("/test6")
	public ResponseEntity<String> test6(@RequestParam boolean brewCoffee) {
		if (brewCoffee) {
			return new ResponseEntity<>("Coffee brewed", HttpStatus.OK);
		}

		return new ResponseEntity<>("Did not brew coffee", HttpStatus.I_AM_A_TEAPOT);
	}

	@GetMapping("/test7")
	public @ResponseBody String test7(HttpServletResponse resp) {
		resp.setStatus(206);
		resp.addHeader("custom-header", "custom-value");
		return "/spring-mvc-demo/test/test7 works!";
	}

	@GetMapping("/test8")
	public @ResponseBody String test8(@RequestHeader(required = false) String someHeader) {
		return "/spring-mvc-demo/test/test4 works! Provided values " + someHeader;
	}

	@PostMapping(value = "/test9", consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LoginSuccessResponse test9(@RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest);
		if(loginRequest.getUsername() == null || loginRequest.getUsername().trim().equals("")) {
			throw new InvalidRequestException("There is an issue with login information try a gain");
		}
		return new LoginSuccessResponse(loginRequest.getUsername(), "JWT: asuidhf0 129374yuasidfhashdf0918wysf897yhui");
	}

	@GetMapping("/testEx")
	public void testEx() {
		throw new InvalidRequestException("hi");
	}

	@GetMapping("/runtimeEx")
	public void testRuntimeException(){
		System.out.println("Oops runtime exception");
		throw new RuntimeException("uh oh");
	}

	@GetMapping("/testAuthEx")
	public void testAuthException() {
		throw new AuthenticationException("authentication, no.");
	}

	@GetMapping("/testResourcePersistEx")
	public void testResourcePersistenceException(){
		System.out.println("Oops persistence exception");
		throw new ResourcePersistenceException("uh oh");
	}

}
