package interfaces;

import java.util.ArrayList;

import model.TicketDetail;
import model.TicketHeader;

public interface SellsInterface {

	//create ticket number
	public String generarNumBoleta();
	
	//process order
	public int processOrder(TicketHeader cab, ArrayList<TicketDetail> det);
	
}
