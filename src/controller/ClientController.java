package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import interfaces.ClientInterface;
import model.Client;
import util.DatabaseConnection;

public class ClientController implements ClientInterface {

	public int addFullClient(Client client) {
		int ok = 0;

		// prepare conection
		Connection con = null;
		// prepare statement
		PreparedStatement pst = null;

		try {
			// get conection
			con = DatabaseConnection.getConexion();
			// sql query
			String sql = "{CALL addFullClient(?, ?, ?)}";

			pst = con.prepareStatement(sql);
			// set parameters
			pst.setString(1, client.getName());
			pst.setString(2, client.getPhone());
			pst.setString(3, client.getAddress());
			pst.executeUpdate();

		} catch (Exception e) {
			System.out.println("Error en registrar: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con); // close conection
		}

		return ok;
	}

	public Client clientDetail(int codigo) {
		String sql = "{CALL clientDetail(?)}";
		try (Connection conn = DatabaseConnection.getConexion(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setInt(1, codigo);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Client cliente = new Client();
					cliente.setIdClient(rs.getInt("idClient"));
					cliente.setName(rs.getString("name"));
					cliente.setPhone(rs.getString("phone"));
					cliente.setAddress(rs.getString("address"));
					return cliente;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateClient(Client c) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = DatabaseConnection.getConexion();

			// Sentencia SQL
			String sql = "{CALL updateClient(?, ?, ?, ?,)}";

			pst = con.prepareStatement(sql);
			pst.setInt(1, c.getIdClient());
			pst.setString(2, c.getName());
			pst.setString(3, c.getPhone());
			pst.setString(4, c.getAddress());

			// Ejecuta
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en actualizar cliente: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}

		return ok;
	}

	public int deleteClient(int codigo) {
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
			String sql = "{CALL deleteClient(?)}";

			// prepara la sentencia
			pst = con.prepareStatement(sql);

			// setea los parámetros si existe
			pst.setInt(1, codigo);

			// actualiza la variable de control, ejecutando la sentencia
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en eliminar cliente: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con); // cierra la conexión

		}

		return ok;
	}

	public List<Client> clientList() {
		List<Client> lista = null;
		Connection con = null;

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getConexion();

			String sql = "{CALL clientList()} ";

			// prepara la sentencia
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			lista = new ArrayList<Client>();
			while (rs.next()) {
				Client c = new Client();
				c.setIdClient(rs.getInt("idClient"));
				c.setName(rs.getString("name"));
				c.setPhone(rs.getString("phone"));
				c.setAddress(rs.getString("address"));

				lista.add(c);
			}

		} catch (Exception e) {
			System.out.println("Error al listar: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}
		return lista;
	}

	// Método para obtener el siguiente ID de cliente
	public int getNextClientId() {
		Connection con = null;
		CallableStatement cst = null;
		int nextId = -1;

		try {
			// Obtener la conexión
			con = DatabaseConnection.getConexion();

			// Preparar la llamada al procedimiento
			String sql = "{CALL GetNextClientId(?)}";
			cst = con.prepareCall(sql);

			// Registrar el parámetro de salida
			cst.registerOutParameter(1, Types.INTEGER);

			// Ejecutar el procedimiento
			cst.execute();

			// Obtener el valor del parámetro de salida
			nextId = cst.getInt(1);

		} catch (SQLException e) {
			System.out.println("Error al obtener el siguiente ID: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}
		return nextId;
	}

	public Client searchClientId(int code) {
		String sql = "{CALL clientDetail(?)}";
		try (Connection conn = DatabaseConnection.getConexion(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setInt(1, code);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Client cliente = new Client();
					cliente.setName(rs.getString("name"));
					cliente.setPhone(rs.getString("phone"));
					cliente.setAddress(rs.getString("address"));
					return cliente;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
