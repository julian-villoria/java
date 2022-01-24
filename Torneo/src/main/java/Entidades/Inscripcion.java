package Entidades;

import java.time.LocalDate;

public class Inscripcion {
	private Torneo t;
	private Jugador j;
	private LocalDate fechaInicioTorneo;
	private LocalDate fecha;
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
	public LocalDate getFechaInicioTorneo() {
		return fechaInicioTorneo;
	}
	public void setFechaInicioTorneo(LocalDate fechaInicioTorneo) {
		this.fechaInicioTorneo = fechaInicioTorneo;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
}
	
