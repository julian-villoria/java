package Servlets;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Datos.DataCliente;
import Entidades.Cliente;

/**
 * Servlet implementation class ServletLista
 */
@WebServlet("/ServletLista")
public class ServletLista extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLista() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter()
		.append(""
				+ "<!DOCTYPE html>\r\n"
				+ "<html lang='es'>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"ISO-8859-1\">\r\n"
				+ "<title>Insert title here</title>\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css\" integrity=\"sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh\" crossorigin=\"anonymous\">"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<table class=\"table table-dark\">\r\n"
				+ "	<thead>\r\n"
				+ "	<tr>\r\n"
				+ "	<th>id</th>\r\n"
				+ "	<th>nombre</th>\r\n"
				+ "	<th>direccion</th>\r\n"
				+ "	<th>saldo</th>\r\n"
				+ "	</tr>\r\n"
				+ "	</thead>\r\n"
				+ "	<tbody>\r\n"
		);
		int id = Integer.parseInt(request.getParameter("id"));
		Cliente x1 = DataCliente.getCliente(id);
		response.getWriter().append(x1.toString());
		ArrayList<Cliente> losCli = DataCliente.getAll();
		for(Cliente x: losCli) {
			response.getWriter()
			.append("   <tr>\r\n"
					+ "	<td>"+x.getId()+"</td>\r\n"
					+ "	<td>"+x.getNombre()+"</td>\r\n"
					+ "	<td>"+x.getDireccion()+"</td>\r\n"
					+ "	<td>"+x.getSaldo()+"</td>\r\n"
					+ "	</tr>\r\n"
		   );
		}
		response.getWriter()
		.append("	</tbody>\r\n"
				+ "	</table>"
				+ "</body>\r\n"
				+ "</html>"
		);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
