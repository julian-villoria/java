<!DOCTYPE>
<html>
<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="Entidades.Torneo" %>
<%@ page import="Entidades.Jugador" %>
<%@ page import="Datos.DataInscripcion" %>
<%@ page import="Datos.DataTorneo" %>
<head>
	<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Periodo Inscripción</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<% LinkedList<Torneo> dataTorneo = (LinkedList<Torneo>)request.getAttribute("Torneo"); %>
	<% DataInscripcion di = new DataInscripcion(); %>
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
                        <% if (!dataTorneo.isEmpty()) { %>
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
                                    		<button type="submit" name="Inscribirse" class="btn 
                                    		<% if( di.contador(jugador, t) == 0){ %>
                                    		 	btn-success btn-block">Inscribirse
                                    		 <%}else{ %>
                                    		 	btn-secondary btn-block" disabled> Ya Inscripto
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
=======
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="Entidades.Torneo"%>
<%@ page import="Entidades.Jugador"%>
<%@ page import="Datos.DataInscripcion"%>
<%@ page import="Datos.DataTorneo"%>
<head>
<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Periodo Inscripción</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
	integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
	integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
	crossorigin="anonymous"></script>
<% LinkedList<Torneo> dataTorneo = (LinkedList<Torneo>)request.getAttribute("Torneo"); %>
<% DataInscripcion di = new DataInscripcion(); %>
<style type="text/css">
.hvr-grow {
	display: inline-block;
	vertical-align: middle;
	-webkit-transform: perspective(1px) translateZ(0);
	transform: perspective(1px) translateZ(0);
	box-shadow: 0 0 1px rgba(0, 0, 0, 0);
	-webkit-transition-duration: 0.3s;
	transition-duration: 0.3s;
	-webkit-transition-property: transform;
	transition-property: transform;
}

.hvr-grow:hover, .hvr-grow:focus, .hvr-grow:active {
	-webkit-transform: scale(1.3);
	transform: scale(1.3);
}

.footer {
	position: fixed;
	left: 0px;
	bottom: 0px;
	height: 30px;
	width: 100%;
	background: #999;
}
</style>
</head>
<body>
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container px-lg-5">
			<a class="navbar-brand hvr-grow" href="ServletHome">Torneo</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active hvr-grow"
						style="padding-right: 18px" href="#">Home</a></li>
					<%if(jugador == null){%>
					<li class="nav-item"><a class="nav-link hvr-grow"
						href="ServletLogin"> <%="Login"%>
					</a></li>
					<%}else{%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle hvr-grow" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <%= jugador.getUsuario() %>
					</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink" style="margin-top: 7px">
							<a class="dropdown-item" href="ServletEditarPerfil">Editar
								Perfil</a> <a class="dropdown-item" href="ServletCerrarSesion">Cerrar
								Sesion</a>
						</div></li>
					<%} %>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Page Content-->
	<h1 class="text-center my-3">Torneos Próximos</h1>
	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-md">
				<table class="table table-striped table-bordered"
					style="background: white;">
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
						<% if (!dataTorneo.isEmpty()) { %>
						<% for(Torneo t : dataTorneo) { %>
						<tr>
							<td><%= t.getJuego().getDenominacion() %></td>
							<td><%= t.getTipoTorneo().getDenominacion() %></td>
							<td><%= t.getFechaInicio() %></td>
							<td><%= t.getFechaFin() %></td>
							<td><%= t.getIntentos() %></td>
							<td><%= t.getCupo() %></td>
							<td><%= t.getGanador() %></td>
							<td><%= t.getMontoInsc() %></td>
							<td>
								<form action="ServletInscripcion" method="post">
									<button type="submit" name="Inscribirse"
										class="btn 
                                    		<% if( di.contador(jugador, t) == 0){ %>
                                    		 	btn-success btn-block">
										Inscribirse
										<%}else{ %>
										btn-secondary btn-block" disabled> Ya Inscripto
										<%} %>
									</button>
									<input name="idJuego" type="hidden"
										value="<%= t.getJuego().getId() %>"> <input
										name="idTipo" type="hidden"
										value="<%= t.getTipoTorneo().getId() %>"> <input
										name="fechaInicio" type="hidden"
										value="<%= t.getFechaInicio() %>">
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
	<!-- Footer-->
	<footer class="py-5 bg-dark footer">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>
>>>>>>> sabri
</body>
</html>