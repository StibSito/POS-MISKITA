package model;

public class TicketHeader{

	private String num_bol;
    private String date;
    private String num_pedido;
    private String nomCliente;
    
    public TicketHeader() {}
    
	public TicketHeader(String num_bol, String date, String num_pedido, String nomCliente) {
		this.num_bol = num_bol;
		this.date = date;
		this.num_pedido = num_pedido;
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

	public String getNum_pedido() {
		return num_pedido;
	}

	public void setNum_pedido(String num_pedido) {
		this.num_pedido = num_pedido;
	}

	public String getNomCliente() {
		return nomCliente;
	}

	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	@Override
	public String toString() {
		return "TicketHeader [num_bol=" + num_bol + ", date=" + date + ", num_pedido=" + num_pedido + ", nomCliente="
				+ nomCliente + "]";
	}
    
	
    
    
    
}
