package Servlets;

import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Entidades.Jugador;
import Negocio.Login;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
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
				if(jugador.getAcceso().equals("Administrador")) {
				getServletContext().getRequestDispatcher("/jsp/HomeAdmin.jsp").forward(request, response);
				}
				if(jugador.getAcceso().equals("Jugador")) {
					getServletContext().getRequestDispatcher("/jsp/HomeJugador.jsp").forward(request, response);
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
		Jugador j = new Jugador();
		String usuario = request.getParameter("usuario"); 
		String contrase�a = request.getParameter("contrase�a");
		j = Login.validate(usuario, contrase�a);
		System.out.println(j.getId());
		if (j.getId() == 0) {
			response.sendRedirect("ServletLoginError");
		}
		else {
		session.setAttribute("jugador", j);
		response.sendRedirect("ServletHome");
		}
	}

}
