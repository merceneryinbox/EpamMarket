package main.resources;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table(name = "users", schema = "public", catalog = "postgres") public class UsersEntity {
	private String login;
	private String email;
	private long phoneNumber;
	private String password;
	private boolean adminPermis;
	private boolean blocked;
	private CartEntity cartByLogin;
	
	@Id @Column(name = "login", nullable = false, length = 32) public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Basic @Column(name = "email", nullable = false, length = 256) public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Basic @Column(name = "phone_number", nullable = false) public long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	@Basic @Column(name = "password", nullable = false, length = 128) public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Basic @Column(name = "admin_permis", nullable = false) public boolean isAdminPermis() {
		return adminPermis;
	}
	
	public void setAdminPermis(boolean adminPermis) {
		this.adminPermis = adminPermis;
	}
	
	@Basic @Column(name = "blocked", nullable = false) public boolean isBlocked() {
		return blocked;
	}
	
	public void setBlocked(boolean blocked) {
		this.blocked = blocked;
	}
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UsersEntity that = (UsersEntity) o;
		return phoneNumber == that.phoneNumber &&
		       adminPermis == that.adminPermis &&
		       blocked == that.blocked &&
		       Objects.equals(login, that.login) &&
		       Objects.equals(email, that.email) &&
		       Objects.equals(password, that.password);
	}
	
	@Override public int hashCode() {
		
		return Objects.hash(login, email, phoneNumber, password, adminPermis, blocked);
	}
	
	@OneToOne(mappedBy = "usersByLogin") public CartEntity getCartByLogin() {
		return cartByLogin;
	}
	
	public void setCartByLogin(CartEntity cartByLogin) {
		this.cartByLogin = cartByLogin;
	}
}
