package Servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import Entidades.PeriodoInscripcion;
import Datos.DataPeriodoInscripcion;


/**
 * Servlet implementation class ServletPeriodoInscripcion
 */
@WebServlet("/ServletPeriodoInscripcion")
public class ServletPeriodoInscripcion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPeriodoInscripcion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		DataPeriodoInscripcion dip = new DataPeriodoInscripcion(); 
		LinkedList<PeriodoInscripcion> data = new LinkedList<PeriodoInscripcion>(); 
		data = dip.list();
		request.setAttribute("data", data);
		/*int id = Integer.parseInt(request.getParameter("id"));
		if(id == 0) {
			System.out.println("testing id");
		}
		*/
		getServletContext().getRequestDispatcher("/jsp/PeriodoInscripcion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("fechaDesdeNuevo") != null && request.getParameter("fechaHastaNuevo") != null) {
			DataPeriodoInscripcion dip = new DataPeriodoInscripcion(); 
			String fechaDesde = request.getParameter("fechaDesdeNuevo");
			String fechaHasta = request.getParameter("fechaHastaNuevo");
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaDesdeDate = LocalDate.parse(fechaDesde, dtFormat);
			LocalDate fechaHastaDate = LocalDate.parse(fechaHasta, dtFormat);
			dip.create(fechaDesdeDate, fechaHastaDate);
			doGet(request, response);
		}
		if  (request.getParameter("idActualizar") != null && 
				 request.getParameter("fechaDesdeActualizar") != null &&
				 request.getParameter("fechaHastaActualizar") != null
				){
				DataPeriodoInscripcion dip = new DataPeriodoInscripcion(); 
				DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				Integer idActualizar = Integer.parseInt(request.getParameter("idActualizar"));
				LocalDate fechaDesdeDate = LocalDate.parse(request.getParameter("fechaDesdeActualizar"), dtFormat);
				LocalDate fechaHastaDate = LocalDate.parse(request.getParameter("fechaHastaActualizar"), dtFormat);
				dip.update(idActualizar, fechaDesdeDate, fechaHastaDate);
				doGet(request, response);
			}
		if(request.getParameter("idEliminar") != null) {
			DataPeriodoInscripcion dip = new DataPeriodoInscripcion(); 
			Integer idEliminar = Integer.parseInt(request.getParameter("idEliminar"));
			dip.delete(idEliminar);
			doGet(request, response);
		}
		
	}
}
