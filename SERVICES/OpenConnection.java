package ceufct.services;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.mariadb.jdbc.Connection;

public class OpenConnection {

	public Connection abrirConexion() throws SQLException, ClassNotFoundException {
		String urlConexion = "jdbc:mariadb://localhost:3306/ceu-fct";
		String claseDriver = "org.mariadb.jdbc.Driver";
		String usuario = "ceufct";
		String password = "ceufct";

		try {
			Class.forName(claseDriver);
		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra la clase del driver. Revisa tu configuración");
			throw new RuntimeException("No se encuentra clase del driver", e);
		}
		return (Connection) DriverManager.getConnection(urlConexion, usuario, password);
	}
}
