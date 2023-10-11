<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Menu</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="./assets/css/styles.css">
</head>
<body>
	<div class="container-fluid bg-box mb-4 py-2">
		<div class="row">
			<div class="col d-flex justify-content-between align-items-center">
				<a href="home">
					<img alt="logo" src="assets/img/manette.jpeg" class="img-fluid logo">
				</a>
			<c:choose>
				<c:when test="${!empty sessionScope.user }">
				<div>
					<a href="home" class="text-decoration-none me-2 fw-bold btn btn-outline-light">Home</a>
					<a href="insert" class="text-decoration-none me-3 fw-bold btn btn-outline-light">Add</a>
					<a class="btn btn-danger fw-bold" href="deconnect">Logout</a>
				</div>
				</c:when>
				<c:otherwise>
					<div>
						<a href="register" class="text-decoration-none me-2 fw-bold btn btn-outline-primary">Register</a>
						<a href="connect" class="text-decoration-none me-3 fw-bold btn btn-outline-primary">Sign in</a>
					</div>
				</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
</body>
</html>