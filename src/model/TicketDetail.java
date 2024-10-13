package model;

public class TicketDetail {

	private String num_bol;
	private int idProduct;
	private int quantity;
	private double price;
	private double total;
	
	public TicketDetail() {}
	
	public TicketDetail(String num_bol, int idProduct, int quantity, double price, double total) {
		this.num_bol = num_bol;
		this.idProduct = idProduct;
		this.quantity = quantity;
		this.price = price;
		this.total = total;
	}

	public String getNum_bol() {
		return num_bol;
	}

	public void setNum_bol(String num_bol) {
		this.num_bol = num_bol;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	
	
	
	


}
