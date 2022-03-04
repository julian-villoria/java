<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Entidades.Jugador"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
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
					<%if(Objects.isNull(jugador)){%>
					<li class="nav-item"><a class="nav-link" href="ServletLogin">
							<%="Login"%>
					</a></li>
					<%}else{%>
					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <%= jugador.getUsuario() %>
					</a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="ServletEditarPerfil">Editar
								Perfil</a> <a class="dropdown-item" href="ServletCerrarSesion">Cerrar
								Sesion</a>
						</div></li>
					<%} %>
				</ul>
			</div>
		</div>
	</nav>
	<!-- Header-->
	<header class="py-5">
		<div class="container px-lg-5">
			<form action="ServletReportes" method="POST">
				<div class="p-4 p-lg-5 bg-light rounded-3 text-center">
					<div class="m-4 m-lg-5">
						<h1 class="display-5 fw-bold">Reportar un jugador</h1>
						<div class="mb-3">
								<label for="nombreJugador" class="form-label">Usuario/Jugador</label>
								<input type="email" class="form-control"
									id="nombreJugador" name="nombreJugador" placeholder="Ingrese el nombre del usuario que desea reportar">
						</div>
						<p>Por favor, indicanos tan claramente como sea posible lo
						sucedido con este jugador. </p>
						<p>Puedes elegir tantas categorias como creas necesario.</p>
						<div>
						<div class="checkButtons" style="background-color:grey; border-radius: 4px; 
						width: fit-content; margin-left: 150px; ">
						<ul style="list-style: none; padding-inline-start:10px; padding-inline-end:10px; padding-top:10px; padding-bottom:10px; text-align: left;">
						<li>
							<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="flexCheckDefault"> <label class="form-check-label"
								for="flexCheckDefault"> Trampa </label>
							</div>
						</li>
						<li>
							<div class="form-check">
								<input class="form-check-input" type="checkbox" value=""
									id="flexCheckDefault"> <label
									class="form-check-label" for="flexCheckChecked"> Nombre
									inofensivo o inapropiado </label>
							</div>
						</li>
						<li>
							<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="flexCheckDefault" checked> <label
								class="form-check-label" for="flexCheckChecked"> Actitud negativa </label>
							</div>
						</li>
						<li>
							<div class="form-check">
							<input class="form-check-input" type="checkbox" value=""
								id="flexCheckDefault" checked> <label
								class="form-check-label" for="flexCheckChecked"> Expresiones de odio </label>
							</div>
						</li>
						<li></li>
						</ul>
						</div>
							<div class="mb-3">
								<label for="exampleFormControlTextarea1" class="form-label"></label>
								<textarea class="form-control" id="exampleFormControlTextarea1"
									rows="3" placeholder="Ofrecenos contexto adicional sobre lo sucedido"></textarea>
							</div>
						</div>
							<div class="form-group">
								<div class="row d-flex justify-content-end">
									<button type="submit" class="btn btn-primary">Enviar Reporte</button>
								</div>
							</div>
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
</html>