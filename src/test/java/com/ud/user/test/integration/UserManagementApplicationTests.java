package com.ud.user.test.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ud.user.UserManagementApplication;
import com.ud.user.controller.UserController;
import com.ud.user.entity.User;
import com.ud.user.service.UserService;
import com.ud.user.test.util.TestUtil;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = UserManagementApplication.class)
@WebAppConfiguration
class UserManagementApplicationTests {
	
	private MockMvc userMockMvc;

	@Autowired
	private UserService userService;
	
	private static final String USER_API_PATH = "/api/v1/users";
	
	private static final int USER_ID_VALID = 1;
	private static final int USER_ID_INVALID = 10;
	private static final String USER_NAME_VALID = "Udith Nalaka";
	private static final int USER_AGE_VALID = 35;
	private static final String USER_SEX_VALID = "male";
	
	private static final String RESPONSE_PARAM_USER_ID = "id";
	private static final String RESPONSE_PARAM_USER_NAME = "name";
	private static final String RESPONSE_PARAM_USER_AGE = "age";
	private static final String RESPONSE_PARAM_USER_SEX = "sex";
	
	private static final int CREATE_USER_ID = 11;
	private static final String CREATE_USER_NAME = "Minuki D";
	private static final int CREATE_USER_AGE = 5;
	private static final String CREATE_USER_SEX = "female";
	
	private static final String CREATE_USER_NAME_GREATER_THAN_50 = "User name should be less than fifty characters, or else 400.";
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		UserController userResource = new UserController(userService);

		this.userMockMvc = MockMvcBuilders.standaloneSetup(userResource).build();
	}
	
	private User createUserForTesting(int id, String name, int age, String sex) {
		return new User(id, name, age, sex);
	}
	
	////START - test cases for getUserById()
	@Test
	void getUserByIdWithValidIdShouldReturnUser() throws Exception  {
		
		userMockMvc.perform(get(USER_API_PATH + "/" + USER_ID_VALID))
		.andExpect(status().isOk())
		.andExpect(jsonPath(RESPONSE_PARAM_USER_ID).value(USER_ID_VALID))
		.andExpect(jsonPath(RESPONSE_PARAM_USER_NAME).value(USER_NAME_VALID))
		.andExpect(jsonPath(RESPONSE_PARAM_USER_AGE).value(USER_AGE_VALID))
		.andExpect(jsonPath(RESPONSE_PARAM_USER_SEX).value(USER_SEX_VALID));
		
	}
	
	@Test
	void getUserByIdWithInValidIdShouldReturnNotFound() throws Exception  {
		
		userMockMvc.perform(get(USER_API_PATH + "/" + USER_ID_INVALID))
		.andExpect(status().isNotFound());
		
	}
	////END - test cases for getUserById()
	
	
	////START - test cases for createUser()
	@Test
	public void createUserWithValidEntityShouldReturnTheNewUser() throws Exception {

		userMockMvc.perform(post(USER_API_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(TestUtil.convertObjectToJsonBytes(createUserForTesting(
					CREATE_USER_ID, CREATE_USER_NAME, CREATE_USER_AGE, CREATE_USER_SEX))))
			.andExpect(status().isOk())
			.andExpect(jsonPath(RESPONSE_PARAM_USER_ID).value(CREATE_USER_ID))
			.andExpect(jsonPath(RESPONSE_PARAM_USER_NAME).value(CREATE_USER_NAME))
			.andExpect(jsonPath(RESPONSE_PARAM_USER_AGE).value(CREATE_USER_AGE))
			.andExpect(jsonPath(RESPONSE_PARAM_USER_SEX).value(CREATE_USER_SEX));
	}
	
	@Test
	public void createUserWithNameSizeGreaterThanFiftyShouldReturnValidationError() throws Exception {

		userMockMvc.perform(post(USER_API_PATH)
			.contentType(MediaType.APPLICATION_JSON)
			.content(TestUtil.convertObjectToJsonBytes(createUserForTesting(
					CREATE_USER_ID, CREATE_USER_NAME_GREATER_THAN_50, CREATE_USER_AGE, CREATE_USER_SEX))))
			.andExpect(status().isBadRequest());
		
	}
	
	
	////END - test cases for createUser()
	
}
