package services;

import dao.PostgresUserDAO;
import entities.User;
import org.springframework.stereotype.Service;

@Service
public class UserRegistrator {
	
	public UserRegistrator() {
	}

	PostgresUserDAO udao = new PostgresUserDAO();

	public boolean registrate(User user) {

		boolean result = false;
// TODO: 19.12.2017 Wait for PostgreUserDao constructor change into constructor without parameters
		return result;
	}
}
