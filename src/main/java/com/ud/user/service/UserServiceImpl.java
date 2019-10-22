package com.ud.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.ud.user.dao.UserDao;
import com.ud.user.entity.Mail;
import com.ud.user.entity.User;
import com.ud.user.util.EmailService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String EMAIL_SENDER = "udith@me.com";

	private UserDao userDao;
	
	@Autowired
	EmailService emailService;

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

		User newUser = null;

		try {
			newUser = userDao.saveUser(user);

			if (newUser != null) {
				emailService.sendEmail(new Mail(EMAIL_SENDER, newUser.getEmail(), "New User Registration",
						"Registration completed. Welcome to our Company"));
				LOGGER.info("createUser(). Email sent successfully");
			}
		} catch (MailException me) {
			LOGGER.error("createUser(). Error sending Email - {}", me);
		}

		return newUser;
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
