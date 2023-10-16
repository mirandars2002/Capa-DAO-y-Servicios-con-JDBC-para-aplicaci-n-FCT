package ceufct.test;

import java.math.BigDecimal;
import java.time.LocalDate;

import ceufct.modelo.Registro;
import ceufct.services.AutenticarException;
import ceufct.services.RegistroService;
import ceufct.services.RegistroServiceException;
import ceufct.services.UsuarioServiceException;

public class App {

	public static void main(String[] args)
			throws UsuarioServiceException, AutenticarException, RegistroServiceException {

		RegistroService service = new RegistroService();

		Registro r = new Registro();
		r.setIdRegistro(Long.parseLong("1111112222222233"));
		r.setIdUsuario(Long.parseLong("4444433332222"));
		r.setFecha(LocalDate.of(2023, 10, 14));
		r.setNumHoras(new BigDecimal(400));
		r.setDescripcion("DAM");

		service.nuevoRegistro(r);

	}
}