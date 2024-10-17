package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderDetail;
import util.DatabaseConnection;

public class OrderController {

	// Método para generar el número de orden
	// Método para insertar una nueva orden
	// Método para insertar una nueva orden
	public int addOrder(Order order) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		int generatedOrderNumber = 0;

		try {
			con = DatabaseConnection.getConexion();
			String sql = "{CAll addOrder(?,?,?,?,?,?)}";
			pst = con.prepareStatement(sql);

			if (order.getIdCliente() == 0) {
				pst.setNull(1, java.sql.Types.INTEGER);
			} else {
				pst.setInt(1, order.getIdCliente());
			}
			pst.setString(2, order.getFecha());
			pst.setString(3, order.getNomCli());
			pst.setDouble(4, order.getTotal());
			pst.setBoolean(5, order.isDelivery());
			pst.setInt(6, order.getState());

			// Ejecutar la inserción
			pst.executeUpdate();

			// Obtener la clave generada
			pst = con.prepareStatement("SELECT LAST_INSERT_ID()");
			rs = pst.executeQuery();
			if (rs.next()) {
				generatedOrderNumber = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("Error al insertar orden: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (con != null)
					con.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}

		return generatedOrderNumber; // Devolver el num_order generado
	}

	public void addOrderDetail(OrderDetail orderDetail) {
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = DatabaseConnection.getConexion();
			String sql = "INSERT INTO tb_orderdetail (num_order, idprod, quantity, total) VALUES (?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setInt(1, orderDetail.getNumOrder()); // Asegúrate de que este es el num_order correcto
			pst.setInt(2, orderDetail.getIdproduct());
			pst.setInt(3, orderDetail.getQuantity());
			pst.setDouble(4, orderDetail.getTotal());

			pst.executeUpdate(); // Ejecutar la inserción
		} catch (SQLException e) {
			System.out.println("Error al insertar detalle de pedido: " + e.getMessage());
		} finally {
			try {
				if (con != null)
					con.close();
				if (pst != null)
					pst.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
	}

	public List<Order> listOrders() {
		List<Order> lista = null;
		Connection con = null;

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getConexion();

			String sql = "{CALL listOrders()}";

			// prepara la sentencia
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			lista = new ArrayList<Order>();
			while (rs.next()) {
				Order o = new Order();
				o.setNumOrder(rs.getInt("num_order"));
				o.setIdCliente(rs.getInt("idClient"));
				o.setNomCli(rs.getString("clientNAme"));
				o.setDelivery(rs.getBoolean("isDelivery"));
				o.setState(rs.getInt("state"));
				lista.add(o);
			}

		} catch (Exception e) {
			System.out.println("Error al listar producto: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}
		return lista;
	}

	public Order orderDetail(int codigo) {
		String sql = "{CALL orderDetail(?)}";
		try (Connection conn = DatabaseConnection.getConexion(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setInt(1, codigo);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Order order = new Order();
					order.setNomCli(rs.getString("clientName"));
					order.setState(rs.getInt("state"));
					return order;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int updateOrder(Order o) {
		int ok = 0;
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = DatabaseConnection.getConexion();

			// Sentencia SQL
			String sql = "{CALL updateOrder(?, ?, ?)}"; // No necesitas modificar num_order

			pst = con.prepareStatement(sql);
			pst.setInt(1, o.getNumOrder()); // num_order ahora es int
			pst.setString(2, o.getNomCli());
			pst.setInt(3, o.getState());

			// Ejecutar
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en actualizar pedido: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}

		return ok;
	}

}
