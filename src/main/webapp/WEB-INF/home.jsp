<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home page</title>
</head>
<body class="bg-main">
	<c:if test="${!empty games }">
		<c:import url="./lib/menu.jsp" />
		<div class="container pt-5">
			<c:if test="${!empty sessionScope.user }">
				<p class="alert alert-success col-6 mx-auto text-center">
					<c:out
						value="Vous êtes connecté avec l'adresse email : ${sessionScope.user.email }" />
				</p>
			</c:if>
			<h1 class="text-center text-light mt-5 mb-3">Games</h1>
			<div class="row justify-content-center">
				<div class="col mb-4">
					<div class="table-responsive bg-main">
						<table class="table table-striped table-hover">
							<thead>
								<tr>
									<th scope="col">#</th>
									<th scope="col">Nom</th>
									<th scope="col">Description</th>
									<th scope="col">Actions</th>
									<th scope="col"></th>
									<th scope="col"></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="g" items="${games }" varStatus="status">
									<tr>
										<th scope="row"><c:out value="${status.count }" /></th>
										<td><c:out value="${g.name }" /></td>
										<td><c:out value="${g.description }" /></td>

										<td><a class="btn btn-primary btn-md"
											href="show?idGame=<c:out value="${g.id }"/>">View</a></td>
										<td><a class="btn btn-info btn-md"
											href="edit?idGame=<c:out value="${g.id }"/>">Edit</a></td>
										<td><a
											onclick="return confirm('Êtes-vous certain de vouloir supprimer ce jeux? ')"
											class="btn btn-danger btn-md"
											href="delete?idGame=<c:out value="${g.id }"/>">Delete</a></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</c:if>
</body>
</html>