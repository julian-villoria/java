package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.DataJuego;
import Datos.DataJugador;
import Datos.DataPais;
import Entidades.Encrypt;
import Entidades.Juego;
import Entidades.Pais;
import Negocio.CrudTorneo;

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
		LinkedList<Juego> data = new LinkedList<Juego>(); 
		data = DataJuego.list();
		request.setAttribute("data", data);
		getServletContext().getRequestDispatcher("/jsp/Jugador.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(		request.getParameter("usuarioNuevo") != null
				&& request.getParameter("nombreNuevo") != null
				&& request.getParameter("apellidoNuevo") != null 
				&& request.getParameter("paisFinNuevo") != null
				&& request.getParameter("contraseñaNuevo") != null) {
			Pais p = new Pais();
			String usuario = request.getParameter("usuarioNuevo");
			String nombre = request.getParameter("nombreNuevo");
			String apellido = request.getParameter("apellidoNuevo");
			String pais = request.getParameter("paisNuevo");
			String contraseña = Encrypt.convertirSHA256(request.getParameter("contraseñaNuevo")); 
			p.setNombre(pais);
			p = DataPais.buscar(p);
			DataJugador.create(usuario, contraseña , nombre, apellido, p.getId());
			doGet(request, response);
		}
		if  (	request.getParameter("usuarioActualizar") != null
				&& request.getParameter("nombreActualizar") != null
				&& request.getParameter("apellidoActualizar") != null 
				&& request.getParameter("paisFinActualizar") != null
				&& request.getParameter("contraseñaActualizar") != null
				&& request.getParameter("idActualizar") != null){
			Pais p = new Pais();
			int idJug = Integer.parseInt(request.getParameter("idActualizar"));
			String usuario = request.getParameter("usuarioNuevo");
			String nombre = request.getParameter("nombreNuevo");
			String apellido = request.getParameter("apellidoNuevo");
			String pais = request.getParameter("paisNuevo");
			String contraseña = Encrypt.convertirSHA256(request.getParameter("contraseñaNuevo")); 
			p.setNombre(pais);
			p = DataPais.buscar(p);
			DataJugador.update(idJug, usuario, contraseña, nombre, apellido, p);
			doGet(request, response);
		}
		if(	request.getParameter("usuarioEliminar") != null){
			String usuario = request.getParameter("usuarioEliminar");
			DataJugador.delete(usuario);
			doGet(request, response);
		}
	}

}
