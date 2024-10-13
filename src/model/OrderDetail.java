package model;

public class OrderDetail {

	private String numOrder;
	private int idproduct;
	private int quantity;
	private double total;

	public OrderDetail() {
	}

	public OrderDetail(String numOrder, int idproduct, int quantity, double total) {
		this.numOrder = numOrder;
		this.idproduct = idproduct;
		this.quantity = quantity;
		this.total = total;
	}

	public String getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(String numOrder) {
		this.numOrder = numOrder;
	}

	public int getIdproduct() {
		return idproduct;
	}

	public void setIdproduct(int idproduct) {
		this.idproduct = idproduct;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "OrderDetail [numOrder=" + numOrder + ", idproduct=" + idproduct + ", quantity=" + quantity + ", total="
				+ total + "]";
	}

}
