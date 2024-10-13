package model;

public class Product {

	private int id;
	private String description;
	private double price;
	private int idCategory;
	private boolean available;

	public Product() {

	}

	public Product(int id, String description, double price, int idCategory, boolean available) {
		this.id = id;
		this.description = description;
		this.price = price;
		this.idCategory = idCategory;
		this.available = available;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(int idCategory) {
		this.idCategory = idCategory;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", description=" + description + ", price=" + price + ", idCategory=" + idCategory
				+ ", available=" + available + "]";
	}

}
