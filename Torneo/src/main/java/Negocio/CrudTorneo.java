package Negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import Datos.DataJuego;
import Datos.DataTipoTorneo;
import Datos.DataTorneo;
import Entidades.Juego;
import Entidades.TipoTorneo;
import Entidades.Torneo;

public class CrudTorneo {
	
	public static LinkedList<Torneo> listaTorneo(){
		LinkedList<Torneo> dataTorneo = new LinkedList<Torneo>(); 
		dataTorneo = DataTorneo.list();
		return dataTorneo;
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
	
	public static void create(String denominacionJuego, String denominacionTipoTorneo, String fechaInicio, String fechaFin, int intentos, int cupo, String ganador, float montoInsc){
		Juego j = DataJuego.search(denominacionJuego);
		TipoTorneo tt = DataTipoTorneo.search(denominacionTipoTorneo);
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
		LocalDate fechaFinDate = LocalDate.parse(fechaFin, dtFormat);
		DataTorneo.create(j, tt, fechaInicioDate, fechaFinDate, intentos, cupo, ganador, montoInsc);
	}
	
	public static void update(String denominacionJuego, String denominacionTipoTorneo, String fechaInicio, String fechaFin, int intentos, int cupo, String ganador, float montoInsc){
		Juego j = DataJuego.search(denominacionJuego);
		TipoTorneo tt = DataTipoTorneo.search(denominacionTipoTorneo);
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
		LocalDate fechaFinDate = LocalDate.parse(fechaFin, dtFormat);
		DataTorneo.update(j, tt, fechaInicioDate, fechaFinDate, intentos, cupo, ganador, montoInsc);
	}
	
	public static void delete(int idJuego, int idTipo, String fechaInicio){
		int idJuegoBorrar = DataJuego.search(idJuego).getId();
		int idTipoBorrar = DataTipoTorneo.search(idTipo).getId();
		DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
		DataTorneo.delete(idJuegoBorrar, idTipoBorrar, fechaInicioDate);
	}
	
}
