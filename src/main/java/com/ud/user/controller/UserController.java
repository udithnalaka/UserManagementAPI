package com.ud.user.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	/**
	 * create a User.
	 * 
	 * @param user {@link User}
	 * 
	 * @return newly created {@link User}
	 */
	@PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User added Successfully"),
			@ApiResponse(code = 400, message = "Validation Error") })
	public ResponseEntity<User> createUser(@Validated @RequestBody(required = true) User user) {
		LOGGER.info("createUser(). User : {} ", user);

		return Optional.ofNullable(userService.createUser(user))
				.map(result -> ResponseEntity.ok(result))
				.orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
	}

	/**
	 * update User details.
	 * 
	 * @param id   User id
	 * @param user {@link User}
	 * 
	 * @return updated {@link User}
	 */
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User updated Successfully"),
			@ApiResponse(code = 400, message = "Validation Error"),
			@ApiResponse(code = 404, message = "User Not Found. not able to update") })
	public ResponseEntity<User> updateUser(@PathVariable("id") final int id,
			@Validated @RequestBody(required = true) User user) {
		LOGGER.info("updateUser(). ID : {}, User : {} ", id, user);

		return Optional.ofNullable(userService.updateUser(id, user))
				.map(result -> ResponseEntity.ok(result))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	
	/**
     * delete a User with id.(Soft delete).
     * only change the status to DEACTIVE.
     *
     * @param id UserId
     * 
     * @return void
     */
    @DeleteMapping(value = "/{ids}")
    @ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Successfully deleted the User")})
    public ResponseEntity<?> deleteUser(@PathVariable("ids") final List<Integer> ids) {
    	LOGGER.info("deleteUser(). ID : {} ", ids);
        	
    	userService.deleteUser(ids);
    	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
