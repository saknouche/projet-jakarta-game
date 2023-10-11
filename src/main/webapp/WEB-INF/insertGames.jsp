<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insérer des jeux</title>
</head>
<body class="bg-main">
	<c:import url="./lib/menu.jsp"/>
	<div class="container">
		<div class="row justify-content-center" >
			<h2 class="mb-3 text-center text-light mb-3">Add Game</h2>
			<div class="col-md-8 border p-5 bg-light text-light rounded">
				<form action="insert" method="POST">
					<input type="text" name="name" class="form-control mb-2" value="<c:out value="${game.name }"/>" placeholder="nom">
					<c:if test="${!empty form.erreurs['name'] }">
						<p class="text-danger"><c:out value="${ form.erreurs['name']}"/></p>
					</c:if>
					<textarea name="description" rows="5" class="form-control mb-2" value="<c:out value="${game.description }"/>" placeholder="Ecrivez une description"><c:out value="${game.description }"/></textarea>
					<c:if test="${!empty form.erreurs['description'] }">
						<p class="text-danger"><c:out value="${ form.erreurs['description']}"/></p>
					</c:if>
					<div class="d-flex justify-content-end">
						<button class="btn btn-primary">Add</button>
					</div>
				</form>
			<c:if test="${!empty form.erreurs || !empty form.fail}">
				<p class="alert alert-danger mt-3"><c:out value="${ form.fail}"/></p>
			</c:if>
		
			<c:if test="${!empty form.success}">
				<p class="alert alert-success mt-3"><c:out value="${ form.success}"/></p>
			</c:if>
			</div>
		</div>
	</div>
</body>
</html>