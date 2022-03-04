<!DOCTYPE>
<html>
<%@ page import="Entidades.Jugador" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="Entidades.Torneo" %>
<%@ page import="Entidades.Juego" %>
<%@ page import="Entidades.TipoTorneo" %>
<%@ page import="Entidades.Jugador" %>
<head>
<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Periodo Inscripción</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<% LinkedList<Torneo> dataTorneo = (LinkedList<Torneo>)request.getAttribute("Torneo"); %>
<% LinkedList<Juego> dataJuego = (LinkedList<Juego>)request.getAttribute("Juego"); %>
<% LinkedList<TipoTorneo> dataTipo = (LinkedList<TipoTorneo>)request.getAttribute("TipoTorneo"); %>
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
	-webkit-transform: scale(1.025);
	transform: scale(1.025);
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
	<h1 class="text-center my-3">Torneos</h1>
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
                                </tr>
                            <% } %>
                        <% } %>
                    </tbody>
                </table>			
            </div>
        </div>
   <div>
	        <!-- Button trigger modal -->
			<button type="button" class="btn btn-success hvr-grow2" data-toggle="modal" data-target="#ModalAgregar">
	 			Agregar
			</button>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-info hvr-grow2" data-toggle="modal" data-target="#ModalActualizar">
	 			Actualizar
			</button>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-danger hvr-grow2" data-toggle="modal" data-target="#ModalEliminar">
	 			Eliminar
			</button>
		</div>
    </div>

<!-- Modal Agregar-->
<div class="modal fade" id="ModalAgregar" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <form method="post" action="ServletTorneo">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Agregar</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	  <div class="form-group">
				    <label for="juegoNuevo">Juego:</label>
					<select name="juegoNuevo" id="juegoNuevo">
						<% if(!dataJuego.isEmpty()){ %>
							<% for(Juego j: dataJuego){ %>
								<option value="<%= j.getDenominacion() %>"> <%= j.getDenominacion() %> </option>
							<% } %>
						<% } %>
					</select>
			  </div>
			  <div class="form-group">
				    <label for="tipoTorneoNuevo">Tipo de Torneo:</label>
					<select name="tipoTorneoNuevo" id="tipoTorneoNuevo">
						<% if(!dataTipo.isEmpty()){ %>
							<% for(TipoTorneo tt: dataTipo){ %>
								<option value="<%= tt.getDenominacion() %>"><%= tt.getDenominacion() %></option>
							<% } %>
						<% } %>
					</select>
			  </div>
			  <div class="form-group">
			    <label for="fechaInicioNuevo">Fecha Inicio</label>
			    <input type="date" class="form-control" id="fechaInicioNuevo" name="fechaInicioNuevo">
			  </div>
			  <div class="form-group">
			    <label for="fechaFinNuevo">Fecha Fin</label>
			    <input type="date" class="form-control" id="fechaFinNuevo" name="fechaFinNuevo">
			  </div>
			  <div class="form-group">
			    <label for="intentosNuevo">Intentos</label>
			    <input type="number" class="form-control" id="intentosNuevo" name="intentosNuevo">
			  </div>
			  <div class="form-group">
			    <label for="cupoNuevo">Cupo</label>
			    <input type="number" class="form-control" id="cupoNuevo" name="cupoNuevo">
			  </div>
			  <div class="form-group">
			    <label for="ganadorNuevo">Ganador</label>
			    <input type="text" class="form-control" id="ganadorNuevo" name="ganadorNuevo">
			  </div>
			  <div class="form-group">
			    <label for="montoNuevo">Monto de Inscripción</label>
			    <input type="text" class="form-control" id="montoNuevo" name="montoNuevo">
			  </div>
	      </div>
	      <div class="modal-footer">
	      	<button type="submit" class="btn btn-info hvr-grow2">Agregar</button>
	     	<button type="button" class="btn btn-secondary hvr-grow2" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
    </form>
  </div>
</div>

<!-- Modal Actualizar-->
<div class="modal fade" id="ModalActualizar" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <form method="post" action="ServletTorneo">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Actualizar</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	  <div class="form-group">
				    <label for="juegoActualizar">Juego:</label>
					<select name="juegoActualizar" id="juegoActualizar">
						<% if(!dataJuego.isEmpty()){ %>
							<% for(Juego j: dataJuego){ %>
								<option value="<%= j.getDenominacion() %>"> <%= j.getDenominacion() %> </option>
							<% } %>
						<% } %>
					</select>
			  </div>
			  <div class="form-group">
				    <label for="tipoTorneoActualizar">Tipo de Torneo:</label>
					<select name="tipoTorneoActualizar" id="tipoTorneoActualizar">
						<% if(!dataTipo.isEmpty()){ %>
							<% for(TipoTorneo tt: dataTipo){ %>
								<option value="<%= tt.getDenominacion() %>"><%= tt.getDenominacion() %></option>
							<% } %>
						<% } %>
					</select>
			  </div>
			  <div class="form-group">
			    <label for="fechaInicioActualizar">Fecha Inicio</label>
			    <input type="date" class="form-control" id="fechaInicioActualizar" name="fechaInicioActualizar">
			  </div>
			  <div class="form-group">
			    <label for="FechaFinActualizar">Fecha Fin</label>
			    <input type="date" class="form-control" id="FechaFinActualizar" name="fechaFinActualizar">
			  </div>
			  <div class="form-group">
			    <label for="intentosActualizar">Intentos</label>
			    <input type="number" class="form-control" id="intentosActualizar" name="intentosActualizar">
			  </div>
			  <div class="form-group">
			    <label for="cupoActualizar">Cupo</label>
			    <input type="number" class="form-control" id="cupoActualizar" name="cupoActualizar">
			  </div>
			  <div class="form-group">
			    <label for="ganadorActualizar">Ganador</label>
			    <input type="text" class="form-control" id="ganadorActualizar" name="ganadorActualizar">
			  </div>
			  <div class="form-group">
			    <label for="montoActualizar">Monto de Inscripcion</label>
			    <input type="text" class="form-control" id="montoActualizar" name="montoActualizar">
			  </div>
			<div class="modal-footer">
		      	<button type="submit" class="btn btn-info hvr-grow2">Actualizar</button>
		     	<button type="button" class="btn btn-secondary hvr-grow2" data-dismiss="modal">Cerrar</button>
	      	</div>
	      </div>
	    </div>
    </form>
  </div>
</div>

<!-- Modal Eliminar-->
<div class="modal fade" id="ModalEliminar" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <form method="post" action="ServletTorneo">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	  <div class="form-group">
				    <label for="juegoEliminar">Juego:</label>
					<select name="juegoEliminar" id="juegoEliminar">
						<% if(!dataJuego.isEmpty()){ %>
							<% for(Juego j: dataJuego){ %>
								<option value="<%= j.getDenominacion() %>"> <%= j.getDenominacion() %> </option>
							<% } %>
						<% } %>
					</select>
			  </div>
			  <div class="form-group">
				    <label for="tipoTorneoEliminar">Tipo de Torneo:</label>
					<select name="tipoTorneoEliminar" id="tipoTorneoEliminar">
						<% if(!dataTipo.isEmpty()){ %>
							<% for(TipoTorneo tt: dataTipo){ %>
								<option value="<%= tt.getDenominacion() %>"><%= tt.getDenominacion() %></option>
							<% } %>
						<% } %>
					</select>
			  </div>
			  <div class="form-group">
			    <label for="fechaInicioEliminar">Fecha Inicio</label>
			    <input type="date" class="form-control" id="fechaInicioEliminar" name="fechaInicioEliminar">
			  </div>
	      </div>
	      <div class="modal-footer">
	      	<button type="submit" class="btn btn-info hvr-grow2">Eliminar</button>
	        <button type="button" class="btn btn-secondary hvr-grow2" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
    </form>
  </div>
</div>
   <footer class="py-5 bg-dark footer">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>  
</body>
</html>