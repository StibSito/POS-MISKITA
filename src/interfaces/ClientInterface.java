package interfaces;

import java.util.List;

import model.Client;

public interface ClientInterface {

	// add all the data client
	public int addFullClient(Client client);

	// obtain clinet detail
	public Client clientDetail(int codigo);

	// update data from the client
	public int updateClient(Client cliente);

	// delete client through id
	public int deleteClient(int codigo);

	// list client
	public List<Client> clientList();

	// Método para obtener el siguiente ID de cliente
	public int getNextClientId();

}
