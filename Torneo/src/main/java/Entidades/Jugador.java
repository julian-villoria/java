package Entidades;

public class Jugador {

	private int id;
	private String usuario;
	private String nombre;
	private String apellido;
	private String contrase�a;
	private String acceso;
	private int reportes;
	private Pais pais;
	
	public Pais getPais() {
		return pais;
	}
	public void setPais(Pais pais) {
		this.pais = pais;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getContrase�a() {
		return contrase�a;
	}
	public void setContrase�a(String contrase�a) {
		this.contrase�a = contrase�a;
	}
	public String getAcceso() {
		return acceso;
	}
	public void setAcceso(String acceso) {
		this.acceso = acceso;
	}
	public int getReportes() {
		return reportes;
	}
	public void setReportes(int reportes) {
		this.reportes = reportes;
	}
	@Override
	public String toString() {
		return "Jugador [id=" + id + ", usuario=" + usuario + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", contrase�a=" + contrase�a + ", pais=" + pais + "]";
	}

	
	
	
	
}
