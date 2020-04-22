package com.bridgelabz.services;

import org.springframework.stereotype.Service;

import com.bridgelabz.dto.LoginDto;
import com.bridgelabz.dto.RegistrationDto;
import com.bridgelabz.entity.User;

@Service
public interface IUserService {
	User register(RegistrationDto registerInfo);

	boolean updateIsVerify(String token);

	User login(LoginDto loginDtoInfo);

	User getUserById(String token);
}
