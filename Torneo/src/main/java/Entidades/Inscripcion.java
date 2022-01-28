package Entidades;

import java.sql.Date;
import java.time.LocalDate;

public class Inscripcion {
	private Torneo t;
	private Jugador j;
	private Date fechaInscripcion;
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
	public Date getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	
	public float getMonto() {
		return monto;
	}
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
}
	

