package Entidades;

public class Juego {

	private int id;
	private String denominacion;
	private Dificultad d;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDenominacion() {
		return denominacion;
	}
	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}
	public Dificultad getDificultad() {
		return d;
	}
	public void setDificultad(Dificultad d) {
		this.d = d;
	}
	@Override
	public String toString() {
		return "Juego [id=" + id + ", denominacion=" + denominacion + "]";
	}
	
}
