package ceufct.modelo;

public class Usuario {

	private Long idUsuario;
	private String email;
	private String password;
	private String nombre;
	private String apellidos;
	private String ciclo;
	private Boolean activo;

	public Usuario() {
		this.activo = false;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCiclo() {
		return ciclo;
	}

	public void setCiclo(String ciclo) {
		this.ciclo = ciclo;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Usuarios [idUsuario=" + idUsuario + ", email=" + email + ", password=" + password + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", ciclo=" + ciclo + ", activo=" + activo + "]";
	}

}
