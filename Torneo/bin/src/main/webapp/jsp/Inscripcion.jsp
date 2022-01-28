<!DOCTYPE>
<html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="Entidades.Torneo" %>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Periodo Inscripción</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
	<% LinkedList<Torneo> dataTorneo = (LinkedList<Torneo>)request.getAttribute("Torneo"); %>
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
                        	<th scope="col">Monto Inscripción</th>
                        	<th></th>
                        </tr>
                    </thead>
                    <tbody>
                    	<% int i = 0; %>
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
                                    		<button type="submit" name="Inscribirse" value=<%= i %> class="btn btn-success btn-block">Inscribirse</button>
                                    	</form>
                                    </td>
                                </tr>
                                <% i++; %>
                            <% } %>
                        <% } %>
                    </tbody>
                </table>			
            </div>
        </div>
	</div>	
</body>
</html>