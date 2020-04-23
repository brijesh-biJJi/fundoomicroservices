package com.bridgelabz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.RegistrationDto;
import com.bridgelabz.entity.User;
import com.bridgelabz.response.Response;
import com.bridgelabz.services.IUserService;
import com.bridgelabz.utility.JWTGenerator;

import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {
	
	@Autowired
	private JWTGenerator jwtGenerate;
	
	@Autowired
	private IUserService userService;

	@PostMapping("user/register")
	public ResponseEntity<Response> register(@RequestBody RegistrationDto registerDto)
	{
		User userInfo=userService.register(registerDto);
		if(userInfo!=null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Registration Successfull..", userInfo));		
		}
		return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Response("User Already Exists...!",  userInfo));	
	}
	
	/**
	 * API for Token Verification
	 * @param token
	 * @return
	 */
	@GetMapping ("user/verify/{token}")
	@ApiOperation(value="User Verification", response = Response.class)
	public ResponseEntity<Response> userVerification(@PathVariable ("token") String token)
	{
		boolean updateUserInfo=userService.updateIsVerify(token);
		if(updateUserInfo)
		{
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("VERIFIED",200));
		}
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Not Verified. Unauthorized Client...!",400));
	}
	
	/**
	 * API for Login
	 * login is a Handler method that communicates with Service layer in order to perform Login Operation
	 * @param info
	 * @return
	 */
	@PostMapping("/user/login")
	@ApiOperation(value="Login User", response = Response.class)
	public ResponseEntity<Response> login(@RequestBody LoginDto loginDtoInfo)
	{
		User userInfo = userService.login(loginDtoInfo);
		if(userInfo != null) 
		{
			System.out.println("SuccessFull hh ");
			String token=jwtGenerate.createToken(userInfo.getUserid());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response(token,200,loginDtoInfo));
		}else {
			System.out.println("Bad ");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Invalid User...!",  400));
		}
	}
	
	
	
	
	
	
	
	@GetMapping("user/getuser/{token}")
	@ApiOperation(value="Get User By Id", response = Response.class)
	//@Cacheable( value="users")
	public User getUserById(@PathVariable("token") String token) {
		System.out.println("3 inside USerService controller");
		User user = userService.getUserById(token);
		System.out.println("USer service hash "+user.hashCode());
		if(user!=null) 
		{
			System.out.println("4 retrieved user "+user);
			return new User(user.getUserid(), user.getName(), user.getPassword(), user.getPhone(), user.getEmail(), user.isVerified(), user.getDateTime());
		}
		else
			System.out.println("USer Not Found in User Service....");
		return user;
	}
}
