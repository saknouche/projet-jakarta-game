<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inscription des utilisateurs</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./assets/css/styles.css">
</head>
<body class="bg-main">
<c:import url="./lib/menu.jsp" />
<div class="container">
		<div class="row justify-content-center align-items-center" style="height: 100vh">
			<div class="col-md-5 p-5 bg-box text-light rounded">
				<form action="register" method="POST">
					<div class="d-flex justify-content-center mb-3">					
						<img alt="sign in logo" src="assets/img/login.png" class="img-fluid w-50 rounded-circle" >
					</div>
					<h2 class="mb-3 text-center">Please register</h2>
					<input type="text" name="email" class="form-control mb-1" value="<c:out value="${user.email }"/>" placeholder="email">
					<c:if test="${!empty form.erreurs['email'] }">
						<p class="text-danger"><c:out value="${ form.erreurs['email']}"/></p>
					</c:if>
					<input type="password" name="password" class="form-control mb-1" placeholder="password">
					<c:if test="${!empty form.erreurs['password'] }">
						<p class="text-danger"><c:out value="${ form.erreurs['password']}"/></p>
					</c:if>
					<div class="d-flex justify-content-end">
						<button class="btn btn-primary mb-3">Register</button>
					</div>
				</form>
			<c:choose>
				<c:when test="${!empty form.erreurs || !empty form.faillure }">
					<p class="alert alert-danger"><c:out value="${form.faillure }"/></p>
				</c:when>
				<c:when test="${!empty form.success }">
					<p class="alert alert-success"><c:out value="${form.success }"/></p>
				</c:when>
			</c:choose>
			</div>
		</div>
	</div>
</body>
</html>