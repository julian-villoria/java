package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataInscripcion;
import Datos.DataTorneo;
import Entidades.Inscripcion;
import Entidades.Juego;
import Entidades.Jugador;
import Entidades.TipoTorneo;
import Entidades.Torneo;

/**
 * Servlet implementation class ServletInscripcion
 */
@WebServlet("/ServletInscripcion")
public class ServletInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscripcion() {
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
			if(jugador.getId() != 0 && jugador.getAcceso().equals("Jugador")) {
		LinkedList<Torneo> dataTorneo = new LinkedList<Torneo>(); 
		DataTorneo dt = new DataTorneo();
		int cantInsc = 0;
		dataTorneo = dt.proximos();
		request.setAttribute("cantInsc", cantInsc);
		request.setAttribute("Torneo", dataTorneo);
		getServletContext().getRequestDispatcher("/jsp/Inscripcion.jsp").forward(request, response);
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
		HttpSession session = request.getSession(true);
		Torneo t = new Torneo();
		Jugador j = new Jugador();
		Inscripcion i = new Inscripcion();
		DataTorneo dt = new DataTorneo();
		DataInscripcion di = new DataInscripcion();
		j = (Jugador) session.getAttribute("jugador");
		int success = 0;
		int idJuego = Integer.parseInt(request.getParameter("idJuego"));
		int idTipo = Integer.parseInt(request.getParameter("idTipo"));
		String fechaInicioString = request.getParameter("fechaInicio");
        LocalDate fechaInicio = LocalDate.parse(fechaInicioString);
        t = dt.search(idJuego, idTipo, fechaInicio);
        i.setTorneo(t);
        i.setJ(j);
        i.setFechaInscripcion(LocalDate.now());
        success = di.create(i);
		LinkedList<Torneo> dataTorneo = new LinkedList<Torneo>(); 
		LinkedList<Juego> dataJuego = new LinkedList<Juego>();
		LinkedList<TipoTorneo> dataTipoTorneo = new LinkedList<TipoTorneo>();
		int cantInsc = 0;
		dataTorneo = dt.proximos();
		request.setAttribute("cantInsc", cantInsc);
		request.setAttribute("Torneo", dataTorneo);
		request.setAttribute("Juego", dataJuego);
		request.setAttribute("TipoTorneo", dataTipoTorneo);
		request.setAttribute("success", success);
		getServletContext().getRequestDispatcher("/jsp/InscripcionSuccess.jsp").forward(request, response);
	}
}
