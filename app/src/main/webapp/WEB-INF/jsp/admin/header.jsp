<!-- header.jsp -->
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/">QuickCart - Admin</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarNav" aria-controls="navbarNav"
		aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarNav">
		<ul class="navbar-nav mr-auto">

			<li class="nav-item"><a class="nav-link" href="/admin/users">Users</a>
			</li>

		</ul>

		<!-- Profile Dropdown -->
		<ul class="navbar-nav">
			<a href="/logout">Logout</a>
		</ul>
	</div>
</nav>
