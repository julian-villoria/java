package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.DataDificultad;
import Datos.DataJuego;
import Datos.DataPais;
import Entidades.Dificultad;
import Entidades.Juego;
import Entidades.Pais;

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
		try {
			data = dd.list();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("data", data);
		getServletContext().getRequestDispatcher("/jsp/Dificultad.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if (request.getParameter("nuevoNombre") != null && request.getParameter("nuevoRangoPuntaje") != null
			&& request.getParameter("nuevoRangoVictorias") != null && request.getParameter("nuevoJuego") != null) {
			DataDificultad dd = new DataDificultad();
			Dificultad d = new Dificultad();
			d.setNombre(request.getParameter("nuevoNombre"));
			d.setRango_puntajes(Integer.parseInt(getInitParameter("nuevoRangoPuntaje")));
			d.setRango_victorias(Integer.parseInt(request.getParameter("nuevoRangoVictorias")));
			DataJuego dj = new DataJuego();
			Juego j = new Juego();
			String denominacion = request.getParameter("nuevoJuego");
			j = dj.search(denominacion);
			d.setJuego(j);
			dd.nuevo(d);
			doGet(request, response);
			

		}
		//ACTUALIZAR
		if  (request.getParameter("actualizarNombre") != null && 
				 request.getParameter("actualizarRangoPuntaje") != null
				 && request.getParameter("actualizarVictorias") != null
				 && request.getParameter("actualizarJuego") != null) {
				DataDificultad dd = new DataDificultad();
				Dificultad d = new Dificultad();
				DataJuego dj = new DataJuego();
				Juego j = new Juego();
				String juegoActualizar = request.getParameter("actualizarJuego");
				j = dj.search(juegoActualizar);
				d.setNombre(request.getParameter("actualizarNombre"));
				d.setRango_puntajes(Integer.parseInt(request.getParameter("actualizarRangoPuntaje")));
				d.setRango_victorias(Integer.parseInt(request.getParameter("actualizarRangoVictorias")));
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
