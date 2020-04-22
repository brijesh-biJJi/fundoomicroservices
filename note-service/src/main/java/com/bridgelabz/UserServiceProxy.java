package com.bridgelabz;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bridgelabz.entity.User;
import com.bridgelabz.response.Response;


@FeignClient (name="zuul-service")
@RibbonClient(name="user-service")
public interface UserServiceProxy {
	
	@GetMapping("user-service/user/getuser/{token}")
	public User getUserById(@PathVariable("token") String token);
}
