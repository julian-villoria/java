package Entidades;

public class Pais {

	private int id;
	
	private String nombre;

	public int getId() {
		return id;
	}

	public void setId(int id_pais) {
		this.id = id_pais;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Paises [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
