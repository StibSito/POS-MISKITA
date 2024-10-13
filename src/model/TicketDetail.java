package model;

public class TicketDetail {

	private String num_bol;
	private int idprod;
	private int quantity;
	private double price;
	private double total;

	public TicketDetail() {
	}

	public TicketDetail(String num_bol, int idprod, int quantity, double price, double total) {
		this.num_bol = num_bol;
		this.idprod = idprod;
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

	public int getIdprod() {
		return idprod;
	}

	public void setIdprod(int idprod) {
		this.idprod = idprod;
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

	@Override
	public String toString() {
		return "TicketDetail [num_bol=" + num_bol + ", idprod=" + idprod + ", quantity=" + quantity + ", price=" + price
				+ ", total=" + total + "]";
	}

}
