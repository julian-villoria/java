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
		LinkedList<Torneo> dataTorneo = new LinkedList<Torneo>(); 
		LinkedList<Juego> dataJuego = new LinkedList<Juego>();
		LinkedList<TipoTorneo> dataTipoTorneo = new LinkedList<TipoTorneo>();
		int cantInsc = 0;
		dataTorneo = DataTorneo.proximos();
		cantInsc = DataInscripcion.contador();
		request.setAttribute("cantInsc", cantInsc);
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
		HttpSession session = request.getSession(true);
		Torneo t = new Torneo();
		Jugador j = new Jugador();
		j = (Jugador) session.getAttribute("jugador");
		int idJuego = Integer.parseInt(request.getParameter("idJuego"));
		int idTipo = Integer.parseInt(request.getParameter("idTipo"));
		String fechaInicioString = request.getParameter("fechaInicio");
        LocalDate fechaInicio = LocalDate.parse(fechaInicioString);
        t = DataTorneo.search(idJuego, idTipo, fechaInicio);
		DataInscripcion.create(t, j, LocalDate.now());
		doGet(request, response);
	}
}
