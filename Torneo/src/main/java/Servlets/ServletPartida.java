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
		HttpSession session = request.getSession(true);
		Jugador jugador = (Jugador) session.getAttribute("jugador");
		if(jugador == null) {
			getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
		}else {
			if(jugador.getId() != 0) {
				DataTorneo dt = new DataTorneo();
				Torneo t = dt.getTorneoJugadorActual(jugador);
				if(t.getFechaInicio() != null) {
					getServletContext().getRequestDispatcher("/jsp/Partida.jsp").forward(request, response);
				}else{
					getServletContext().getRequestDispatcher("/ServletInscripcion").forward(request, response);
				}
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
		Jugador jugador = (Jugador) session.getAttribute("jugador");
		if(jugador == null) {
			getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
		}else {
			if(jugador.getId() != 0 && jugador.getAcceso().equals("Jugador")) {
				Torneo t = new Torneo();
				Juego juego = new Juego();
				DataTorneo dt = new DataTorneo();
				DataPartida dp = new DataPartida();
				t = dt.getTorneoJugadorActual(jugador);
				juego = t.getJuego();
				int puntos = Integer.parseInt(request.getParameter("puntos"));
				LocalDateTime fechaHora = LocalDateTime.now();
				int cont = dp.contador(t, jugador);
				if( cont == 0){
					dp.create(fechaHora, jugador, juego, puntos);
				}
				request.setAttribute("cantPartidasTorneo", cont);
				getServletContext().getRequestDispatcher("/jsp/PartidaEnviada.jsp").forward(request, response);
			}else {
				getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
			}
		}
	}

}
