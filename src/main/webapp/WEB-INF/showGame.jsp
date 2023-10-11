<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Détails d'un jeux</title>

</head>
<body class="bg-main">
<c:import url="./lib/menu.jsp"/>
	<div class="container mt-5">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<c:if test="${!empty game }">
					<div class="card">
						<div class="card-header">
							Détails du jeux N°
							<c:out value="${game.id }" />
						</div>
						<div class="card-body">
							<h5 class="card-title">
								<c:out value="${game.name }" />
							</h5>
							<p class="card-text">
								<c:out value="${game.description }" />
							</p>
							<a href="home" class="btn btn-primary">Retour à la liste des
								jeux</a>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>