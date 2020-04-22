package com.bridgelabz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import com.bridgelabz.entity.User;


@Repository

public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query("from User where useremail=:email")
	User findByUserEmail(String email);
	
	@Modifying
	@Query("update from User set isverified=true where userid=:id")
	int updateByUserId(long id);
	
	@Query("from User where userid=:id")
	User findUserById(long id);
}
