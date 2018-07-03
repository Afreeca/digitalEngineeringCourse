package com.afreeca.course.user;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import com.afreeca.course.Greeting;
import com.afreeca.course.exceptionHandler.CreateException;
import com.afreeca.course.exceptionHandler.NotFoundException;

@RestController
public class userController 
{
	private static final Logger LOGGER = LogManager.getLogger(userController.class);
	
	@Autowired 
	private UserService uService;
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private static final String word = "Hello %s";

	@RequestMapping(value ="/helloWorld", method = RequestMethod.GET)
	public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) 
	{			
		return new Greeting(String.format(word, name));
	}
	
	@RequestMapping(value ="/users", method = RequestMethod.GET)
	public List<User> getUsers() 
	{	
		LOGGER.info("get all users");
		return uService.getAllUsers();
	}
	
	@RequestMapping(value ="/users", method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody User user) 
	{	
		user.setId(String.valueOf(uService.getNumberUsers()+1));
		if(uService.createUser(user) == null)
		{
			 LOGGER.error("Failed to create user!!!");
			throw new CreateException("user failed to be created");
		}
			
		 LOGGER.info("User created successfully");
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("MyResponseHeader", "MyValue");
		return new ResponseEntity<>(user, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value ="/users/{userId}", method = RequestMethod.GET)
	public Resource<User> getUser(@PathVariable String userId) 
	{	
		User user = uService.getUser(userId);
		if(user == null)
		{
			
		    throw new NotFoundException("user with id" + userId +  " not found");
		}
		LOGGER.info("user found");
		return new Resource<>(user);
	}
	
	public void sendMessage(String msg) 
	{
	    kafkaTemplate.send("digital", msg);
	}
}
