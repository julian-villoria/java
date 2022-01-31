<!DOCTYPE>
<html>
<%@ page import="Entidades.Jugador" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="Entidades.Torneo" %>
<%@ page import="Entidades.Juego" %>
<%@ page import="Entidades.TipoTorneo" %>
<%@ page import="Entidades.Jugador" %>
<%@ page import="Entidades.Inscripcion" %>
<head>
<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inscripciones</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<% LinkedList<Inscripcion> dataInsc = (LinkedList<Inscripcion>)request.getAttribute("Inscripcion"); %>
<% LinkedList<Torneo> dataTorneo = (LinkedList<Torneo>)request.getAttribute("Torneo"); %>
<% LinkedList<Juego> dataJuego = (LinkedList<Juego>)request.getAttribute("Juego"); %>
<% LinkedList<TipoTorneo> dataTipo = (LinkedList<TipoTorneo>)request.getAttribute("TipoTorneo"); %>
</head>
<body>
<!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-lg-5">
                <a class="navbar-brand" href="ServletHome">Torneo</a>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="#">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="ServletPartida">Partida</a></li>
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
	<h1 class="text-center my-3">Inscripciones</h1>
    <div class="container-fluid">
        <div class="row mt-5">
            <div class="col-md">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr class="table-primary">
                            <th scope="col">Usuario</th>
                            <th scope="col">Juego</th>
                            <th scope="col">Tipo</th>
                            <th scope="col">Fecha de Comienzo</th>
                            <th scope="col">Fecha de Finalización</th>
                        	<th scope="col">Fecha de Inscripcion</th>
                        	<th scope="col">Dificultad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (!dataInsc.isEmpty()) { %>
                            <% for(Inscripcion i : dataInsc) { %>
                                <tr>
                                    <td>
                                        <%=i.getJugador().getUsuario()  %>
                                    </td>
                                    <td>
                                        <%= i.getTorneo().getJuego().getDenominacion() %>
                                    </td>
                                    <td>
                                        <%= i.getTorneo().getTipoTorneo().getDenominacion() %>
                                    </td>
                                    <td>
                                        <%= i.getTorneo().getFechaInicio() %>
                                    </td>
                                    <td>
                                        <%= i.getTorneo().getFechaFin() %>
                                    </td>
                                    <td>
                                        <%= i.getFechaInscripcion() %>
                                    </td>
                                    <td>
                                        <%= i.getTorneo().getJuego().getDificultad().getNombre() %>
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
			<button type="button" class="btn btn-success" data-toggle="modal" data-target="#ModalAgregar">
	 			Agregar
			</button>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#ModalActualizar">
	 			Actualizar
			</button>
			<!-- Button trigger modal -->
			<button type="button" class="btn btn-danger" data-toggle="modal" data-target="#ModalEliminar">
	 			Eliminar
			</button>
		</div>
    </div>

<!-- Modal Agregar-->
<div class="modal fade" id="ModalAgregar" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <form method="post" action="ServletInscripcionCrud">
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
			    <label for="fechaInicioNuevo">Fecha Inicio del Torneo</label>
			    <input type="date" class="form-control" id="fechaInicioNuevo" name="fechaInicioNuevo">
			  </div>
			  <div class="form-group">
			    <label for="fechaInscNuevo">Fecha Inscripcion</label>
			    <input type="date" class="form-control" id="fechaInscNuevo" name="fechaInscNuevo">
			  </div>
			  <div class="form-group">
			    <label for="jugadorNuevo">Usuario Jugador</label>
			    <input type="text" class="form-control" id="jugadorNuevo" name="jugadorNuevo">
			  </div>
	      </div>
	      <div class="modal-footer">
	      	<button type="submit" class="btn btn-primary">Agregar</button>
	     	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
    </form>
  </div>
</div>
<!-- Modal Actualizar-->
<div class="modal fade" id="ModalActualizar" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <form method="post" action="ServletInscripcionCrud">
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
			    <label for="fechaInicioActualizar">Fecha Inicio del Torneo</label>
			    <input type="date" class="form-control" id="fechaInicioActualizar" name="fechaInicioActualizar">
			  </div>
			  <div class="form-group">
			    <label for="fechaInscActualizar">Fecha Inscripcion</label>
			    <input type="date" class="form-control" id="fechaInscActualizar" name="fechaInscActualizar">
			  </div>
			  <div class="form-group">
			    <label for="jugadorActualizar">Usuario Jugador</label>
			    <input type="text" class="form-control" id="jugadorActualizar" name="jugadorActualizar">
			  </div>
			<div class="modal-footer">
		      	<button type="submit" class="btn btn-primary">Actualizar</button>
		     	<button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      	</div>
	      </div>
	    </div>
    </form>
  </div>
</div>

<!-- Modal Eliminar-->
<div class="modal fade" id="ModalEliminar" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <form method="post" action="ServletInscripcionCrud">
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
			  <div class="form-group">
			    <label for="jugadorEliminar">Usuario Jugador</label>
			    <input type="text" class="form-control" id="jugadorEliminar" name="jugadorEliminar">
			  </div>
	      </div>
	      <div class="modal-footer">
	      	<button type="submit" class="btn btn-primary">Eliminar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
    </form>
  </div>
</div>
    
</body>
</html>