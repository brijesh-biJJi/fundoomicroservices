package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bridgelabz.entity.User;

public interface IUserRepo extends JpaRepository<User, Long>{
	@Query("from User where userid=:id")
	User findByUserId(long id);

}
