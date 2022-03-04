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

import Datos.DataJuego;
import Datos.DataTipoTorneo;
import Datos.DataTorneo;
import Entidades.Juego;
import Entidades.Jugador;
import Entidades.TipoTorneo;
import Entidades.Torneo;

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
		HttpSession session = request.getSession(true);
		Jugador jugador = (Jugador) session.getAttribute("jugador");
		if(jugador == null) {
			getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
		}else {
			if(jugador.getId() != 0 && jugador.getAcceso().equals("Administrador")) {
				DataTorneo dt = new DataTorneo();
				DataJuego dj = new DataJuego();
				DataTipoTorneo dtt = new DataTipoTorneo();
				request.setAttribute("Torneo", dt.list());
				request.setAttribute("Juego", dj.list());
				request.setAttribute("TipoTorneo", dtt.list());
				getServletContext().getRequestDispatcher("/jsp/Torneo.jsp").forward(request, response);
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
				&& request.getParameter("fechaFinNuevo") != null
				&& request.getParameter("intentosNuevo") != null
				&& request.getParameter("cupoNuevo") != null
				&& request.getParameter("ganadorNuevo") != null
				&& request.getParameter("montoNuevo") != null) {
			DataTipoTorneo dtt = new DataTipoTorneo();
			DataJuego dj = new DataJuego();
			DataTorneo dt = new DataTorneo();
			Juego j = dj.search(request.getParameter("juegoNuevo"));
			TipoTorneo tt = dtt.search(request.getParameter("tipoTorneoNuevo"));
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaInicioDate = LocalDate.parse(request.getParameter("fechaInicioNuevo"), dtFormat);
			LocalDate fechaFinDate = LocalDate.parse(request.getParameter("fechaFinNuevo"), dtFormat);
			Torneo tNuevo = new Torneo();
			tNuevo.setJuego(j);
			tNuevo.setTipoTorneo(tt);
			tNuevo.setFechaInicio(fechaInicioDate);
			tNuevo.setFechaFin(fechaFinDate);
			tNuevo.setIntentos(Integer.parseInt(request.getParameter("intentosNuevo")));
			tNuevo.setCupo(Integer.parseInt(request.getParameter("cupoNuevo")));
			tNuevo.setGanador(request.getParameter("ganadorNuevo"));
			tNuevo.setMontoInsc(Integer.parseInt(request.getParameter("montoNuevo")));
			dt.create(tNuevo);
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
			String fechaInicio = request.getParameter("fechaInicioActualizar");
			String fechaFin = request.getParameter("fechaFinActualizar");
			DataTipoTorneo dtt = new DataTipoTorneo();
			DataJuego dj = new DataJuego();
			DataTorneo dt = new DataTorneo();
			Juego j = dj.search(request.getParameter("juegoActualizar"));
			TipoTorneo tt = dtt.search(request.getParameter("tipoTorneoActualizar"));
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Torneo tNuevo = new Torneo();
			tNuevo.setJuego(j);
			tNuevo.setTipoTorneo(tt);
			tNuevo.setFechaInicio(LocalDate.parse(fechaInicio, dtFormat));
			tNuevo.setFechaFin(LocalDate.parse(fechaFin, dtFormat));
			tNuevo.setIntentos(Integer.parseInt(request.getParameter("intentosActualizar")));
			tNuevo.setCupo(Integer.parseInt(request.getParameter("cupoActualizar")));
			tNuevo.setGanador(request.getParameter("ganadorActualizar"));
			tNuevo.setMontoInsc(Integer.parseInt(request.getParameter("montoActualizar")));
			dt.update(tNuevo);
			doGet(request, response);
		}
		if(		request.getParameter("juegoEliminar") != null
				&& request.getParameter("tipoTorneoEliminar") != null
				&& request.getParameter("fechaInicioEliminar") != null){
			int idJuego = Integer.parseInt(request.getParameter("juegoEliminar"));
			int idTipo = Integer.parseInt(request.getParameter("tipoTorneoEliminar"));
			String fechaInicio = request.getParameter("fechaInicioEliminar");
			DataTipoTorneo dtt = new DataTipoTorneo();
			DataJuego dj = new DataJuego();
			DataTorneo dt = new DataTorneo();
			int idJuegoBorrar = dj.search(idJuego).getId();
			int idTipoBorrar = dtt.search(idTipo).getId();
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaInicioDate = LocalDate.parse(fechaInicio, dtFormat);
			dt.delete(idJuegoBorrar, idTipoBorrar, fechaInicioDate);
			doGet(request, response);
		}
	}

}
