package Entidades;

import java.time.LocalDate;

public class Inscripcion {
	private Torneo t;
	private Jugador j;
	private LocalDate fecha;
	
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
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
}
