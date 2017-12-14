package main.resources;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity @Table(name = "cart", schema = "public", catalog = "postgres") public class CartEntity {
	private int amount;
	private Timestamp reserveTime;
	private String login;
	
	@Basic @Column(name = "amount", nullable = false) public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Basic @Column(name = "reserve_time", nullable = false)
	public Timestamp getReserveTime() {
		return reserveTime;
	}
	
	public void setReserveTime(Timestamp reserveTime) {
		this.reserveTime = reserveTime;
	}
	
	@Id @Column(name = "login", nullable = false, length = 32)
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		CartEntity that = (CartEntity) o;
		return amount == that.amount &&
		       Objects.equals(reserveTime, that.reserveTime) &&
		       Objects.equals(login, that.login);
	}
	
	@Override public int hashCode() {
		
		return Objects.hash(amount, reserveTime, login);
	}
}
