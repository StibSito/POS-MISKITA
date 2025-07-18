package interfaces;

import java.util.List;

import model.Order;
import model.OrderDetail;
import model.OrderTicket;

public interface OrderInterface {

	//create order number
	public String generarNumBoleta();

	//insert order
	public void addOrder(Order order);

	//add order detail
	public void addOrderDetail(OrderDetail detail);

	//list
	public List<OrderTicket> listOrders();

	//get order Detail
	public Order orderDetail(String codigo);

	//update order
	public int updateOrder(Order o);
}
