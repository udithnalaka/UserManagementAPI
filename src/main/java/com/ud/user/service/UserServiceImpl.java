package com.ud.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ud.user.dao.UserDao;
import com.ud.user.entity.User;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

	private UserDao userDao;

	@Autowired
	public UserServiceImpl(final UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserById(int id) {
		LOGGER.info("getUserById(). ID : {}", id);

		return userDao.getUserById(id);

	}

	@Override
	public User createUser(User user) {
		LOGGER.info("createUser(). User : {}", user);

		return userDao.saveUser(user);
	}

	@Override
	public User updateUser(int id, User user) {
		LOGGER.info("updateUser().  ID : {}, User : {}", id, user);

		return userDao.updateUser(id, user);
	}

	@Override
	public void deleteUser(List<Integer> ids) {
		LOGGER.info("deleteUser().  ID : {}", ids);

		 userDao.deleteUser(ids);
			 
	}

}
