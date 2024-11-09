package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import interfaces.TipoPagoInterface;
import model.Tipo;
import util.DatabaseConnection;

public class TipoPagoController implements TipoPagoInterface {

	@Override
	public List<Tipo> lstTipoPagos() {
		List<Tipo> lista = null;
		Connection con = null;

		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			con = DatabaseConnection.getConexion();

			String sql = "SELECT * FROM tb_tipo_pago";

			// prepara la sentencia
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();

			lista = new ArrayList<Tipo>();
			while (rs.next()) {
				Tipo t = new Tipo();
				t.setIdtipo(rs.getInt("idtipo"));
				t.setName(rs.getString("name"));

				lista.add(t);
			}

		} catch (Exception e) {
			System.out.println("Error al listar tipos de pago: " + e.getMessage());
		} finally {
			DatabaseConnection.closeConexion(con);
		}
		return lista;
	}

}
