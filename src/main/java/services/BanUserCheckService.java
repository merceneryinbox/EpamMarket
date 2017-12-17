package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BanUserCheckService {
	private static String login = null;
	private static UserDAO userDAO = new PostgresUserDAO();
	
	public BanUserCheckService() {
	}
	
	public BanUserCheckService(String login) {
		this.login = login;
	}
	
	public boolean checkUserBan() {
		Optional<User> optUser = userDAO.getUserByLogin(login);
		// TODO: 18.12.2017 Change the structure of User_table in DB
		
		return optUser.isPresent() ? optUser.get().getStatus() : false;
		
	}
}