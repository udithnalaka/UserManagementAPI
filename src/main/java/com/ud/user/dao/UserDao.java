package com.ud.user.dao;

import java.util.HashMap;
import java.util.List;
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
		users.put(1, new User(1, "Udith Nalaka", 35, "male", "ACTIVE", "u@n.com"));
		users.put(2, new User(2, "John Smith ", 90, "male", "ACTIVE", "j@s.com"));
		users.put(3, new User(3, "Ira Rana", 20, "female", "ACTIVE", "i@r.com"));
		users.put(4, new User(4, "Asa Rana", 50, "male", "ACTIVE", "a@r.com"));
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
	 * delete Users
	 * 
	 * @param ids List of User id's
	 * 
	 * @return void
	 */
	public void deleteUser(List<Integer> ids) {
		
		for (Integer id : ids) {
			if (users.containsKey(id)) {
				User userToDelete = users.get(id);
				userToDelete.setStatus("DEACTIVE");
			}
		}

	}

}
