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
						<div class="container">
								<div class="row">
								  <div class="col-sm">

								  </div>
								  <div class="col-sm">
									<ul style=" padding-left: 0; text-align: left; list-style: none; background-color: grey;">
										<li style="padding-right: 0;">
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="c" id="trampa" value="Trampa"> <label
													class="form-check-label" for="trampa"> Trampa </label>
											</div>
										</li>
										<li>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="c" id="nombreOfensivo" value="Nombre Ofensivo"> <label
													class="form-check-label" for="nombreOfensivo"> Nombre
													Ofensivo o inapropiado </label>
											</div>
										</li>
										<li>
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="c" id="actNegativa" value="Actitud Negativa"> <label
													class="form-check-label" for="actNegativa"> Actitud
													negativa </label>
											</div>
										</li>
										<li>
		
											<div class="form-check">
												<input class="form-check-input" type="radio"
													name="c" id="expOdio" value="Expresiones de Odio"> <label
													class="form-check-label" for="expOdio"> Expresiones
													de odio </label>
											</div>
										</li>
									</ul>
								  </div>
								  <div class="col-sm">
									
								  </div>
								</div>
						</div>
						<div class="checkButtons;">
						</div>
						<div class="mb-3">
							<label for="exampleFormControlTextarea1" class="form-label"></label>
							<textarea class="form-control" id="content" rows="3" name = "content"
								placeholder="Ofrecenos contexto adicional sobre lo sucedido"></textarea>
						</div>
					</div>
					<div>
						<ul
							style="list-style: none; display: flex; align-items: center; align-content: space-between; justify-content: space-around;">
							<li><a href="ServletHome" class="btn btn-danger hvr-grow2"
								role="button">Cancelar</a></li>
							<li>
								<button type="submit" class="btn btn-info hvr-grow2">Reportar Jugador</button>
							</li>
						</ul>
					</div>
				</div>
			</form>
		</div>
	</header>
	<!-- Footer-->
	<footer class="py-5 bg-dark footer">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p>
		</div>
	</footer>
</body>
</html>