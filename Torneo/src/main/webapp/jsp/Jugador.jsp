<!DOCTYPE>
<html>
<%@ page import="Entidades.Jugador" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="Entidades.Pais" %>
<%@ page import="Entidades.Jugador" %>
<head>
<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Periodo Inscripción</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<% LinkedList<Jugador> dataJugador = (LinkedList<Jugador>)request.getAttribute("data"); %>
<% LinkedList<Pais> dataPais = (LinkedList<Pais>)request.getAttribute("pais"); %>
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
	<h1 class="text-center my-3">Jugadores</h1>
    <div class="container-fluid">
        <div class="row mt-5">
            <div class="col-md">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr class="table-primary">
                            <th scope="col">ID</th>
                            <th scope="col">Usuario</th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Apellido</th>
                            <th scope="col">Contraseña</th>
                            <th scope="col">Acceso</th>
                        	<th scope="col">Pais</th>
                        	<th scope="col">Reportes</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (!dataJugador.isEmpty()) { %>
                            <% for(Jugador j : dataJugador) { %>
                                <tr>
                                    <td>
                                        <%= j.getId() %>
                                    </td>
                                    <td>
                                        <%= j.getUsuario() %>
                                    </td>
                                    <td>
                                        <%= j.getNombre() %>
                                    </td>
                                    <td>
                                        <%= j.getApellido() %>
                                    </td>
                                    <td>
                                        <%= j.getContraseña() %>
                                    </td>
                                    <td>
                                    	<%= j.getAcceso() %>
                                    </td>
                                    <td>
                                        <%= j.getPais().getNombre() %>
                                    </td>
                                    <td>
                                        <%= j.getReportes() %>
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
    <form method="post" action="ServletJugador">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Agregar</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
			  <div class="form-group">
			    <label for="usuarioNuevo">Usuario</label>
			    <input type="text" class="form-control" id="usuarioNuevo" name="usuarioNuevo">
			  </div>
			  <div class="form-group">
			    <label for="nombreNuevo">Nombre</label>
			    <input type="text" class="form-control" id="nombreNuevo" name="nombreNuevo">
			  </div>
			  <div class="form-group">
			    <label for="apellidoNuevo">Apellido</label>
			    <input type="text" class="form-control" id="apellidoNuevo" name="apellidoNuevo">
			  </div>
			  <div class="form-group">
			    <label for="contraseñaNuevo">Contraseña</label>
			    <input type="password" class="form-control" id="contraseñaNuevo" name="contraseñaNuevo">
			  </div>
			  <div class="form-group">
			    <label for="accesoNuevo">Acceso</label>
			    <input type="text" class="form-control" id="accesoNuevo" name="accesoNuevo">
			  </div>
			  <div class="form-group">
			    <label for="paisNuevo">País</label>
			    <input type="text" class="form-control" id="paisNuevo" name="paisNuevo">
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
    <form method="post" action="ServletJugador">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Actualizar</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	  <div>
			    <label for="idActualizar">ID</label>
			    <input type="number" class="form-control" id="idActualizar" name="idActualizar">
			  </div>
	      	  <div>
			    <label for="usuarioActualizar">Usuario</label>
			    <input type="text" class="form-control" id="usuarioActualizar" name="usuarioActualizar">
			  </div>
			  <div class="form-group">
			    <label for="nombreActualizar">Nombre</label>
			    <input type="text" class="form-control" id="nombreActualizar" name="nombreActualizar">
			  </div>
			  <div class="form-group">
			    <label for="apellidoActualizar">Apellido</label>
			    <input type="text" class="form-control" id="apellidoActualizar" name="apellidoActualizar">
			  </div>
			  <div class="form-group">
			    <label for="contraseñaActualizar">Contraseña</label>
			    <input type="password" class="form-control" id="contraseñaActualizar" name="contraseñaActualizar">
			  </div>
			  <div class="form-group">
			    <label for="accesoActualizar">Acceso</label>
			    <input type="text" class="form-control" id="accesoActualizar" name="accesoActualizar">
			  </div>
			  <div class="form-group">
			    <label for="paisActualizar">País</label>
			    <input type="text" class="form-control" id="paisActualizar" name="paisActualizar">
			  </div>
			  <div class="form-group">
			    <label for="reportesActualizar">Reportes</label>
			    <input type="number" class="form-control" id="reportesActualizar" name="reportesActualizar">
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
	    <form method="post" action="ServletJugador">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
				  <div class="form-group">
				    <label for="usuarioEliminar">Usuario</label>
				    <input type="text" class="form-control" id="usuarioEliminar" name="usuarioEliminar">
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