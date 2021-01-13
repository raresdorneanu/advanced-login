package com.fils.advancedlogin.repo;

import com.fils.advancedlogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("from User where user_email=?1")
	public List<User> findByEmail(String email);
	
	@Query("from User where user_email=?1 and user_pass=?2")
	public User findByUsernamePassword(String username, String password);

}
