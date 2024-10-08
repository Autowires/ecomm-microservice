<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Admin - Retailer Management</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap 4.5.2 CSS -->
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/styles/admin.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div class="container mt-5">
		<h1 class="mb-4">Retailer Management</h1>

		<!-- Retailer Table -->
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Username</th>
					<th scope="col">Email</th>
					<th scope="col">Shop Name</th>
					<th scope="col">GSTIN</th>
					<th scope="col">Pan Number</th>
					<th scope="col">Address</th>
					<th scope="col">Status</th>
					<th scope="col">Manage Status</th>
				</tr>
			</thead>
			<tbody>
				<!-- Loop through only retailers -->
				<c:forEach var="user" items="${users}">

					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td>${user.shopName}</td>
						<td>${user.gstin}</td>
						<td>${user.pannumber}</td>
						<td>${user.address}</td>
						<td>${user.status}</td>
						<td>

							<form action="/admin/status" method="post"
								style="display: inline;">
								<input type="hidden" name="_method" value="PUT" /> <input
									type="hidden" name="userId" value="${user.id}" /> <input
									type="hidden" name="status" value="ACTIVE" />
								<button type="submit" class="btn btn-success btn-sm"
									<c:if test="${user.status != 'UNDER_REVIEW'}">disabled</c:if>>
									&#10004;
									<!-- Checkmark -->
								</button>
							</form>

							<form action="/admin/status" method="post"
								style="display: inline;">
								<input type="hidden" name="_method" value="PUT" /> <input
									type="hidden" name="userId" value="${user.id}" /> <input
									type="hidden" name="status" value="REJECTED" />
								<button type="submit" class="btn btn-danger btn-sm"
									<c:if test="${user.status != 'UNDER_REVIEW'}">disabled</c:if>>
									&#10006;
									<!-- Cross -->
								</button>
							</form>
						</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>

		<!-- Display when no retailers are found -->
		<c:if test="${empty users}">
			<div class="alert alert-warning" role="alert">No retailers
				found.</div>
		</c:if>
	</div>

	<!-- Bootstrap JS and dependencies -->
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
