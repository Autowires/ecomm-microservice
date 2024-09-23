<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Admin - User Management</title>
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
		<h1 class="mb-4">User Management</h1>

		<!-- Filter Form using HTML select -->
		<form method="get" action="/admin" class="form-inline mb-4">
			<div class="form-group mr-2">
				<label for="role" class="mr-2">Filter by Role:</label> <select
					name="role" id="role" class="form-control">
					<option value="">All Roles</option>
					<option value="CUSTOMER"
						<c:if test="${param.role == 'CUSTOMER'}">selected</c:if>>Customer</option>
					<option value="RETAILER"
						<c:if test="${param.role == 'RETAILER'}">selected</c:if>>Retailer</option>
				</select>
			</div>
			<button type="submit" class="btn btn-primary">Filter</button>
		</form>

		<!-- User Table -->
		<table class="table table-bordered table-striped">
			<thead>
				<tr>
					<th scope="col">ID</th>
					<th scope="col">Username</th>
					<th scope="col">Email</th>
					<th scope="col">Role</th>
					<th scope="col">Status</th>
					<th scope="col">Update Status</th>
					<!-- New column for status update -->
				</tr>
			</thead>
			<tbody>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td>${user.role}</td>
						<td>${user.status}</td>
						<td>
						<form:form method="put" style="display:inline;">
								<input type="hidden" name="userId" value="${user.id}" />
								<but	ton type="submit" name="status" value="ACTIVE"
									class="btn btn-success btn-sm"
									<c:if test="${user.status == 'ACTIVE'}">disabled</c:if>>Activate</button>
							</form:form> 
							<form:form method="put" style="display:inline;">
								<input type="hidden" name="userId" value="${user.id}" />
								<button type="submit" name="status" value="SUSPENDED"
									class="btn btn-warning btn-sm"
									<c:if test="${user.status == 'SUSPENDED'}">disabled</c:if>>Suspend</button>
							</form:form> <form:form method="put" style="display:inline;">
								<input type="hidden" name="userId" value="${user.id}" />
								<button type="submit" name="status" value="DEACTIVATED"
									class="btn btn-secondary btn-sm"
									<c:if test="${user.status == 'DEACTIVATED'}">disabled</c:if>>Deactivate</button>
							</form:form></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<!-- Display when no users are found -->
		<c:if test="${empty users}">
			<div class="alert alert-warning" role="alert">No users found
				for the selected filter.</div>
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
