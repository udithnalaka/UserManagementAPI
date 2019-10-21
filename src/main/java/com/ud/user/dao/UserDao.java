package com.ud.user.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ud.user.entity.User;

/**
 * This class is used to hold {@link User} data in memory.
 * 
 * @author udith
 *
 */
@Component
public class UserDao {

	private static Map<Integer, User> users = new HashMap<>();

	static {
		users.put(1, new User(1, "Udith Nalaka", 35, "male"));
		users.put(2, new User(2, "John Smith ", 90, "male"));
		users.put(3, new User(3, "Ira Rana", 20, "female"));
	}

	/**
	 * get a User by id which is stored in the map.
	 * 
	 * @param id User id
	 * 
	 * @return User
	 */
	public User getUserById(final int id) {

		return users.get(id);
	}

}
