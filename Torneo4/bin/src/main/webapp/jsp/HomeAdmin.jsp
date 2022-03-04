<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="Entidades.Jugador"%>
<!DOCTYPE html>
<html lang="en">
<head>
<%Jugador jugador = (Jugador) session.getAttribute("jugador");%>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Home</title>
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

.footer {
	position: relative;
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
	<!-- Page Content-->
	<header class="py-5"> </header>
	<section class="pt-4">
		<div class="container px-lg-5">
			<!-- Page Features-->
			<div class="row gx-lg-5">
				<div class="col-lg-6 col-xxl-4 mb-5 caja">
					<div class="card bg-light border-0 h-100">
						<div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
							<div
								class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">
								<i class="bi bi-collection"></i>
							</div>
							<h2 class="fs-4 fw-bold">Periodo Inscripción ABMC Simple</h2>
							<a href="ServletPeriodoInscripcion"
								class="btn btn-info btn-lg hvr-grow">Ir</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-xxl-4 mb-5 caja">
					<div class="card bg-light border-0 h-100">
						<div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
							<div
								class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">
								<i class="bi bi-cloud-download"></i>
							</div>
							<h2 class="fs-4 fw-bold">Torneo ABMC Dependiente</h2>
							<a href="ServletTorneo" class="btn btn-info btn-lg hvr-grow">Ir</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-xxl-4 mb-5 caja">
					<div class="card bg-light border-0 h-100">
						<div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
							<div
								class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">
								<i class="bi bi-card-heading"></i>
							</div>
							<h2 class="fs-4 fw-bold">Países ABMC Simple</h2>
							<a href="ServletPais" class="btn btn-info btn-lg hvr-grow">Ir</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-xxl-4 mb-5 caja">
					<div class="card bg-light border-0 h-100">
						<div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
							<div
								class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">
								<i class="bi bi-bootstrap"></i>
							</div>
							<h2 class="fs-4 fw-bold">Tipo Torneo ABMC Simple</h2>
							<a href="ServletTipoTorneo" class="btn btn-info btn-lg hvr-grow">Ir</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-xxl-4 mb-5 caja">
					<div class="card bg-light border-0 h-100">
						<div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
							<div
								class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">
								<i class="bi bi-bootstrap"></i>
							</div>
							<h2 class="fs-4 fw-bold">Juego ABMC Simple</h2>
							<a href="ServletJuego" class="btn btn-info btn-lg hvr-grow">Ir</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-xxl-4 mb-5 caja">
					<div class="card bg-light border-0 h-100">
						<div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
							<div
								class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">
								<i class="bi bi-code"></i>
							</div>
							<h2 class="fs-4 fw-bold">Jugador ABMC Dependiente</h2>
							<a href="ServletJugador" class="btn btn-info btn-lg hvr-grow">Ir</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-xxl-4 mb-5 caja">
					<div class="card bg-light border-0 h-100">
						<div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
							<div
								class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">
								<i class="bi bi-patch-check"></i>
							</div>
							<h2 class="fs-4 fw-bold">Dificultad ABMC Dependiente</h2>
							<a href="ServletDificultad" class="btn btn-info btn-lg hvr-grow">Ir</a>
						</div>
					</div>
				</div>
				<div class="col-lg-6 col-xxl-4 mb-5 caja">
					<div class="card bg-light border-0 h-100">
						<div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
							<div
								class="feature bg-primary bg-gradient text-white rounded-3 mb-4 mt-n4">
								<i class="bi bi-patch-check"></i>
							</div>
							<h2 class="fs-4 fw-bold">Inscripcion ABMC Dependiente</h2>
							<a href="ServletInscripcionCrud"
								class="btn btn-info btn-lg hvr-grow">Ir</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<!-- Footer-->
	<footer class="py-5 bg-dark footer">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2021</p>
		</div>
	</footer>
</body>
>>>>>>> sabri
</html>