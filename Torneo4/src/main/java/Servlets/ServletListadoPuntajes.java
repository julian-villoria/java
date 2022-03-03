package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataJugador;
import Entidades.Jugador;
import Entidades.Partida;

/**
 * Servlet implementation class ServletListadoPuntajes
 */
@WebServlet("/ServletListadoPuntajes")
public class ServletListadoPuntajes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletListadoPuntajes() {
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
                LinkedList<Partida> data = new LinkedList<Partida>(); 
                DataJugador dj = new DataJugador();
                data = dj.listaJugadoresTorneoActual();
                request.setAttribute("data", data);
                getServletContext().getRequestDispatcher("/jsp/ListadoPuntaje.jsp").forward(request, response);
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
