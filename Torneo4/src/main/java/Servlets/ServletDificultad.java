package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataDificultad;
import Datos.DataJuego;
import Entidades.Dificultad;
import Entidades.Juego;
import Entidades.Jugador;

/**
 * Servlet implementation class ServletDificultad
 */
@WebServlet("/ServletDificultad")
public class ServletDificultad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDificultad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataDificultad dd = new DataDificultad(); 
		LinkedList<Dificultad> data = new LinkedList<Dificultad>(); 
		HttpSession session = request.getSession(true);
		Jugador jugador = (Jugador) session.getAttribute("jugador");
		if(jugador == null) {
			getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
		}else {
			if(jugador.getId() != 0) {
				data = dd.list();
				request.setAttribute("data", data);
				getServletContext().getRequestDispatcher("/jsp/Dificultad.jsp").forward(request, response);
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
		if (request.getParameter("nuevoNombre") != null && request.getParameter("nuevoRangoMinPuntaje") != null
			&& request.getParameter("nuevoRangoMinVictorias") != null && request.getParameter("nuevoJuego") != null
			&& request.getParameter("nuevoRangoMaxPuntaje") != null && request.getParameter("nuevoRangoMaxVictorias") != null) {
			DataDificultad dd = new DataDificultad();
			Dificultad d = new Dificultad();
			DataJuego dj = new DataJuego();
			d.setNombre(request.getParameter("nuevoNombre"));
			d.setRangoMinPuntajes(Integer.parseInt(request.getParameter("nuevoRangoMinPuntaje")));
			d.setRangoMaxPuntajes(Integer.parseInt(request.getParameter("nuevoRangoMaxPuntaje")));
			d.setRangoMaxVictorias(Integer.parseInt(request.getParameter("nuevoRangoMaxVictorias")));
			d.setRangoMaxVictorias(Integer.parseInt(request.getParameter("nuevoRangoMaxVictorias")));
			Juego j = new Juego();
			String denominacion = request.getParameter("nuevoJuego");
			j = dj.search(denominacion);
			d.setJuego(j);
			dd.nuevo(d);
			doGet(request, response);
		}
		//ACTUALIZAR
		if  (request.getParameter("actualizarNombre") != null && 
				 request.getParameter("actualizarRangoMinPuntaje") != null
				 && request.getParameter("actualizarRangoMaxPuntaje") != null
				 && request.getParameter("actualizarRangoMinVictorias") != null
				 && request.getParameter("actualizarRangoMaxVictorias") != null
				 && request.getParameter("actualizarJuego") != null) {
				DataDificultad dd = new DataDificultad();
				Dificultad d = new Dificultad();
				Juego j = new Juego();
				DataJuego dj = new DataJuego();
				String juegoActualizar = request.getParameter("actualizarJuego");
				j = dj.search(juegoActualizar);
				d.setNombre(request.getParameter("actualizarNombre"));
				d.setRangoMinPuntajes(Integer.parseInt(request.getParameter("actualizarRangoMinPuntaje")));
				d.setRangoMaxPuntajes(Integer.parseInt(request.getParameter("actualizarRangoMaxPuntaje")));
				d.setRangoMaxVictorias(Integer.parseInt(request.getParameter("actualizarRangoMinVictorias")));
				d.setRangoMaxVictorias(Integer.parseInt(request.getParameter("actualizarRangoMaxVictorias")));
				d.setJuego(j);
				dd.update(d);
				doGet(request, response);
		}
		//ELIMINAR
		if (request.getParameter("eliminarNombre") != null && request.getParameter("eliminarJuego") != null ) {
			DataDificultad dd = new DataDificultad();
			String borrarNombre = request.getParameter("eliminarNombre");
			String borrarJuego = request.getParameter("eliminarJuego");
			dd.borrar(borrarNombre, borrarJuego);
			doGet(request, response);
		}
	}

}
