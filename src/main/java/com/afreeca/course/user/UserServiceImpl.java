package com.afreeca.course.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired
	UserRepository uRepo; // will create a instance of userRepository

	@Override
	public User createUser(User user)
	{
		return this.uRepo.save(user);
	}

	@Override
	public List<User> getAllUsers() 
	{
		return this.uRepo.findAll();
	}

	@Override
	public User getUser(String id) 
	{
		Optional<User> user= this.uRepo.findById(id);
		return user.get();
	}

	@Override
	public long getNumberUsers() 
	{
		return this.uRepo.count();
	}

}
