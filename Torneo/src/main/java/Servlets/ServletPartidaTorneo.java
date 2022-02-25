package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Jugador;
import Entidades.Partida;
import Entidades.Torneo;
import Datos.*;

/**
 * Servlet implementation class ServletPartidaTorneo
 */
@WebServlet("/ServletPartidaTorneo")
public class ServletPartidaTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPartidaTorneo() {
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
				LinkedList<Partida> partidas = new LinkedList<Partida>();
				Torneo t = new Torneo();
				DataTorneo dt = new DataTorneo();
				DataPartida dp = new DataPartida();
				t = dt.getTorneoJugadorActual(jugador);
				partidas = dp.listPartidasTorneo(t);
				request.setAttribute("partidas", partidas);
				System.out.println(t);
				System.out.println(partidas);
				getServletContext().getRequestDispatcher("/jsp/Puntajes.jsp").forward(request, response);
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
		doGet(request, response);
	}

}
