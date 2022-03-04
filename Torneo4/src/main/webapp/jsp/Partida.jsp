<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="Datos.DataTorneo"%>
<%@ page import="Entidades.Jugador"%>
<%@ page import="Datos.DataTorneo"%>
<!DOCTYPE html>
<html>
<head>
   	<% Jugador jugador = (Jugador) session.getAttribute("jugador"); %>
   	<% DataTorneo dt = new DataTorneo(); %>
   	<% int intentos = dt.getTorneoJugadorActual(jugador).getIntentos(); %>
	<meta charset="ISO-8859-1">
	<title>JavaScript Snake game</title>    
	<style>
	    canvas {
	    background: black;    
	    display: block;
	    width: 600px;
	    }
	    h1 {
	        text-align: center;
	    }
	    button{
	    	align-self: left;
	    }
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
.hvr-grow2 {
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

.hvr-grow2:hover, .hvr-grow2:focus, .hvr-grow2:active {
	-webkit-transform: scale(1.05);
	transform: scale(1.05);
}

.footer {
	position: relative;
	left: 0px;
	bottom: 0px;
	height: 30px;
	width: 100%;
	background: #999;}
	</style>
	<script type="text/javascript" src="jsp/snake.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</head>
<body onload="init();">
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
    <img alt="apple" src="jsp/apple.png" id="apple" hidden="true">
    <img alt="head" src="jsp/head.png" id="head" hidden="true">
    <img alt="dot" src="jsp/dot.png" id="dot" hidden="true">
    <h1>Snake</h1>
    <div class="container">
    	<div class="row justify-content-center">
    		<canvas id="myCanvas" width=300 height=300></canvas>
		</div>
		<form action="ServletPartida" method="post">
		  <div class="form-row my-3">
		  	<div class="col-2"></div>
			  <div class="col4">
			    <label for="puntos" class="col-sm-3 col-form-label">Puntos: </label>
			    <input type="text" readonly class="form-control-sm-plaintext text-center" id="puntos" name="puntos" value="2">
			  </div>
			  <div class="col4">
			    <label for="intentos" class="col-sm-3 col-form-label">Intentos: </label>
			    <input type="text" readonly class="form-control-sm-plaintext text-center" id="intentos" name="intentos" value="<%= dt.getTorneoJugadorActual(jugador).getIntentos() %>">
			  </div>
			  <button type="submit" class="btn btn-info hvr-grow2">Enviar</button>
			  <div class="col"></div>
		  </div>
		</form>
	</div>
	<footer class="py-5 bg-dark footer">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>
</body>
</html>