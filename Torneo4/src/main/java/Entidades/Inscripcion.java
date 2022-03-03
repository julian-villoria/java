package Entidades;

import java.time.LocalDate;

public class Inscripcion {
	private Torneo t;
	private Jugador j;
	private Juego jue;
	private LocalDate fechaInscripcion;
	private float monto;
	
	public Torneo getTorneo() {
		return t;
	}
	public void setTorneo(Torneo t) {
		this.t = t;
	}
	public Jugador getJugador() {
		return j;
	}
	public void setJugador(Jugador j) {
		this.j = j;
	}
	public LocalDate getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(LocalDate fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	public Torneo getT() {
		return t;
	}
	public void setT(Torneo t) {
		this.t = t;
	}
	public Jugador getJ() {
		return j;
	}
	public void setJ(Jugador j) {
		this.j = j;
	}
	public Juego getJue() {
		return jue;
	}
	public void setJue(Juego jue) {
		this.jue = jue;
	}
	@Override
	public String toString() {
		return "Inscripcion [t=" + t + ", j=" + j + ", jue=" + jue + ", fechaInscripcion=" + fechaInscripcion
				+ ", monto=" + monto + "]\n";
	}
	
}
	

