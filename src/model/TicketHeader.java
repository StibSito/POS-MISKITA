package model;

public class TicketHeader {

	private String num_bol;
	private String date;
	private String nomCliente;

	public TicketHeader() {
	}

	public TicketHeader(String num_bol, String date, String num_pedido, String nomCliente) {
		this.num_bol = num_bol;
		this.date = date;
		this.nomCliente = nomCliente;
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

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	@Override
	public String toString() {
		return "TicketHeader [num_bol=" + num_bol + ", date=" + date + ", nomCliente=" + nomCliente + "]";
	}

}
