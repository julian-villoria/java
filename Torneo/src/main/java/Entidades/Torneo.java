package Entidades;
import java.sql.Date;
import java.time.*;

public class Torneo {

	private Date fechaInicio;
	private Date fechaFin;
	private int intentos;
	private int cupo;
	private String ganador;
	private float montoInsc;
	private Juego juego;
	private TipoTorneo tipoTorneo;
	
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
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
	public float getMontoInsc() {
		return montoInsc;
	}
	public void setMontoInsc(float montoInsc) {
		this.montoInsc = montoInsc;
	}
	
}
