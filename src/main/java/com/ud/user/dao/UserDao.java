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
		users.put(1, new User(1, "Udith Nalaka", 35, "male", "ACTIVE"));
		users.put(2, new User(2, "John Smith ", 90, "male", "ACTIVE"));
		users.put(3, new User(3, "Ira Rana", 20, "female", "ACTIVE"));
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

	/**
	 * create a new User
	 * 
	 * @param user User
	 * 
	 * @return {@link User}
	 */
	public User saveUser(final User user) {

		users.put(user.getId(), user);
		return getUserById(user.getId());
	}

	/**
	 * update User
	 * 
	 * @param id   User id
	 * @param user User
	 * 
	 * @return {@link User}
	 */
	public User updateUser(int id, User user) {

		if (users.containsKey(id)) {
			users.put(id, user);
			return getUserById(id);
		}

		return null;
	}

	/**
	 * delete User
	 * 
	 * @param id User id
	 * 
	 * @return {@link User}
	 */
	public User deleteUser(int id) {
		
		if (users.containsKey(id)) {
			User userToDelete = users.get(id);
			userToDelete.setStatus("DEACTIVE");
			return updateUser(id, userToDelete);
		}

		return null;
	}

}
