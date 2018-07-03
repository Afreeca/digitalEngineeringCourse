package com.afreeca.course.UnitTest;

import org.testng.annotations.Test;

import com.afreeca.course.Greeting;
import com.afreeca.course.user.User;
import com.afreeca.course.user.UserService;
import com.afreeca.course.user.userController;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.annotations.BeforeClass;


@RunWith(SpringJUnit4ClassRunner.class)
public class userControllerTest
{
	private MockMvc mockMvc;
	
	@InjectMocks
	private ObjectMapper mapper;
	
	@InjectMocks
	private userController uController;
	
	@Mock
	private UserService uService;
	
	@BeforeClass
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(uController).build();
    }
	
	@Test
	public void greetingTest() throws Exception 
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/helloWorld"))
		.andExpect(handler().methodName("greeting"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("{\"message\":\"Hello World\"}"));	
	}
	
	@Test
	public void getUsersTest() throws Exception 
	{
		// Data
		List<User> userList = Arrays.asList(new User("1", "Marie Van", "Brown"), new User("2", "Howard", "Turman"));

		when(uService.getAllUsers()).thenReturn(userList);
		mockMvc.perform(get("/users")).andExpect(content()
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$[0].id", is("1")))
				.andExpect(jsonPath("$[0].firstname", is("Marie Van")))
				.andExpect(jsonPath("$[0].lastname", is("Brown")))
				.andExpect(jsonPath("$[1].id", is("2")))
				.andExpect(jsonPath("$[1].firstname", is("Howard")))
				.andExpect(jsonPath("$[1].lastname", is("Turman")));

		verify(uService, times(1)).getAllUsers();
		verifyNoMoreInteractions(uService);

	}
	/*
	@Test
	public void CreateUserTest() throws Exception 
	{
		User user = new User("2", "Howard", "Turman");
		
		when(uService.createUser(user)).thenReturn(user);
		mockMvc.perform(post("/users" ))
				.andExpect(status().isOk())
		        .andExpect(content()
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is("2")))
				.andExpect(jsonPath("$.firstname", is("Howard")))
				.andExpect(jsonPath("$.lastname", is("Turman")));
		
		verify(uService, times(1)).createUser(user);
		verifyNoMoreInteractions(uService);
	}
	
	/*@Test
	public void getUserTest() throws Exception 
	{
		User user = new User("2", "Howard", "Turman");
		
		when(uService.getUser("2")).thenReturn(user);
		mockMvc.perform(get("/users/{userId}", "2"))
				.andExpect(status().isOk())
		        .andExpect(content()
				.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(jsonPath("$.id", is("2")))
				.andExpect(jsonPath("$.firstname", is("Howard")))
				.andExpect(jsonPath("$.lastname", is("Turman")));
		
		verify(uService, times(1)).getUser("2");
		verifyNoMoreInteractions(uService);
	}*/
	
	/*private static String createUserInJson (String id, String firstname, String lastname) {
        return "{ \"id\": \"" + id + "\", " +
                            "\"firstname\":\"" + firstname + "\"," +
                            "\"lastname\":\"" + lastname + "\"}";
    }*/
	
 }


