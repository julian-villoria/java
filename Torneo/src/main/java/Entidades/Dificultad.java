package Entidades;

public class Dificultad {
	
	private Juego juego;
	private String nombre;
	private int rango_puntajes;
	private int rango_victorias;
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getRango_puntajes() {
		return rango_puntajes;
	}
	public void setRango_puntajes(int rango_puntajes) {
		this.rango_puntajes = rango_puntajes;
	}
	public int getRango_victorias() {
		return rango_victorias;
	}
	public void setRango_victorias(int rango_victorias) {
		this.rango_victorias = rango_victorias;
	}
}
