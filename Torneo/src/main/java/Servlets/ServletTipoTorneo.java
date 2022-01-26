package Servlets;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.DataTipoTorneo;
import Entidades.TipoTorneo;

/**
 * Servlet implementation class ServletTipoTorneo
 */
@WebServlet("/ServletTipoTorneo")
public class ServletTipoTorneo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTipoTorneo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataTipoTorneo dtt = new DataTipoTorneo(); 
		LinkedList<TipoTorneo> data = new LinkedList<TipoTorneo>(); 
		data = dtt.list();
		request.setAttribute("data", data);
		getServletContext().getRequestDispatcher("/jsp/TipoTorneo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//Agregar
		if(request.getParameter("nuevaDenominacion") != null) {
			DataTipoTorneo dtt = new DataTipoTorneo(); 
			//int id = Integer.parseInt(request.getParameter("nuevoId"));
			TipoTorneo t = new TipoTorneo();
			t.setDenominacion(request.getParameter("nuevaDenominacion"));
			dtt.create(t);
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
				DataTipoTorneo dtt = new DataTipoTorneo(); 
				Integer idActualizar = Integer.parseInt(request.getParameter("idActualizar"));
				String denominacionActualizar = request.getParameter("denominacionActualizar");
				dtt.update(idActualizar, denominacionActualizar);
				doGet(request, response);
			}
	}

}
