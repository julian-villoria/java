package Entidades;

public class Dificultad {
	
	private Juego juego;
	private String nombre;
	private int rangoMinPuntajes;
	private int rangoMaxPuntajes;
	private int rangoMinVictorias;
	private int rangoMaxVictorias;
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
	public int getRangoMinPuntajes() {
		return rangoMinPuntajes;
	}
	public void setRangoMinPuntajes(int rangoMinPuntajes) {
		this.rangoMinPuntajes = rangoMinPuntajes;
	}
	public int getRangoMaxPuntajes() {
		return rangoMaxPuntajes;
	}
	public void setRangoMaxPuntajes(int rangoMaxPuntajes) {
		this.rangoMaxPuntajes = rangoMaxPuntajes;
	}
	public int getRangoMinVictorias() {
		return rangoMinVictorias;
	}
	public void setRangoMinVictorias(int rangoMinVictorias) {
		this.rangoMinVictorias = rangoMinVictorias;
	}
	public int getRangoMaxVictorias() {
		return rangoMaxVictorias;
	}
	public void setRangoMaxVictorias(int rangoMaxVictorias) {
		this.rangoMaxVictorias = rangoMaxVictorias;
	}
}