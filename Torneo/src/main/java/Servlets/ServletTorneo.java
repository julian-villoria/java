package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.DataTorneo;
import Datos.DataJuego;
import Datos.DataTipoTorneo;
import Entidades.Torneo;
import Entidades.Juego;
import Entidades.TipoTorneo;

/**
 * Servlet implementation class ServletTorneo
 */
@WebServlet("/ServletTorneo")
public class ServletTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTorneo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataTorneo dt = new DataTorneo(); 
		DataJuego dj = new DataJuego();
		DataTipoTorneo dtt = new DataTipoTorneo();
		LinkedList<Torneo> dataTorneo = dt.list();
		LinkedList<Juego> dataJuego = dj.list();
		LinkedList<TipoTorneo> dataTipoTorneo = dtt.list();
		request.setAttribute("Torneo", dataTorneo);
		request.setAttribute("Juego", dataJuego);
		request.setAttribute("TipoTorneo", dataTipoTorneo);
		getServletContext().getRequestDispatcher("/jsp/Torneo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(		request.getParameter("juegoNuevo") != null
				&& request.getParameter("tipoTorneoNuevo") != null
				&& request.getParameter("fechaInicioNuevo") != null 
				&& request.getParameter("fechaFinNuevo") != null
				&& request.getParameter("intentosNuevo") != null
				&& request.getParameter("cupoNuevo") != null
				&& request.getParameter("ganadorNuevo") != null
				&& request.getParameter("montoNuevo") != null) {
			DataTorneo dt = new DataTorneo(); 
			DataJuego dj = new DataJuego();
			DataTipoTorneo dtt = new DataTipoTorneo();
			String denominacionJuego = request.getParameter("juegoNuevo");
			Juego j = dj.search(denominacionJuego);
			String denominacionTipoTorneo = request.getParameter("tipoTorneoNuevo");
			TipoTorneo tt = dtt.search(denominacionTipoTorneo);
			String fechaInicio = request.getParameter("fechaInicioNuevo");
			String fechaFin = request.getParameter("fechaFinNuevo");
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
			LocalDate fechaFinDate = LocalDate.parse(fechaFin, dtFormat);
			int intentos = Integer.parseInt(request.getParameter("intentosNuevo"));
			int cupo = Integer.parseInt(request.getParameter("cupoNuevo"));
			String ganador = request.getParameter("ganadorNuevo");
			float montoInsc = Integer.parseInt(request.getParameter("montoNuevo"));
			dt.create(j, tt, fechaInicioDate, fechaFinDate, intentos, cupo, ganador, montoInsc);
			doGet(request, response);
		}
		if  (	request.getParameter("juegoActualizar") != null
				&& request.getParameter("tipoTorneoActualizar") != null
				&& request.getParameter("fechaInicioActualizar") != null 
				&& request.getParameter("fechaFinActualizar") != null
				&& request.getParameter("intentosActualizar") != null
				&& request.getParameter("cupoActualizar") != null
				&& request.getParameter("ganadorActualizar") != null
				&& request.getParameter("montoActualizar") != null){
			DataTorneo dt = new DataTorneo(); 
			DataJuego dj = new DataJuego();
			DataTipoTorneo dtt = new DataTipoTorneo();
			String denominacionJuego = request.getParameter("juegoActualizar");
			Juego j = dj.search(denominacionJuego);
			String denominacionTipoTorneo = request.getParameter("tipoTorneoActualizar");
			TipoTorneo tt = dtt.search(denominacionTipoTorneo);
			String fechaInicio = request.getParameter("fechaInicioActualizar");
			String fechaFin = request.getParameter("fechaFinActualizar");
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
			LocalDate fechaFinDate = LocalDate.parse(fechaFin, dtFormat);
			int intentos = Integer.parseInt(request.getParameter("intentosActualizar"));
			int cupo = Integer.parseInt(request.getParameter("cupoActualizar"));
			String ganador = request.getParameter("ganadorActualizar");
			float montoInsc = Integer.parseInt(request.getParameter("montoActualizar"));
			dt.update(j, tt, fechaInicioDate, fechaFinDate, intentos, cupo, ganador, montoInsc);
			doGet(request, response);
		}
		if(		request.getParameter("juegoEliminar") != null
				&& request.getParameter("tipoTorneoEliminar") != null
				&& request.getParameter("fechaInicioEliminar") != null){
			DataTorneo dt = new DataTorneo(); 
			DataJuego dj = new DataJuego();
			DataTipoTorneo dtt = new DataTipoTorneo();
			int idJuego = dj.search(request.getParameter("juegoEliminar")).getId();
			int idTipo = dtt.search(request.getParameter("tipoTorneoEliminar")).getId();
			String fechaInicio = request.getParameter("fechaInicioEliminar");
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
			dt.delete(idJuego, idTipo, fechaInicioDate);
			doGet(request, response);
		}
	}

}
