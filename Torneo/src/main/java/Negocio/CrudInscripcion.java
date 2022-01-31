package Negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import Datos.DataJuego;
import Datos.DataJugador;
import Datos.DataTipoTorneo;
import Datos.DataInscripcion;
import Entidades.Juego;
import Entidades.Jugador;
import Entidades.TipoTorneo;
import Entidades.Torneo;
import Entidades.Inscripcion;

public class CrudInscripcion {
	public static LinkedList<Inscripcion> listaInscripcion(){
		LinkedList<Inscripcion> dataInscripcion = new LinkedList<Inscripcion>(); 
		dataInscripcion = DataInscripcion.list();
		return dataInscripcion;
	}
	
	public static LinkedList<Juego> listaJuego(){
		LinkedList<Juego> juegos = new LinkedList<Juego>();
		juegos = DataJuego.list();
		return juegos;
	}
	
	public static LinkedList<TipoTorneo> listaTipoTorneo(){
		LinkedList<TipoTorneo> dataTipoTorneo = new LinkedList<TipoTorneo>();
		dataTipoTorneo = DataTipoTorneo.list();
		return dataTipoTorneo;
	}
	
	public static void create(String denominacionJuego, String denominacionTipoTorneo, String fechaInicio, String fechaInsc, String usuario){
		Torneo t = new Torneo();
		Jugador jug = DataJugador.search(usuario);
		Juego j = DataJuego.search(denominacionJuego);
		TipoTorneo tt = DataTipoTorneo.search(denominacionTipoTorneo);
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
		LocalDate fechaInscDate = LocalDate.parse(fechaInsc, dtFormat);
		t.setJuego(j);
		t.setTipoTorneo(tt);
		t.setFechaInicio(fechaInicioDate);
		DataInscripcion.create(t, jug, fechaInscDate);
	}
	
	public static void update(String denominacionJuego, String denominacionTipoTorneo, String fechaInicio, String fechaInsc, String usuario){
		Torneo t = new Torneo();
		Jugador jug = DataJugador.search(usuario);
		Juego j = DataJuego.search(denominacionJuego);
		TipoTorneo tt = DataTipoTorneo.search(denominacionTipoTorneo);
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
		LocalDate fechaInscDate = LocalDate.parse(fechaInsc, dtFormat);
		t.setJuego(j);
		t.setTipoTorneo(tt);
		t.setFechaInicio(fechaInicioDate);
		DataInscripcion.update(t, jug, fechaInscDate);
	}
	
	public static void delete(String denominacionJuego, String denominacionTipoTorneo, String fechaInicio, String usuario){
		Torneo t = new Torneo();
		Jugador jug = DataJugador.search(usuario);
		Juego j = DataJuego.search(denominacionJuego);
		TipoTorneo tt = DataTipoTorneo.search(denominacionTipoTorneo);
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
		t.setJuego(j);
		t.setTipoTorneo(tt);
		t.setFechaInicio(fechaInicioDate);
		DataInscripcion.delete(t, jug);
	}
}
