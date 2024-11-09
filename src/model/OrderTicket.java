package model;

public class OrderTicket {

	private int numOrder;
	private int idCliente;
	private String fecha;
	private String nomCli;
	private double total;
	private boolean delivery;
	private int state;

	public OrderTicket() {
	}

	public OrderTicket(int numOrder, int idCliente, String fecha, String nomCli, double total, boolean delivery,
			int state) {
		this.numOrder = numOrder;
		this.idCliente = idCliente;
		this.fecha = fecha;
		this.nomCli = nomCli;
		this.total = total;
		this.delivery = delivery;
		this.state = state;
	}

	public int getNumOrder() {
		return numOrder;
	}

	public void setNumOrder(int numOrder) {
		this.numOrder = numOrder;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNomCli() {
		return nomCli;
	}

	public void setNomCli(String nomCli) {
		this.nomCli = nomCli;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isDelivery() {
		return delivery;
	}

	public void setDelivery(boolean delivery) {
		this.delivery = delivery;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "OrderTicket [numOrder=" + numOrder + ", idCliente=" + idCliente + ", fecha=" + fecha + ", nomCli="
				+ nomCli + ", total=" + total + ", delivery=" + delivery + ", state=" + state + "]";
	}

}
