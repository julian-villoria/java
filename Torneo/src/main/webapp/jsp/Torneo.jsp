<!DOCTYPE>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="Entidades.Torneo" %>
<%@ page import="Entidades.Juego" %>
<%@ page import="Entidades.TipoTorneo" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Periodo Inscripción</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<% LinkedList<Torneo> data = (LinkedList<Torneo>)request.getAttribute("data"); %>
<% LinkedList<Juego> dataJuego = (LinkedList<Juego>)request.getAttribute("juego"); %>
<% LinkedList<TipoTorneo> dataTipo = (LinkedList<TipoTorneo>)request.getAttribute("TipoTorneo"); %>
</head>
<body>
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
                        </tr>
                    </thead>
                    <tbody>
                        <% if (!data.isEmpty()) { %>
                            <% for(Torneo t : data) { %>
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
			    <label for="GanadorActualizar">Ganador</label>
			    <input type="text" class="form-control" id="ganadorActualizar" name="ganadorActualizar">
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
	      	<button type="submit" class="btn btn-primary">Eliminar</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
	      </div>
	    </div>
    </form>
  </div>
</div>
    
</body>
</html>