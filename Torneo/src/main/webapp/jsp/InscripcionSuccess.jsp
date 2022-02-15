<!DOCTYPE>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="Entidades.Torneo" %>
<%@ page import="Entidades.Jugador" %>
<%@ page import="Datos.DataInscripcion" %>
<head>
	<style>
	.fas.fa-check-circle.fa-2x{
		color: green;
	}
	.fas.fa-times-circle.fa-2x{
		color: red;
	}
	</style>
	<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
	<%int success = (Integer) request.getAttribute("success");%>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Periodo Inscripción</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<script src="https://kit.fontawesome.com/e79a668882.js" crossorigin="anonymous"></script>
	<% LinkedList<Torneo> dataTorneo = (LinkedList<Torneo>)request.getAttribute("Torneo"); %>
</head>
<body>
<!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-lg-5">
                <a class="navbar-brand" href="ServletHome">Torneo</a>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Home</a></li>
	                    <%if(jugador == null){%>
	                    	<li class="nav-item">
	                    		<a class="nav-link" href="ServletLogin">
	                        		<%="Login"%>
	                    		</a>
	                    	</li>
	                    <%}else{%>
						      <li class="nav-item dropdown">
						        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						          <%= jugador.getUsuario() %>
						        </a>
						        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						          <a class="dropdown-item" href="ServletEditarPerfil">Editar Perfil</a>
						          <a class="dropdown-item" href="ServletCerrarSesion">Cerrar Sesion</a>
						        </div>
						      </li>
	                    <%} %>
                    </ul>
                </div>
            </div>
        </nav>
	<h1 class="text-center my-3">Torneos Próximos</h1>
    <div class="container-fluid">
        <div class="row mt-5">
            <div class="col-md">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr class="table-primary">
                            <th scope="col">juego</th>
                            <th scope="col">Tipo de Torneo</th>
                            <th scope="col">Fecha Comienzo</th>
                            <th scope="col">Fecha Finalizacón</th>
                            <th scope="col">Cantidad de Intentos</th>
                        	<th scope="col">Cupo Inicial</th>
                        	<th scope="col">Ganador</th>
                        	<th scope="col">Monto Inscripción</th>
                        	<th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (!Objects.isNull(dataTorneo)) { %>
                            <% for(Torneo t : dataTorneo) { %>
                                <tr>
                                    <td>
                                        <%= t.getJuego().getDenominacion() %>
                                    </td>
                                    <td>
                                        <%= t.getTipoTorneo().getDenominacion() %>
                                    </td>
                                    <td>
                                        <%= t.getFechaInicio() %>
                                    </td>
                                    <td>
                                        <%= t.getFechaFin() %>
                                    </td>
                                    <td>
                                        <%= t.getIntentos() %>
                                    </td>
                                    <td>
                                        <%= t.getCupo() %>
                                    </td>
                                    <td>
                                        <%= t.getGanador() %>
                                    </td>
                                    <td>
                                        <%= t.getMontoInsc() %>
                                    </td>
                                    <td>
                                    	<form action="ServletInscripcion" method="post">
                                    		<button type="submit" name="Inscribirse" class="btn btn-success btn-block"
                                    		<% if(DataInscripcion.contador(jugador, t) == 0){ %>
                                    		 	>Inscribirse
                                    		 <%}else{ %>
                                    		 	disabled> Ya Inscripto
                                    		 <%} %>
                                    		 </button>
                                    		<input name="idJuego" type="hidden" value="<%= t.getJuego().getId() %>">
                                    		<input name="idTipo" type="hidden" value="<%= t.getTipoTorneo().getId() %>"> 
                                    		<input name="fechaInicio" type="hidden" value="<%= t.getFechaInicio() %>"> 
                                    	</form>
                                    </td>
                                </tr>
                            <% } %>
                        <% } %>
                    </tbody>
                </table>			
            </div>
        </div>
	</div>	

	<div class="modal show" tabindex="-1" role="dialog" id="myModal">
	  <div class="modal-dialog modal-dialog-centered" role="document">
	    <div class="modal-content">
		  <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<div class="container">
	      		<div class="row justify-content-center">
					<% if(success != 0){ %>
			        	<h5>Inscripción Realizada con Exito</h5> &nbsp; <i class="fas fa-check-circle fa-2x"></i>
			        <% }else{ %>
			        	<h5>Hubo un Error en la Inscripción</h5> &nbsp; <i class="fas fa-times-circle fa-2x"></i>
			        <% } %>
	        	</div>
	        </div>
	      </div>
	      <div class="modal-footer">
	        <a href="ServletInscripcion"><button type="button" class="btn btn-primary">Aceptar</button></a>
	      </div>
	    </div>
	  </div>
	</div>

</body>
<script>
$('#myModal').modal({backdrop:'static',keyboard:false, show:true});
</script>
</html>