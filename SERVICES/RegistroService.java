package ceufct.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.mariadb.jdbc.Connection;
import ceufct.dao.RegistroDao;
import ceufct.modelo.Registro;

public class RegistroService {

	private OpenConnection connProvider;

	public RegistroService() {
		connProvider = new OpenConnection();
	}

	public List<Registro> consultarRegistro(Long idUsuario)
			throws RegistroServiceException, AutenticarException, UsuarioServiceException {
		Connection conn = null;
		try {
			conn = connProvider.abrirConexion();

			List<Registro> registros = new ArrayList<Registro>();
			RegistroDao dao = new RegistroDao();
			Registro registroNuevo = dao.consultarRegistro(conn, idUsuario);

			if (registroNuevo != null) {
				if (registroNuevo.getIdUsuario() == idUsuario) {
					return registros;
				} else {
					throw new UsuarioServiceException("La contraseña es incorrecta");
				}
			} else {
				throw new AutenticarException("EL registro es incorrecto");
			}

		} catch (SQLException e) {
			throw new RegistroServiceException("Este usuario ya existe" + e.getMessage());

		} catch (ClassNotFoundException e) {
			throw new RegistroServiceException("Error al encontrar la clase " + e.getMessage());

		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}

	public void nuevoRegistro(Registro registro) throws RegistroServiceException, AutenticarException {
		Connection conn = null;
		try {
			conn = connProvider.abrirConexion();

			Registro registroNuevo = new Registro();
			RegistroDao dao = new RegistroDao();
			registroNuevo = dao.consultarRegistroFecha(conn, registro.getIdUsuario(), registro.getFecha());

			if (registroNuevo == null) {
				dao.insertarNuevoRegistro(conn, registroNuevo);
			} else {
				throw new RegistroServiceException("Este usuario ya existe");
			}

		} catch (SQLException e) {
			throw new RegistroServiceException("Este usuario ya existe" + e.getMessage());
		} catch (ClassNotFoundException e) {
			throw new RegistroServiceException("Error al encontrar la clase " + e.getMessage());

		} finally {
			try {
				conn.close();
			} catch (Exception e) {

			}
		}
	}

}
