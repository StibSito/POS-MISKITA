package interfaces;

import model.TicketHeader;

public interface SellsInterface {

	//create ticket number
	public String generarNumBoleta();
	
	//process order
	public int processOrder(TicketHeader cab);
	
}
