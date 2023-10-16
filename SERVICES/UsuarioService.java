package ceufct.services;

import java.sql.SQLException;

import org.mariadb.jdbc.Connection;

import ceufct.dao.UsuarioDao;
import ceufct.modelo.Usuario;

public class UsuarioService {

	private OpenConnection connProvider;

	public UsuarioService() {
		connProvider = new OpenConnection();
	}

	public Usuario consultarUsuario(String email, String password) throws UsuarioServiceException, AutenticarException {
		Connection conn = null;
		try {
			conn = connProvider.abrirConexion();
			Usuario usuario1 = new Usuario();
			UsuarioDao dao = new UsuarioDao();
			usuario1 = dao.consultarLogin(conn, email, password);

			if (usuario1 != null) {

				if (usuario1.getEmail().equals(email) && usuario1.getPassword().equals(password)) {
					return usuario1;
				} else {
					throw new UsuarioServiceException("La contraseña es incorrecta");
				}
			} else {
				throw new AutenticarException("EL usuario es incorrecto");
			}

		} catch (SQLException e) {
			throw new UsuarioServiceException("Este usuario no existe" + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new UsuarioServiceException("Error al encontrar la clase " + e.getMessage());

		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}

	public void insertarAltaNueva(Usuario usuario) throws UsuarioServiceException {
		Connection conn = null;
		try {
			conn = connProvider.abrirConexion();

			UsuarioDao dao = new UsuarioDao();
			Usuario nuevoUsuario = new Usuario();
			nuevoUsuario = dao.consultarLogin(conn, usuario.getEmail(), usuario.getPassword());

			if (nuevoUsuario == null) {
				dao.insertarAlta(conn, usuario);
			} else {
				throw new UsuarioServiceException("El usuario ya existe");
			}

		} catch (SQLException e) {

			throw new UsuarioServiceException("Error al registrar el usuario", e);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} finally {
			try {
				conn.close();
			} catch (Exception ignore) {
			}
		}
	}

}
