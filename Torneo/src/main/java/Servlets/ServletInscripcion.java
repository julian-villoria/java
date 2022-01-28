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
		DataTorneo dt = new DataTorneo(); 
		LinkedList<Torneo> dataTorneo = new LinkedList<Torneo>(); 
		LinkedList<Juego> dataJuego = new LinkedList<Juego>();
		LinkedList<TipoTorneo> dataTipoTorneo = new LinkedList<TipoTorneo>();
		dataTorneo = dt.proximos();
		request.setAttribute("Torneo", dataTorneo);
		request.setAttribute("Juego", dataJuego);
		request.setAttribute("TipoTorneo", dataTipoTorneo);
		getServletContext().getRequestDispatcher("/jsp/Inscripcion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int index = Integer.parseInt(request.getParameter("Inscribirse"));
		HttpSession session = request.getSession(true);
		Jugador j = (Jugador) session.getAttribute("jugador");
		DataTorneo dt = new DataTorneo(); 
		LinkedList<Torneo> torneos = dt.proximos(); 
		DataInscripcion di = new DataInscripcion();
		Torneo t = torneos.get(index);
		di.create(t, j);
		doGet(request, response);
	}

}
