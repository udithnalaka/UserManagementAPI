package com.ud.user.integration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.ud.user.UserManagementApplication;
import com.ud.user.controller.UserController;
import com.ud.user.service.UserService;

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
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		UserController userResource = new UserController(userService);

		this.userMockMvc = MockMvcBuilders.standaloneSetup(userResource).build();
	}

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

}
