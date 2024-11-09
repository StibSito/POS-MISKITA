package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import interfaces.SellsInterface;
import model.TicketDetail;
import model.TicketHeader;
import util.DatabaseConnection;

public class SellsController implements SellsInterface {

	public String generarNumBoleta() {
		String codigo = "BMIK-0000001"; // valor del código x default, cuando no hay datos

		// Plantilla
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			con = DatabaseConnection.getConexion();
			String sql = "select substr(max(num_ticket),6) from tb_TicketHeader";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			if (rs.next()) {
				codigo = String.format("BMIK-%07d", rs.getInt(1) + 1);
			}
		} catch (Exception e) {
			System.out.println("Error en generaNumBoleta : " + e.getMessage());
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("Error al cerrar: " + e.getMessage());
			}
		}
		return codigo;
	}

	public int processOrder(TicketHeader cab) {
		int rs = 0;
		Connection con = null;
		PreparedStatement pst1 = null; // Registrar cabecera boleta
		try {
			con = DatabaseConnection.getConexion();
			con.setAutoCommit(false); // Desactivar la confirmación automática

			String sql1 = "INSERT INTO tb_TicketHeader(num_ticket, date_ticket, idtipo,total,num_order) VALUES (?, NOW(), ?, ?,?)";
			pst1 = con.prepareStatement(sql1);

			cab.setNum_bol(generarNumBoleta());
			pst1.setString(1, cab.getNum_bol());
			pst1.setInt(2, cab.getIdtipo());
			pst1.setDouble(3, cab.getTotal());
			pst1.setInt(4,cab.getNum_order());
			rs = pst1.executeUpdate();


			con.commit();
		} catch (Exception e) {
			System.out.println("Error en registrar boleta : " + e.getMessage());
			rs = 0;
			try {
				con.rollback(); // Deshacer las operaciones registradas
			} catch (SQLException e1) {
				System.out.println("Error al cerrar : " + e1.getMessage());
			}
		} finally {
			DatabaseConnection.closeConexion(con);
		}
		return rs;
	}

}
