package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataInscripcion;
import Datos.DataJuego;
import Datos.DataTipoTorneo;
import Entidades.Inscripcion;
import Entidades.Juego;
import Entidades.Jugador;
import Entidades.TipoTorneo;
import Negocio.CrudInscripcion;

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
		LinkedList<Juego> dataJuego = new LinkedList<Juego>();
		LinkedList<TipoTorneo> dataTipoTorneo = new LinkedList<TipoTorneo>();
		LinkedList<Inscripcion> dataInsc = new LinkedList<Inscripcion>();
		dataInsc = DataInscripcion.list();
		dataJuego = DataJuego.list();
		dataTipoTorneo = DataTipoTorneo.list();
		request.setAttribute("Inscripcion", dataInsc);
		request.setAttribute("Juego", dataJuego);
		request.setAttribute("TipoTorneo", dataTipoTorneo);
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
			String denominacionJuego = request.getParameter("juegoNuevo");
			String denominacionTipoTorneo = request.getParameter("tipoTorneoNuevo");
			String fechaInicio = request.getParameter("fechaInicioNuevo");
			String fechaInsc = request.getParameter("fechaInscNuevo");
			String usuario = request.getParameter("jugadorNuevo");
			CrudInscripcion.create(denominacionJuego, denominacionTipoTorneo, fechaInicio, fechaInsc, usuario);
			doGet(request, response);
		}
		if  (	request.getParameter("juegoActualizar") != null
				&& request.getParameter("tipoTorneoActualizar") != null
				&& request.getParameter("fechaInicioActualizar") != null 
				&& request.getParameter("fechaInscActualizar") != null
				&& request.getParameter("jugadorActualizar") != null){
			String denominacionJuego = request.getParameter("juegoActualizar");
			String denominacionTipoTorneo = request.getParameter("tipoTorneoActualizar");
			String fechaInicio = request.getParameter("fechaInicioActualizar");
			String fechaInsc = request.getParameter("fechaInscActualizar");
			String usuario = request.getParameter("jugadorActualizar");
			CrudInscripcion.update(denominacionJuego, denominacionTipoTorneo, fechaInicio, fechaInsc, usuario);
			doGet(request, response);
		}
		if(		request.getParameter("juegoEliminar") != null
				&& request.getParameter("tipoTorneoEliminar") != null
				&& request.getParameter("fechaInicioEliminar") != null
				&& request.getParameter("jugadorEliminar") != null){
			String denominacionJuego = request.getParameter("juegoEliminar");
			String denominacionTipoTorneo = request.getParameter("tipoTorneoEliminar");
			String fechaInicio = request.getParameter("fechaInicioEliminar");
			String usuario = request.getParameter("jugadorEliminar");
			CrudInscripcion.delete(denominacionJuego, denominacionTipoTorneo, fechaInicio, usuario);
			doGet(request, response);
		}
	}

}
