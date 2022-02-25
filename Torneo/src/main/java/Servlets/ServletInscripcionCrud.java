package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataInscripcion;
import Datos.DataJuego;
import Datos.DataJugador;
import Datos.DataTipoTorneo;
import Entidades.Inscripcion;
import Entidades.Juego;
import Entidades.Jugador;
import Entidades.TipoTorneo;
import Entidades.Torneo;

/**
 * Servlet implementation class ServletInscripcionCrud
 */
@WebServlet("/ServletInscripcionCrud")
public class ServletInscripcionCrud extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscripcionCrud() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(true);
		Jugador jugador = (Jugador) session.getAttribute("jugador");
		if(jugador == null) {
			getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
		}else {
			if(jugador.getId() != 0 && jugador.getAcceso().equals("Administrador")) {
		DataInscripcion di = new DataInscripcion();
		DataJuego dj = new DataJuego();
		DataTipoTorneo dtt = new DataTipoTorneo();
		request.setAttribute("Inscripcion", di.list());
		request.setAttribute("Juego", dj.list());;
		request.setAttribute("TipoTorneo", dtt.list());
		getServletContext().getRequestDispatcher("/jsp/InscripcionCrud.jsp").forward(request, response);
			}else {
				getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
			}
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(		request.getParameter("juegoNuevo") != null
				&& request.getParameter("tipoTorneoNuevo") != null
				&& request.getParameter("fechaInicioNuevo") != null 
				&& request.getParameter("fechaInscNuevo") != null
				&& request.getParameter("jugadorNuevo") != null) {
			Torneo t = new Torneo();
			DataJuego dj = new DataJuego();
			DataJugador djug = new DataJugador();
			DataTipoTorneo dtt = new DataTipoTorneo();
			DataInscripcion di = new DataInscripcion();
			Jugador jug = djug.search(request.getParameter("jugadorNuevo"));
			Juego j = dj.search(request.getParameter("juegoNuevo"));
			TipoTorneo tt = dtt.search(request.getParameter("tipoTorneoNuevo"));
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			t.setFechaInicio(LocalDate.parse(request.getParameter("fechaInicioNuevo"), dtFormat));
			t.setJuego(j);
			t.setTipoTorneo(tt);
			Inscripcion iNuevo = new Inscripcion();
			iNuevo.setFechaInscripcion(LocalDate.parse(request.getParameter("fechaInscNuevo"), dtFormat));
			iNuevo.setJugador(jug);
			iNuevo.setTorneo(t);
			di.create(iNuevo);
			doGet(request, response);
		}
		if  (	request.getParameter("juegoActualizar") != null
				&& request.getParameter("tipoTorneoActualizar") != null
				&& request.getParameter("fechaInicioActualizar") != null 
				&& request.getParameter("fechaInscActualizar") != null
				&& request.getParameter("jugadorActualizar") != null){
			Torneo t = new Torneo();
			DataJuego dj = new DataJuego();
			DataJugador djug = new DataJugador();
			DataTipoTorneo dtt = new DataTipoTorneo();
			DataInscripcion di = new DataInscripcion();
			Jugador jug = djug.search(request.getParameter("jugadorActualizar"));
			Juego j = dj.search(request.getParameter("juegoActualizar"));
			TipoTorneo tt = dtt.search(request.getParameter("tipoTorneoActualizar"));
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			t.setFechaInicio(LocalDate.parse(request.getParameter("fechaInicioActualizar"), dtFormat));
			t.setJuego(j);
			t.setTipoTorneo(tt);
			Inscripcion iNuevo = new Inscripcion();
			iNuevo.setFechaInscripcion(LocalDate.parse(request.getParameter("fechaInscActualizar"), dtFormat));
			iNuevo.setJugador(jug);
			iNuevo.setTorneo(t);
			di.update(iNuevo);
			doGet(request, response);
		}
		if(		request.getParameter("juegoEliminar") != null
				&& request.getParameter("tipoTorneoEliminar") != null
				&& request.getParameter("fechaInicioEliminar") != null
				&& request.getParameter("jugadorEliminar") != null){
			Torneo t = new Torneo();
			DataJuego dj = new DataJuego();
			DataJugador djug = new DataJugador();
			DataTipoTorneo dtt = new DataTipoTorneo();
			DataInscripcion di = new DataInscripcion();
			Jugador jug = djug.search(request.getParameter("jugadorEliminar"));
			Juego j = dj.search(request.getParameter("juegoEliminar"));
			TipoTorneo tt = dtt.search(request.getParameter("tipoTorneoEliminar"));
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaInicioDate = LocalDate.parse(request.getParameter("fechaInicioEliminar"), dtFormat);
			t.setJuego(j);
			t.setTipoTorneo(tt);
			t.setFechaInicio(fechaInicioDate);
			di.delete(t, jug);
			doGet(request, response);
		}
	}

}
