package services;

import dao.PostgresUserDAO;
import entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserRegistratorService {
	private static User user;
	private static PostgresUserDAO userDao;
	
	public UserRegistratorService(User user) {
		this.user = user;
	}
	
	// usefull for registration jsp
	public boolean registration() {
		return userDao.createNew(user);
	}
}
