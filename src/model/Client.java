package model;

public class Client {

	private int idClient;
	private String name;
	private String phone;
	private String address;

	public Client() {}

	
	public Client(int idClient, String name, String phone, String address) {
		this.idClient = idClient;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String adress) {
		this.address = adress;
	}

	

	@Override
	public String toString() {
		return "Client [idClient=" + idClient + ", name=" + name + ", phone=" + phone + ", address=" + address + "]";
	}

}
