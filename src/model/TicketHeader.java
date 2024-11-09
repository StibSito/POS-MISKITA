package model;

public class TicketHeader {

	private String num_bol;
	private String date;
	private int idtipo;
	private double total;
	private int num_order;

	public TicketHeader() {
	}

	public TicketHeader(String num_bol, String date, int idtipo, double total, int num_order) {
		this.num_bol = num_bol;
		this.date = date;
		this.idtipo = idtipo;
		this.total = total;
		this.num_order = num_order;
	}

	public String getNum_bol() {
		return num_bol;
	}

	public void setNum_bol(String num_bol) {
		this.num_bol = num_bol;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getIdtipo() {
		return idtipo;
	}

	public void setIdtipo(int idtipo) {
		this.idtipo = idtipo;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public int getNum_order() {
		return num_order;
	}

	public void setNum_order(int num_order) {
		this.num_order = num_order;
	}

	@Override
	public String toString() {
		return "TicketHeader [num_bol=" + num_bol + ", date=" + date + ", idtipo=" + idtipo + ", total=" + total
				+ ", num_order=" + num_order + "]";
	}

}
