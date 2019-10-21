package com.ud.user.service;

import com.ud.user.entity.User;

public interface UserService {

	/**
	 * View a {@link User} for the passed id .
	 *
	 * @param id User id
	 * 
	 * @return {@link User}
	 */
	public User getUserById(final int id);

}
