<!DOCTYPE>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*"%>
<%@ page import="Entidades.Dificultad"%>
<%@ page import="Entidades.Jugador"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<% Jugador jugador = (Jugador) session.getAttribute("jugador"); %>
<title>Dificultad</title>
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
<% LinkedList<Dificultad> data = (LinkedList<Dificultad>)request.getAttribute("data"); %>
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
	<!-- Responsive navbar-->
	<h1 class="text-center my-3">Dificultades</h1>
	<div class="container-fluid">
		<div class="row mt-5">
			<div class="col-md">
				<table class="table table-striped table-bordered">
					<thead class="thead-dark">
						<tr class="table-primary">
							<th scope="col">Nombre</th>
							<th scope="col">Id Juego</th>
							<th scope="col">Puntaje Minimo</th>
							<th scope="col">Puntaje Maximo</th>
							<th scope="col">Victorias Minimas</th>
							<th scope="col">Victorias Maximas</th>
						</tr>
					</thead>
					<tbody>
						<% if (!data.isEmpty()) { %>
						<% for(Dificultad d : data) { %>
						<tr>
							<td><%= d.getNombre() %></td>
							<td><%= d.getJuego().getId() %></td>
							<td><%= d.getRangoMinPuntajes() %></td>
							<td><%= d.getRangoMaxPuntajes() %></td>
							<td><%= d.getRangoMinVictorias() %></td>
							<td><%= d.getRangoMaxVictorias() %></td>
						</tr>
						<% } %>
						<% } %>
					</tbody>
				</table>
			</div>
		</div>
			<div>
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-success hvr-grow2" data-toggle="modal"
			data-target="#ModalAgregar">Agregar</button>
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-info hvr-grow2" data-toggle="modal"
			data-target="#ModalActualizar">Actualizar</button>
		<!-- Button trigger modal -->
		<button type="button" class="btn btn-danger hvr-grow2" data-toggle="modal"
			data-target="#ModalEliminar">Eliminar</button>
	</div>
	</div>
	<!-- Modal Agregar-->
	<div class="modal fade" id="ModalAgregar" tabindex="-1" role="dialog"
		aria-labelledby="ModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form method="post" action="ServletDificultad">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Agregar</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="nuevoNombre">Nombre</label> <input type="text"
								class="form-control" id="nuevoNombre" name="nuevoNombre">
						</div>
						<div class="form-group">
							<label for="nuevoRangoMinPuntaje">Puntaje Minimo</label> <input
								type="number" class="form-control" id="nuevoRangoMinPuntaje"
								name="nuevoRangoMinPuntaje">
						</div>
						<div class="form-group">
							<label for="nuevoRangoMaxPuntaje">Puntaje Maximo</label> <input
								type="number" class="form-control" id="nuevoRangoMaxPuntaje"
								name="nuevoRangoMaxPuntaje">
						</div>
						<div class="form-group">
							<label for="nuevoRangoMinVictorias">Victoias Minimas</label> <input
								type="number" class="form-control" id="nuevoRangoMinVictorias"
								name="nuevoRangoMinVictorias">
						</div>
						<div class="form-group">
							<label for="nuevoRangoMaxVictorias">Victorias Máximas</label> <input
								type="number" class="form-control" id="nuevoRangoMaxVictorias"
								name="nuevoRangoMaxVictorias">
						</div>
						<div class="form-group">
							<label for="nuevoJuego">Juego</label> <input type="text"
								class="form-control" id="nuevoJuego" name="nuevoJuego">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-info hvr-grow2">Agregar</button>
						<button type="button" class="btn btn-secondary hvr-grow2"
							data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- Modal Actualizar-->
	<div class="modal fade" id="ModalActualizar" tabindex="-1"
		role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form method="post" action="ServletDificultad">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Actualizar</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="actualizarNombre">Nombre</label> <input type="text"
								class="form-control" id="actualizarNombre"
								name="actualizarNombre">
						</div>
						<div class="form-group">
							<label for="actualizarRangoMinPuntaje">Puntaje Minimo</label> <input
								type="number" class="form-control"
								id="actualizarRangoMinPuntaje" name="actualizarRangoMinPuntaje">
						</div>
						<div class="form-group">
							<label for="actualizarRangoMaxPuntaje">Puntaje Maximo</label> <input
								type="number" class="form-control"
								id="actualizarRangoMaxPuntaje" name="actualizarRangoMaxPuntaje">
						</div>
						<div class="form-group">
							<label for="actualizarRangoMinVictorias">Victoias Minimas</label>
							<input type="number" class="form-control"
								id="actualizarRangoMinVictorias"
								name="actualizarRangoMinVictorias">
						</div>
						<div class="form-group">
							<label for="actualizarRangoMaxVictorias">Victorias
								Máximas</label> <input type="number" class="form-control"
								id="actualizarRangoMaxVictorias"
								name="actualizarRangoMaxVictorias">
						</div>
						<div class="form-group">
							<label for="actualizarJuego">Juego</label> <input type="text"
								class="form-control" id="actualizarJuego" name="actualizarJuego">
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary hvr-grow2"
							data-dismiss="modal">Cerrar</button>
						<button type="submit" class="btn btn-info hvr-grow2">Actualizar</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<!-- Modal Eliminar-->
	<div class="modal fade" id="ModalEliminar" tabindex="-1" role="dialog"
		aria-labelledby="ModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<form method="post" action="ServletDificultad">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="eliminarNombre">Nombre</label> <input type="text"
								class="form-control" id="eliminarNombre" name="eliminarNombre">
						</div>
						<div class="form-group">
							<label for="eliminarJuego">Juego</label> <input type="text"
								class="form-control" id="eliminarJuego" name="eliminarJuego">
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn btn-info hvr-grow2">Eliminar</button>
						<button type="button" class="btn btn-secondary hvr-grow2"
							data-dismiss="modal">Cerrar</button>
					</div>
				</div>
			</form>
		</div>
	</div>

</body>
</html>