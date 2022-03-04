<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Entidades.Jugador"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
<%
Jugador jugador = (Jugador) session.getAttribute("jugador");
%>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Reportar Jugador</title>
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
</head>
<body>
	<!-- Responsive navbar-->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<div class="container px-lg-5">
			<a class="navbar-brand" href="ServletHome">Torneo</a>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#">Home</a></li>
					<%
					if (Objects.isNull(jugador)) {
					%>
					<li class="nav-item"><a class="nav-link" href="ServletLogin">
							<%="Login"%>
					</a></li>
					<%
					} else {
					%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <%=jugador.getUsuario()%>
					</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="ServletEditarPerfil">Editar
								Perfil</a> <a class="dropdown-item" href="ServletCerrarSesion">Cerrar
								Sesion</a>
						</div></li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Header-->
	<header class="py-5">
		<div class="container px-lg-5">
			<form action="ServletReportes" method="post">
				<div class="p-4 p-lg-5 bg-light rounded-3 text-center">
					<div class="m-4 m-lg-5">
						<h1 class="display-5 fw-bold">Reportar un jugador</h1>
						<div class="mb-3">
							<label for="exampleFormControlInput1" class="form-label">Usuario/Jugador</label>
							<input type="text" class="form-control" id="usuarioReportado" name="usuarioReportado"
								placeholder="Ingrese el nombre del usuario que desea reportar">
						</div>
						<p>Por favor, indicanos tan claramente como sea posible lo
							sucedido con este jugador.</p>
						<div class="checkButtons"
							style="width: fit-content; text-align: left; align-content: center; justify-content: center;">
							<ul
								style="list-style: none; background-color: grey; border-radius: 4px;">
								<li style="padding-right: 0;">
									<div class="form-check">
										<input class="form-check-input" type="radio"
											name="flexRadioDefault" id="trampa"> <label
											class="form-check-label" for="trampa"> Trampa </label>
									</div>
								</li>
								<li>
									<div class="form-check">
										<input class="form-check-input" type="radio"
											name="flexRadioDefault" id="nombreOfensivo"> <label
											class="form-check-label" for="nombreOfensivo"> Nombre
											Ofensivo o inapropiado </label>
									</div>
								</li>
								<li>
									<div class="form-check">
										<input class="form-check-input" type="radio"
											name="flexRadioDefault" id="actNegativa"> <label
											class="form-check-label" for="actNegativa"> Actitud
											negativa </label>
									</div>
								</li>
								<li>

									<div class="form-check">
										<input class="form-check-input" type="radio"
											name="flexRadioDefault" id="expOdio"> <label
											class="form-check-label" for="expOdio"> Expresiones
											de odio </label>
									</div>
								</li>
								<li></li>
							</ul>
						</div>
						<div class="mb-3">
							<label for="exampleFormControlTextarea1" class="form-label"></label>
							<textarea class="form-control" id="content" rows="3"
								placeholder="Ofrecenos contexto adicional sobre lo sucedido"></textarea>
						</div>
						<ul
							style="list-style: none; display: flex; align-items: center; align-content: space-between; justify-content: space-around;">
							<li><a href="ServletHome" class="btn btn-danger"
								role="button">Cancelar</a></li>
							<li>
								<button type="button" class="btn btn-dark" data-toggle="modal"
									data-target="#ModalExitoso">Reportar Jugador</button>
							</li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</header>
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>
</body>

<!-- Modal Mensaje Exitoso-->
<div class="modal show" id="ModalExitoso" tabindex="-1"
	role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">Mensaje</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				<p>Reporte Exitoso</p>
			</div>
			<div class="modal-footer">
				<a href="ServletHome" class="btn btn-secondary" role="button">Aceptar</a>
			</div>
		</div>
	</div>
</div>

<script>
$('#myModal').modal({backdrop:'static',keyboard:false, show:true});
</script>
</html>





