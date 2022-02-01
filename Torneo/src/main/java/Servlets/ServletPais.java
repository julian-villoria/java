package Servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataPais;
import Entidades.Jugador;
import Entidades.Pais;

/**
 * Servlet implementation class ServletPais
 */
@WebServlet("/ServletPais")
public class ServletPais extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPais() {
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
				LinkedList<Pais> data = new LinkedList<Pais>(); 
				data = DataPais.list();
				request.setAttribute("data", data);
				getServletContext().getRequestDispatcher("/jsp/Pais.jsp").forward(request, response);
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
		//NUEVO
		if (request.getParameter("nuevoPais") != null) {
			Pais p = new Pais();
			p.setNombre(request.getParameter("nuevoPais"));
			DataPais.nuevo(p);
			doGet(request, response);
		}
		//ACTUALIZAR
		if  (request.getParameter("idActualizar") != null && 
				 request.getParameter("denominacionActualizar") != null) {
				Integer idActualizar = Integer.parseInt(request.getParameter("idActualizar"));
				String paisActualizar = request.getParameter("paisActualizar");
				DataPais.update(idActualizar, paisActualizar);
				doGet(request, response);
		}
		//ELIMINAR
		if (request.getParameter("eliminarPais") != null) {
			Pais p = new Pais();
			p.setId(Integer.parseInt(request.getParameter("eliminarPais")));
			DataPais.borrar(p);
			doGet(request, response);
		}
	}

}
