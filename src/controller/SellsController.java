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

public class SellsController implements SellsInterface{

	
	public String generarNumBoleta() {
		String codigo = "BMIK-00001"; // valor del código x default, cuando no hay datos

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
				codigo = String.format("BMIK-%05d", rs.getInt(1) + 1);
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
	
	
	public int processOrder(TicketHeader cab, ArrayList<TicketDetail> det) {
        int rs = 0;
        Connection con = null;
        PreparedStatement pst1 = null; // Registrar cabecera boleta
        PreparedStatement pst2 = null; // Registrar detalle de boleta

        try {
            con = DatabaseConnection.getConexion();
            con.setAutoCommit(false); // Desactivar la confirmación automática

            // Insertar cabecera boleta
            String sql1 = "insert into tb_TicketHeader(num_ticket, date_ticket, num_order, clientName) values (?, NOW(), ?, ?)";
            pst1 = con.prepareStatement(sql1);

            cab.setNum_bol(generarNumBoleta());
            pst1.setString(1, cab.getNum_bol());
            pst1.setString(2, cab.getNum_pedido());
            pst1.setString(3, cab.getNomCliente());
            rs = pst1.executeUpdate();

            // Insertar detalle boleta
            String sql2 = "insert into tb_TicketDetail(num_ticket, idProduct, quantity, price, total) values (?, ?, ?, ?, ?)";
            for (TicketDetail d : det) {
                pst2 = con.prepareStatement(sql2);
                pst2.setString(1, cab.getNum_bol()); // mismo dato de la cabecera
                pst2.setInt(2, d.getIdProduct());
                pst2.setInt(3, d.getQuantity());
                pst2.setDouble(4, d.getPrice());
                pst2.setDouble(5, d.getTotal());
                rs += pst2.executeUpdate();
            }

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
