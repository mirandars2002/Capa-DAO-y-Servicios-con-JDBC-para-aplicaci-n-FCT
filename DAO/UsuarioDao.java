package ceufct.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mariadb.jdbc.Statement;

import ceufct.modelo.Usuario;

public class UsuarioDao {

	public Usuario consultarLogin(Connection conn, String email, String password) throws SQLException {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Usuario usuario = new Usuario();
			String sql = "SELECT id_usuario FROM usuarios where email = " + "'" + email + "'" + " AND password = " + "'"
					+ password + "'";
			stmt = (Statement) conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				usuario.setEmail(rs.getString("email"));
				usuario.setPassword(rs.getString("password"));
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setCiclo(rs.getString("ciclo"));
				usuario.setActivo(rs.getBoolean("activo"));

			} else {
				return null;
			}
			return usuario;

		} finally {
			try {
				stmt.close();
			} catch (Exception e) {
			}
		}
	}

	public void insertarAlta(Connection conn, Usuario usuario) throws SQLException {
		PreparedStatement stmt = null;
		try {
			String sql = "insert  into usuarios  (id_usuario, email, password, nombre, apellidos, ciclo, activo) values (?,?,?,?,?,?,?)";
			stmt = conn.prepareStatement(sql);

			stmt.setLong(1, usuario.getIdUsuario());
			stmt.setString(2, usuario.getEmail());
			stmt.setString(3, usuario.getPassword());
			stmt.setString(4, usuario.getNombre());
			stmt.setString(5, usuario.getApellidos());
			stmt.setString(6, usuario.getCiclo());
			stmt.setBoolean(7, usuario.getActivo());

			stmt.execute();

		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}

}
