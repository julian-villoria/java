package Servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Jugador;
import Negocio.CrudTorneo;

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
		request.setAttribute("Torneo", CrudTorneo.listaTorneo());
		request.setAttribute("Juego", CrudTorneo.listaJuego());
		request.setAttribute("TipoTorneo", CrudTorneo.listaTipoTorneo());
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
			String denominacionJuego = request.getParameter("juegoNuevo");
			String denominacionTipoTorneo = request.getParameter("tipoTorneoNuevo");
			String fechaInicio = request.getParameter("fechaInicioNuevo");
			String fechaFin = request.getParameter("fechaFinNuevo");
			int intentos = Integer.parseInt(request.getParameter("intentosNuevo"));
			int cupo = Integer.parseInt(request.getParameter("cupoNuevo"));
			String ganador = request.getParameter("ganadorNuevo");
			float montoInsc = Integer.parseInt(request.getParameter("montoNuevo"));
			CrudTorneo.create(denominacionJuego, denominacionTipoTorneo, fechaInicio, fechaFin, intentos, cupo, ganador, montoInsc);
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
			String denominacionJuego = request.getParameter("juegoActualizar");
			String denominacionTipoTorneo = request.getParameter("tipoTorneoActualizar");
			String fechaInicio = request.getParameter("fechaInicioActualizar");
			String fechaFin = request.getParameter("fechaFinActualizar");
			int intentos = Integer.parseInt(request.getParameter("intentosActualizar"));
			int cupo = Integer.parseInt(request.getParameter("cupoActualizar"));
			String ganador = request.getParameter("ganadorActualizar");
			float montoInsc = Integer.parseInt(request.getParameter("montoActualizar"));
			CrudTorneo.update(denominacionJuego, denominacionTipoTorneo, fechaInicio, fechaFin, intentos, cupo, ganador, montoInsc);
			doGet(request, response);
		}
		if(		request.getParameter("juegoEliminar") != null
				&& request.getParameter("tipoTorneoEliminar") != null
				&& request.getParameter("fechaInicioEliminar") != null){
			int idJuego = Integer.parseInt(request.getParameter("juegoEliminar"));
			int idTipo = Integer.parseInt(request.getParameter("tipoTorneoEliminar"));
			String fechaInicio = request.getParameter("fechaInicioEliminar");
			CrudTorneo.delete(idJuego, idTipo, fechaInicio);
			doGet(request, response);
		}
	}

}
