package com.ud.user.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ud.user.entity.User;
import com.ud.user.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * REST controller for managing User related functionality
 * 
 * @author udith
 *
 */

@RestController
@RequestMapping(path = "/api/v1/users")
@Api(value = "User API Resources")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	private final UserService userService;

	@Autowired
	public UserController(final UserService userService) {
		this.userService = userService;
	}
	
	
	/**
	 * get a User for the given id.
	 * 
	 * @param id User id
	 * 
	 * @return {@link ResponseEntity<User>}
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User Successfully Retriewed"),
			@ApiResponse(code = 404, message = "User not found for the given id") })
	public ResponseEntity<User> getUserById(@PathVariable("id") final int id) {
		LOGGER.info("getUserById(). ID : {}", id);
		
		return Optional.ofNullable(userService.getUserById(id))
				.map(result -> ResponseEntity.ok(result))
        		.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		
	}

}
