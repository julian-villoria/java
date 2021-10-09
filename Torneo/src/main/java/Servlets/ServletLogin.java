package Servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataJugador;
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
		if( session.getAttribute("jugador") == null ) {
			getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
		}else {
			response.getWriter().append("<h1>Ya estás logueado</h1>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Login l = new Login();
		Jugador j = new Jugador();
		String usuario = request.getParameter("usuario"); 
		String contraseña = request.getParameter("contraseña");
		j = l.validate(usuario, contraseña);
		HttpSession session = request.getSession(true);
		session.setAttribute("jugador", j);
		response.sendRedirect("ServletHome");
	}

}
