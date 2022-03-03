package Servlets;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Datos.DataJugador;
import Datos.EmailData;
import Entidades.Jugador;

/**
 * Servlet implementation class ServletReportes
 */
@WebServlet("/ServletReportes")
public class ServletReportes extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletReportes() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    private String host = "smtp.gmail.com";
    private String port = "587";
    private String user = "reportesappjava@gmail.com";
    private String pass = "Milanesas123";
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        HttpSession session = request.getSession(true);
        Jugador jugador = (Jugador) session.getAttribute("jugador");
        if(jugador == null) {
            getServletContext().getRequestDispatcher("/jsp/Login.jsp").forward(request, response);
        }else {
            if(jugador.getId() != 0 && jugador.getAcceso().equals("Jugador")) {
                getServletContext().getRequestDispatcher("/jsp/Reportes.jsp").forward(request, response);
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
        Jugador jugador = (Jugador) session.getAttribute("jugador");
        DataJugador dj = new DataJugador();
        Jugador j = dj.search(request.getParameter("usuarioReportado"));
        dj.updateReportes(j);
        String destinatario = "reportesappjava@gmail.com";
        String subject = "Reporte al jugador " + j.getUsuario();
        String content = request.getParameter("content");
        String c = request.getParameter("c");
 
        try {
            EmailData.sendEmail(host, port, user, pass, destinatario, subject, jugador.getUsuario(), content, c);
        } catch (Exception ex) {
            ex.printStackTrace();
        } 
        getServletContext().getRequestDispatcher("/jsp/ReporteExitoso.jsp").forward(request, response);
    }

}