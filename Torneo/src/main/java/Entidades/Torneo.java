package Entidades;
import java.time.*;

public class Torneo {

	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	private int intentos;
	private int cupo;
	private String ganador;
	private Juego juego;
	private TipoTorneo tipoTorneo;
	
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getIntentos() {
		return intentos;
	}
	public void setIntentos(int intentos) {
		this.intentos = intentos;
	}
	public int getCupo() {
		return cupo;
	}
	public void setCupo(int cupo) {
		this.cupo = cupo;
	}
	public String getGanador() {
		return ganador;
	}
	public void setGanador(String ganador) {
		this.ganador = ganador;
	}
	public Juego getJuego() {
		return juego;
	}
	public void setJuego(Juego juego) {
		this.juego = juego;
	}
	public TipoTorneo getTipoTorneo() {
		return tipoTorneo;
	}
	public void setTipoTorneo(TipoTorneo tipoTorneo) {
		this.tipoTorneo = tipoTorneo;
	}
	
	
}
