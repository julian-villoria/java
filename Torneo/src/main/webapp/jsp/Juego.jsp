<!DOCTYPE>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="Entidades.Juego" %>
<%@ page import="Entidades.Jugador" %>
<head>
<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Tipo Torneo</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<% LinkedList<Juego> data = (LinkedList<Juego>)request.getAttribute("data"); %>
</head>
<body>
<!-- Responsive navbar-->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container px-lg-5">
                <a class="navbar-brand" href="ServletHome">Torneo</a>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                        <li class="nav-item"><a class="nav-link" aria-current="page" href="#">Home</a></li>
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
	<h1 class="text-center my-3">Juegos</h1>
    <div class="container-fluid">
        <div class="row mt-5">
            <div class="col-md">
                <table class="table table-striped table-bordered">
                    <thead class="thead-dark">
                        <tr class="table-primary">
                            <th scope="col">ID</th>
                            <th scope="col">Denominacion</th>
                            <th scope="col">Dificultad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% if (!data.isEmpty()) { %>
                            <% for(Juego j : data) { %>
                                <tr>
                                    <td>
                                        <%= j.getId() %>
                                    </td>
                                    <td>
                                        <%= j.getDenominacion() %>
                                    </td>
                                    <td>
                                        <%= j.getDificultad().getNombre() %>
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
	    <form method="post" action="ServletJuego">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Agregar</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
				 <!--  <div class="form-group">
				    <label for="fechaDesde">ID</label>
				    <input type="date" class="form-control" id="nuevoId" name="nuevoId">
				  </div> -->
				  <div class="form-group">
				    <label for="nuevaDenominacion">Denominacion</label>
				    <input type="text" class="form-control" id="nuevaDenominacion" name="nuevaDenominacion">
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
	    <form method="post" action="ServletJuego">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Actualizar</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
				  <div class="form-group">
				    <label for="fechaDesde">ID</label>
				    <input type="number" class="form-control" id="idActualizar" name="idActualizar">
				  </div>
				  <div class="form-group">
				    <label for="denominacion">Denominacion</label>
				    <input type="text" class="form-control" id="denominacionActualizar" name="denominacionActualizar">
				  </div>
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
		      	<button type="submit" class="btn btn-primary">Actualizar</button>
		      </div>
		    </div>
	    </form>
	  </div>
	</div>
	
	<!-- Modal Eliminar-->
	<div class="modal fade" id="ModalEliminar" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <form method="post" action="ServletJuego">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">Eliminar</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
				  <div class="form-group">
				    <label for="denominacion">Denominacion</label>
				    <input type="text" class="form-control" id="tipoEliminar" name="tipoEliminar">
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