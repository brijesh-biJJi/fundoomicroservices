package com.bridgelabz.services;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.RegistrationDto;
import com.bridgelabz.entity.User;
import com.bridgelabz.exception.EmailNotFoundException;
import com.bridgelabz.exception.UserAlreadyExistsException;
import com.bridgelabz.exception.UserNotVerifiedException;
import com.bridgelabz.repository.UserRepo;
import com.bridgelabz.response.MailObject;
import com.bridgelabz.response.Response;
import com.bridgelabz.utility.JWTGenerator;
import com.bridgelabz.utility.MailServiceProvider;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper model;
	
	@Autowired
	private User userInfo;
	
	@Autowired
	private BCryptPasswordEncoder encrypt;
	
	@Autowired
	private JWTGenerator jwtGenerate;
	
	@Autowired
	private MailObject mailObj;
	
	@Override
	public User register(RegistrationDto registerInfo)
	{
		User user=userRepo.findByUserEmail(registerInfo.getEmail());
		System.out.println("Check User "+user);
		if(user==null) {
			System.out.println("Inside RegisterImpl Model");
			userInfo=model.map(registerInfo, User.class);
			userInfo.setDateTime(LocalDateTime.now());
			String epass=encrypt.encode(registerInfo.getPassword());
			userInfo.setPassword(epass);
			userInfo.setVerified(false);
			
			/**
			 * Saving the USer Information into DB
			 */
			userInfo = userRepo.save(userInfo);
			
			String url = "http://localhost:8765/user-service/user/verify";
			String jwt=jwtGenerate.createToken(userInfo.getUserid());
			String mailResponse=url + "/" + jwt;
			System.out.println(mailResponse);
			
			mailObj.setEmail(registerInfo.getEmail());
			mailObj.setSubject("Verification");
			mailObj.setMessage(mailResponse);
			
			//rabbitMqSender.send(mailObj);
			MailServiceProvider.sendEmail(mailObj.getEmail(), mailObj.getSubject(), mailObj.getMessage());
			return userInfo;
		}
		else
			throw new UserAlreadyExistsException("User Already Exists..");
	}
	
	@Transactional
	@Override
	public boolean updateIsVerify(String token) {
		System.out.println("After Decrypt Token :"+(long) jwtGenerate.parseToken(token));
		Long userId=(long) jwtGenerate.parseToken(token);
		int val=userRepo.updateByUserId(userId);
		if(val>0) 
			return true;
		else
			return false;
	}
	
	@Transactional
	@Override
	public User login(LoginDto loginDtoInfo) 
	{
		/**
		 * Retrieving the UserInformation
		 */
		User userInf = userRepo.findByUserEmail(loginDtoInfo.getEmail());
		if(userInfo != null)
		{
			if(userInf.isVerified()==true && (encrypt.matches(loginDtoInfo.getPassword(), userInf.getPassword())))
			{
				return userInf;
			}
			else 
			{
				String url = "http://localhost:8765/user-service/user/verify";
				String jwt=jwtGenerate.createToken(userInfo.getUserid());
				String mailResponse=url + "/" + jwt;
				System.out.println(mailResponse);
				
				mailObj.setEmail(loginDtoInfo.getEmail());
				mailObj.setSubject("Verification");
				mailObj.setMessage(mailResponse);
				
				//rabbitMqSender.send(mailObj);
				MailServiceProvider.sendEmail(mailObj.getEmail(), mailObj.getSubject(), mailObj.getMessage());
				
				return null;
			}
		}	
		return null;
	}

	@Override
	public User getUserById(String token)
	{
		long userId=jwtGenerate.parseToken(token);
		userInfo = userRepo.findUserById(userId);
		if (userInfo != null)
		{
			return userInfo;
		}
		else
			return null;
	}
}
