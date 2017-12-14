package main.resources;

import javax.persistence.*;
import java.util.Objects;

@Entity @Table(name = "goods", schema = "public", catalog = "postgres") public class GoodsEntity {
	private int id;
	private String productName;
	private float price;
	private int amount;
	private String description;
	private String image;
	
	@Id @Column(name = "id", nullable = false) public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	@Basic @Column(name = "product_name", nullable = false, length = 256)
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	@Basic @Column(name = "price", nullable = false, precision = 8)
	public float getPrice() {
		return price;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	@Basic @Column(name = "amount", nullable = false) public int getAmount() {
		return amount;
	}
	
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Basic @Column(name = "description", nullable = false, length = 1024)
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Basic @Column(name = "image", nullable = true, length = 256)
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	@Override public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GoodsEntity that = (GoodsEntity) o;
		return id == that.id &&
		       Float.compare(that.price, price) == 0 &&
		       amount == that.amount &&
		       Objects.equals(productName, that.productName) &&
		       Objects.equals(description, that.description) &&
		       Objects.equals(image, that.image);
	}
	
	@Override public int hashCode() {
		
		return Objects.hash(id, productName, price, amount, description, image);
	}
}
