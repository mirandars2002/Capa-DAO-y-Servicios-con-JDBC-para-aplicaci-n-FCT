package ceufct.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Registro {

	private Long idRegistro;
	private Long idUsuario;
	private LocalDate fecha;
	private BigDecimal numHoras;
	private String descripcion;

	public Long getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getNumHoras() {
		return numHoras;
	}

	public void setNumHoras(BigDecimal numHoras) {
		this.numHoras = numHoras;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Registros [idRegistro=" + idRegistro + ", idUsuario=" + idUsuario + ", fecha=" + fecha + ", numHoras="
				+ numHoras + ", descripcion=" + descripcion + "]";
	}

}
