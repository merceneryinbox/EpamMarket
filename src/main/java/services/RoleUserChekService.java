package services;

import dao.PostgresUserDAO;
import dao.UserDAO;
import entities.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleUserChekService {
	private static String login;
	private static String password;
	
	public RoleUserChekService() {
	}
	
	public UserRole checkUserRole(String login, String password) {
		UserRole role = UserRole.User;
		UserDAO userDAO = new PostgresUserDAO();
		
		Optional<User> userOptional = userDAO.getUserByLogin(login);
		if (userOptional != null) {
			User user = userOptional.get();
			
			// TODO: 18.12.2017 Restruct table User in DB - add Role field
			String s = user.getRole();
			
			switch (s) {
				case "Administrator":
					role = UserRole.Administrator;
					break;
				case "User":
					role = UserRole.User;
					break;
				case "Guest":
					role = UserRole.Guest;
					break;
			}
		}
		
		return role;
	}
	
}
