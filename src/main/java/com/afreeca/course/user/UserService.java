package com.afreeca.course.user;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface UserService 
{
	User createUser(User user);
	List<User> getAllUsers();
	User getUser(String id);
	long getNumberUsers();

}
