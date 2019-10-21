package com.ud.user.service;

import com.ud.user.entity.User;

public interface UserService {

	/**
	 * View a {@link User} for the passed id.
	 *
	 * @param id User id
	 * 
	 * @return {@link User}
	 */
	public User getUserById(final int id);

	/**
	 * create a new User .
	 * 
	 * @param user {@link User}
	 * 
	 * @return {@link User}
	 */
	public User createUser(User user);

	/**
	 * update User details for the given id.
	 * 
	 * @param id   User id
	 * @param user {@link User}
	 * 
	 * @return {@link User}
	 */
	public User updateUser(final int id, final User user);

}
