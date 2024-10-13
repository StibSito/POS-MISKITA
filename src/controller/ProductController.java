package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import interfaces.ProductInterfaces;
import model.CategoryItem;
import model.Product;
import util.DatabaseConnection;

public class ProductController implements ProductInterfaces {

	@Override
	public int addProduct(Product p) {
		int ok = 0;
		// prepare conection
		Connection con = null;
		// prepare statement
		PreparedStatement pst = null;
		try {
			// get conection
			con = DatabaseConnection.getConexion();
			// sql query
			String sql = "{CALL addProduct(?, ?, ?,?)}";

			pst = con.prepareStatement(sql);
			// set parameters
			pst.setString(1, p.getDescription());
			pst.setDouble(2, p.getPrice());
			pst.setInt(3, p.getIdCategory());
			pst.setBoolean(4, p.isAvailable());
			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error en registrar producto: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con); // close conection
		}

		return ok;
	}

	@Override
	public Product detailProduct(int codigo) {
		String sql = "{CALL productDetail(?)}";
		try (Connection conn = DatabaseConnection.getConexion(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setInt(1, codigo);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Product p = new Product();
					p.setId(rs.getInt("idProduct"));
					p.setDescription(rs.getString("description"));
					p.setPrice(rs.getDouble("price"));
					p.setIdCategory(rs.getInt("idCategory"));
					p.setAvailable(rs.getBoolean("available"));
					return p;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int updateProduct(Product p) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = DatabaseConnection.getConexion();

			// Sentencia SQL
			String sql = "{CALL updateProduct(?, ?, ?, ?, ?)}";

			pst = con.prepareStatement(sql);
			pst.setInt(1, p.getId());
			pst.setString(2, p.getDescription());
			pst.setDouble(3, p.getPrice());
			pst.setInt(4, p.getIdCategory());
			pst.setBoolean(5, p.isAvailable());

			// Ejecuta
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en actualizar: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}

		return ok;
	}

	@Override
	public int deleteProdcut(int code) {
		// Plantilla
		int ok = 0;
		// Prepara conexión
		Connection con = null;
		// preparar sentencia
		PreparedStatement pst = null;

		try {
			// obtiene la conexión
			con = DatabaseConnection.getConexion();

			// sentencia sql
			String sql = "{CALL deleteProduct(?)}";

			// prepara la sentencia
			pst = con.prepareStatement(sql);

			// setea los parámetros si existe
			pst.setInt(1, code);

			// actualiza la variable de control, ejecutando la sentencia
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en eliminar producto: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con); // cierra la conexión

		}

		return ok;
	}

	@Override
	public List<Product> productList() {
		List<Product> lista = null;
		Connection con = null;

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getConexion();

			String sql = "{CALL productList()} ";

			// prepara la sentencia
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			lista = new ArrayList<Product>();
			while (rs.next()) {
				Product c = new Product();
				c.setId(rs.getInt("idProduct"));
				c.setDescription(rs.getString("description"));
				c.setPrice(rs.getDouble("price"));
				c.setIdCategory(rs.getInt("idCategory"));
				c.setAvailable(rs.getBoolean("available"));

				lista.add(c);
			}

		} catch (Exception e) {
			System.out.println("Error al listar producto: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}
		return lista;
	}

	// Método para obtener el siguiente ID de producto
	public int getNextProductId() {
		Connection con = null;
		CallableStatement cst = null;
		int nextId = -1;

		try {
			// Obtener la conexión
			con = DatabaseConnection.getConexion();

			// Preparar la llamada al procedimiento
			String sql = "{CALL GetNextProductId(?)}";
			cst = con.prepareCall(sql);

			// Registrar el parámetro de salida
			cst.registerOutParameter(1, Types.INTEGER);

			// Ejecutar el procedimiento
			cst.execute();

			// Obtener el valor del parámetro de salida
			nextId = cst.getInt(1);

		} catch (SQLException e) {
			System.out.println("Error al obtener el siguiente ID de producto: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}
		return nextId;
	}

	@Override
	public Product searchProductId(int code) {
		String sql = "{CALL searchProductId(?)}";
		try (Connection conn = DatabaseConnection.getConexion(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setInt(1, code);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Product p = new Product();
					p.setDescription(rs.getString("description"));
					p.setPrice(rs.getDouble("price"));
					return p;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// En ProductController.java
	public List<CategoryItem> getCategories() {
		List<CategoryItem> categories = new ArrayList<>();
		String query = "SELECT idCategory, name FROM tb_category";
		try (Connection connection = DatabaseConnection.getConexion();
				PreparedStatement statement = connection.prepareStatement(query);
				ResultSet resultSet = statement.executeQuery()) {

			while (resultSet.next()) {
				int id = resultSet.getInt("idCategory");
				String name = resultSet.getString("name");
				categories.add(new CategoryItem(id, name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return categories;

	}

}
