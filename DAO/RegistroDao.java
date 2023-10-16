package ceufct.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.mariadb.jdbc.Statement;

import ceufct.modelo.Registro;

public class RegistroDao {

	public Registro consultarRegistro(Connection conn, Long idUsuario) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Registro registros = new Registro();
			String sql = "SELECT registro FROM registros where id_usuario = " + idUsuario;
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				Registro registro = new Registro();
				registro.setIdUsuario(rs.getLong("id_usuario"));
				registro.setIdRegistro(rs.getLong("id_registro"));
				registro.setFecha(rs.getDate("fecha").toLocalDate());
				registro.setNumHoras(rs.getBigDecimal("num_horas"));
				registro.setDescripcion(rs.getString("descripcion"));

			} else {
				return null;
			}
			return registros;
		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public Registro consultarRegistroFecha(Connection conn, Long idUsuario, LocalDate fecha) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Registro registro = new Registro();
			String sql = "SELECT registro FROM registros where id_usuario = " + idUsuario + "AND fecha = " + fecha;
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				registro.setIdUsuario(rs.getLong("id_usuario"));
				registro.setIdRegistro(rs.getLong("id_registro"));
				registro.setFecha(rs.getDate("fecha").toLocalDate());
				registro.setNumHoras(rs.getBigDecimal("num_horas"));
				registro.setDescripcion(rs.getString("descripcion"));
			}
			return registro;

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {

			}
		}
	}

	public void insertarNuevoRegistro(Connection conn, Registro registro) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "insert into registros  (id_registro, id_usuario, fecha, num_horas, descripcion) values (?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setLong(1, registro.getIdRegistro());
			stmt.setLong(2, registro.getIdUsuario());
			stmt.setDate(3, Date.valueOf(registro.getFecha()));
			stmt.setBigDecimal(4, registro.getNumHoras());
			stmt.setString(5, registro.getDescripcion());
			stmt.execute();

		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}
}
