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
import Datos.DataPais;
import Entidades.Encrypt;
import Entidades.Jugador;
import Entidades.Pais;

/**
 * Servlet implementation class ServletJugador
 */
@WebServlet("/ServletJugador")
public class ServletJugador extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletJugador() {
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
			getServletContext().getRequestDispatcher("ServletLogin").forward(request, response);
		}else {
			if(jugador.getId() != 0 && jugador.getAcceso().equals("Administrador")) {
				LinkedList<Jugador> data = new LinkedList<Jugador>(); 
				DataJugador dj = new DataJugador();
				data = dj.list();
				request.setAttribute("data", data);
				getServletContext().getRequestDispatcher("/jsp/Jugador.jsp").forward(request, response);
			}else {
				getServletContext().getRequestDispatcher("ServletLogin").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(		request.getParameter("usuarioNuevo") != null
				&& request.getParameter("nombreNuevo") != null
				&& request.getParameter("apellidoNuevo") != null 
				&& request.getParameter("paisNuevo") != null
				&& request.getParameter("contraseñaNuevo") != null
				&& request.getParameter("accesoNuevo") != null) {
			Pais p = new Pais();
			Jugador jNuevo = new Jugador();
			DataJugador dj = new DataJugador();
			DataPais dp = new DataPais();
			String pais = request.getParameter("paisNuevo");
			p = dp.search(pais);
			jNuevo.setUsuario(request.getParameter("usuarioNuevo"));
			jNuevo.setContraseña(Encrypt.convertirSHA256(request.getParameter("contraseñaNuevo")));
			jNuevo.setNombre(request.getParameter("nombreNuevo"));
			jNuevo.setApellido(request.getParameter("apellidoNuevo"));
			jNuevo.setAcceso(request.getParameter("accesoNuevo"));
			jNuevo.setPais(p);
			dj.create(jNuevo);
			doGet(request, response);
		}
		if  (	request.getParameter("usuarioActualizar") != null
				&& request.getParameter("nombreActualizar") != null
				&& request.getParameter("apellidoActualizar") != null 
				&& request.getParameter("paisActualizar") != null
				&& request.getParameter("contraseñaActualizar") != null
				&& request.getParameter("idActualizar") != null
				&& request.getParameter("accesoActualizar") != null
				&& request.getParameter("reportesActualizar") != null){
			Pais p = new Pais();
			DataJugador dj = new DataJugador();
			DataPais dp = new DataPais();
			String pais = request.getParameter("paisActualizar");
			int cantReportes = Integer.parseInt(request.getParameter("reportesActualizar"));
			p.setNombre(pais);
			p = dp.buscar(p);
			Jugador jNuevo = new Jugador();
			jNuevo.setId(Integer.parseInt(request.getParameter("idActualizar")));
			jNuevo.setUsuario(request.getParameter("usuarioActualizar"));
			jNuevo.setContraseña(Encrypt.convertirSHA256(request.getParameter("contraseñaActualizar")));
			jNuevo.setNombre(request.getParameter("nombreActualizar"));
			jNuevo.setApellido(request.getParameter("apellidoActualizar"));
			jNuevo.setAcceso(request.getParameter("accesoActualizar"));
			jNuevo.setPais(p);
			jNuevo.setReportes(cantReportes);
			dj.update(jNuevo);
			doGet(request, response);
		}
		if(	request.getParameter("usuarioEliminar") != null){
			DataJugador dj = new DataJugador();
			String usuario = request.getParameter("usuarioEliminar");
			dj.delete(usuario);
			doGet(request, response);
		}
	}

}
