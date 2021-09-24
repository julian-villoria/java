package Entidades;
import java.time.LocalDateTime;

public class Partida {
	private int id;
	private LocalDateTime fechaHora;
	private Jugador jugador;
	private Juego juego;
	private int puntaje;
	private boolean victoria;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}
	public void setFechaHora(LocalDateTime fechaHora) {
		this.fechaHora = fechaHora;
	}
	public Jugador getJugador() {
		return jugador;
	}
	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	public int getPuntaje() {
		return puntaje;
	}
	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	public boolean isVictoria() {
		return victoria;
	}
	public void setVictoria(boolean victoria) {
		this.victoria = victoria;
	}
	

}
