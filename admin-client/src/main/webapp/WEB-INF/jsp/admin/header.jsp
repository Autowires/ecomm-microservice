<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">Zip - Admin</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" 
            aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/admin">Users</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/admin/reviewRequest">ReviewRequests</a>
            </li>
        </ul>

        <!-- Profile Dropdown -->
        <ul class="navbar-nav ml-auto align-items-center">
            <li class="nav-item">
                <span class="text-white mr-2">Welcome, ${principal.name}</span>
            </li>
            <li class="nav-item">
                <img src="${principal.picture}" alt="Profile Picture" class="rounded-circle" 
                     style="width: 40px; height: 40px;">
            </li>
            <li class="nav-item ml-3">
                <a href="/logout" class="btn btn-outline-light">Logout</a>
            </li>
        </ul>
    </div>
</nav>
