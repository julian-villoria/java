package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataJuego;
import Datos.DataTipoTorneo;
import Entidades.Juego;
import Entidades.Jugador;

/**
 * Servlet implementation class ServletJuego
 */
@WebServlet("/ServletJuego")
public class ServletJuego extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletJuego() {
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
		}
		else {
			if(jugador.getId() != 0) {
			LinkedList<Juego> data = new LinkedList<Juego>(); 
			DataJuego dj = new DataJuego();
			data = dj.list();
			request.setAttribute("data", data);
			getServletContext().getRequestDispatcher("/jsp/Juego.jsp").forward(request, response);
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
		//Agregar
		if(request.getParameter("nuevaDenominacion") != null) {
			DataJuego dj = new DataJuego();
			dj.create(request.getParameter("nuevaDenominacion"));
			doGet(request, response);
		}
		//Eliminar
		if(request.getParameter("tipoEliminar") != null) {
			DataTipoTorneo dtt = new DataTipoTorneo();
			String tipoEliminar = request.getParameter("tipoEliminar");
			dtt.delete(tipoEliminar);
			doGet(request, response);
		}
		//Actualizar
		if  (request.getParameter("idActualizar") != null && 
				 request.getParameter("denominacionActualizar") != null) {
				Integer idActualizar = Integer.parseInt(request.getParameter("idActualizar"));
				String denominacionActualizar = request.getParameter("denominacionActualizar");
				DataTipoTorneo dtt = new DataTipoTorneo();
				dtt.update(idActualizar, denominacionActualizar);
				doGet(request, response);
			}
		doGet(request, response);
		}

}
