package ceufct.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mariadb.jdbc.Connection;

import ceufct.dao.FechaDao;

import ceufct.modelo.Fecha;

public class FechaService {

	private OpenConnection connProvider;

	public FechaService() {
		connProvider = new OpenConnection();
	}

	public List<Fecha> consultarActuales() throws RegistroServiceException, AutenticarException, FechaServiceException {
		Connection conn = null;
		try {
			conn = connProvider.abrirConexion();

			List<Fecha> fechas = new ArrayList<Fecha>();
			FechaDao dao = new FechaDao();

			LocalDate año = LocalDate.now();
			Integer añoActual = año.getYear();
			Integer mesActual = año.getYear();
			Integer evaluacion = año.getYear();

			if (mesActual <= 11 && mesActual >= 9) {

				evaluacion = 1;

			}
			if (mesActual >= 12 && mesActual <= 2) {

				evaluacion = 2;

			}
			if (mesActual >= 3 && mesActual <= 5) {

				evaluacion = 3;

			}

			fechas = dao.consultarFecha(conn, añoActual, evaluacion);

			if (!fechas.isEmpty()) {
				return fechas;
			} else {
				throw new FechaServiceException("No hay fechas para esta evaluacion");
			}
		} catch (SQLException e) {
			throw new RegistroServiceException("Este registro no existe" + e.getMessage());

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
