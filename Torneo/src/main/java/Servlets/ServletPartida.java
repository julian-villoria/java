package Servlets;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataPartida;
import Datos.DataTorneo;
import Entidades.Juego;
import Entidades.Jugador;
import Entidades.Torneo;

/**
 * Servlet implementation class ServletPartida
 */
@WebServlet("/ServletPartida")
public class ServletPartida extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPartida() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/jsp/Partida.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = (HttpSession) request.getSession();
		Jugador jugador = (Jugador) session.getAttribute("jugador");
		Torneo t = new Torneo();
		Juego juego = new Juego();
		t = DataTorneo.getTorneoJugadorActual(jugador);
		juego = t.getJuego();
		System.out.println(t.getJuego().getDenominacion());
		int puntos = Integer.parseInt(request.getParameter("puntos"));
		LocalDateTime fechaHora = LocalDateTime.now();
		DataPartida.create(fechaHora, jugador, juego, puntos);
		getServletContext().getRequestDispatcher("/jsp/PartidaEnviada.jsp").forward(request, response);
	}

}
