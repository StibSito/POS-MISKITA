package interfaces;

import java.util.List;

import model.Product;

public interface ProductInterfaces {

	// add product
	public int addProduct(Product p);

	// show details
	public Product detailProduct(int codigo);

	// update prod
	public int updateProduct(Product p);

	// detele product
	public int deleteProdcut(int code);

	// read products
	public List<Product> productList();

	// Método para obtener el siguiente ID de cliente
	public int getNextProductId();
	
	//search product by id
	public Product searchProductId(int code);
}
