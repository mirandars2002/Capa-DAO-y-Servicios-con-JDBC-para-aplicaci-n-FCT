package ceufct.dao;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Statement;

import ceufct.modelo.Fecha;

public class FechaDao {
	public List<Fecha> consultarFecha(Connection conn, Integer año, Integer evaluacion) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			List<Fecha> fechas = new ArrayList<Fecha>();
			String sql = "SELECT fechas FROM registros where año = " + año + " AND evaluacion = " + evaluacion;
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				Fecha fecha = new Fecha();
				fecha.setFecha(rs.getDate("fecha").toLocalDate());
				fecha.setAño(rs.getInt("año"));
				fecha.setEvaluacion(rs.getInt("evaluacion"));
				fecha.setDisponibilidad(rs.getBoolean("disponibilidad"));
				fechas.add(fecha);

			}
			return fechas;
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
	}

}
