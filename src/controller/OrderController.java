package controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Order;
import model.OrderDetail;
import util.DatabaseConnection;

public class OrderController {

	// Método para generar el número de orden
	public String generateOrderNumber() {
		String codigo = "N00001"; // Valor por defecto
		String sql = "SELECT SUBSTR(MAX(num_order), 2) FROM tb_Order";

		try (Connection con = DatabaseConnection.getConexion();
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery()) {

			if (rs.next()) {
				int maxNum = rs.getInt(1); // Obtener el valor del ResultSet
				if (maxNum != 0) {
					codigo = String.format("N%05d", maxNum + 1); // Incrementar y formatear
				}
			}
		} catch (Exception e) {
			System.out.println("Error en generateOrderNumber: " + e.getMessage());
		}

		return codigo;
	}

	// Método para insertar una nueva orden
	public void addOrder(Order order) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = DatabaseConnection.getConexion();
			String sql = "{CAll addOrder(?,?,?,?,?,?,?)}";
			pst = con.prepareStatement(sql);
			pst.setString(1, order.getNumOrder());
			// Manejar el caso en que idCliente puede ser nulo
			if (order.getIdCliente() == 0) {
				pst.setNull(2, java.sql.Types.INTEGER);
			} else {
				pst.setInt(2, order.getIdCliente());
			}
			pst.setString(3, order.getFecha());
			pst.setString(4, order.getNomCli());
			pst.setDouble(5, order.getTotal());
			pst.setBoolean(6, order.isDelivery());
			pst.setInt(7, order.getState());
			pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error al insertar orden: " + e.getMessage());
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

	// Método para insertar un detalle de pedido
	public void addOrderDetail(OrderDetail detail) {
		Connection con = null;
		PreparedStatement pst = null;
		try {
			con = DatabaseConnection.getConexion();
			String sql = "INSERT INTO tb_orderDetail(num_order, idprod, quantity, total) VALUES (?, ?, ?, ?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, detail.getNumOrder());
			pst.setInt(2, detail.getIdproduct());
			pst.setInt(3, detail.getQuantity());
			pst.setDouble(4, detail.getTotal());
			pst.executeUpdate();
		} catch (Exception e) {
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
				o.setNumOrder(rs.getString("num_order"));
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

	public Order orderDetail(String codigo) {
		String sql = "{CALL orderDetail(?)}";
		try (Connection conn = DatabaseConnection.getConexion(); CallableStatement stmt = conn.prepareCall(sql)) {
			stmt.setString(1, codigo);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					Order order = new Order();
					order.setNumOrder(codigo);
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
			String sql = "{CALL updateOrder(?, ?, ?)}";

			pst = con.prepareStatement(sql);
			pst.setString(1, o.getNumOrder());
			pst.setString(2, o.getNomCli());
			pst.setInt(3, o.getState());

			// Ejecuta
			ok = pst.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error en actualizar pedido: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}

		return ok;
	}

}
