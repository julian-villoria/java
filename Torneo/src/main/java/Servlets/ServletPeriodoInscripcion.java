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
		LinkedList<PeriodoInscripcion> data = new LinkedList<PeriodoInscripcion>(); 
		data = DataPeriodoInscripcion.list();
		request.setAttribute("data", data);
		getServletContext().getRequestDispatcher("/jsp/PeriodoInscripcion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("fechaDesdeNuevo") != null && request.getParameter("fechaHastaNuevo") != null) {
			String fechaDesde = request.getParameter("fechaDesdeNuevo");
			String fechaHasta = request.getParameter("fechaHastaNuevo");
			DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate fechaDesdeDate = LocalDate.parse(fechaDesde, dtFormat);
			LocalDate fechaHastaDate = LocalDate.parse(fechaHasta, dtFormat);
			DataPeriodoInscripcion.create(fechaDesdeDate, fechaHastaDate);
			doGet(request, response);
		}
		if(		 request.getParameter("idActualizar") != null && 
				 request.getParameter("fechaDesdeActualizar") != null &&
				 request.getParameter("fechaHastaActualizar") != null
				){
				DateTimeFormatter dtFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				Integer idActualizar = Integer.parseInt(request.getParameter("idActualizar"));
				LocalDate fechaDesdeDate = LocalDate.parse(request.getParameter("fechaDesdeActualizar"), dtFormat);
				LocalDate fechaHastaDate = LocalDate.parse(request.getParameter("fechaHastaActualizar"), dtFormat);
				DataPeriodoInscripcion.update(idActualizar, fechaDesdeDate, fechaHastaDate);
				doGet(request, response);
			}
		if(request.getParameter("idEliminar") != null) {
			Integer idEliminar = Integer.parseInt(request.getParameter("idEliminar"));
			DataPeriodoInscripcion.delete(idEliminar);
			doGet(request, response);
		}
		
	}
}
