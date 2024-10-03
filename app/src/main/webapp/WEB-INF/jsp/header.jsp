<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<!--Main Navigation-->
<header>
  <!-- Jumbotron -->
  <div class="p-3 text-center bg-white border-bottom">
    <div class="container">
      <div class="row gy-3">
        <!-- Left elements -->
        <div class="col-lg-2 col-sm-4 col-4" style="margin-top:-5px">
          <a href="#" target="_blank" class="float-start">
            <img src="/img/zip.png" height="65" alt="Logo" />
          </a>
        </div>
        <!-- Left elements -->

        <!-- Center elements -->
        <div class="order-lg-last col-lg-5 col-sm-8 col-8">
          <div class="d-flex float-end">
            <!-- Check if user is logged in using JSTL -->
            <c:choose>
              <c:when test="${empty sessionScope.user}">
                <!-- If user is not logged in, show "Sign in" button -->
                <a href="login" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center">
                  <i class="fas fa-user-alt m-1 me-md-2"></i>
                  <p class="d-none d-md-block mb-0">Sign in</p>
                </a>
              </c:when>
              <c:otherwise>
                <!-- If user is logged in, display their username -->
                <a href="#" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center">
                  <i class="fas fa-user-alt m-1 me-md-2"></i>
                  <p class="d-none d-md-block mb-0">${sessionScope.user.username}</p>
                </a>
              </c:otherwise>
            </c:choose>

            <!-- Wishlist Button: Redirect to login if not logged in -->
            <c:choose>
              <c:when test="${empty sessionScope.user}">
                <a href="wishlist" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center">
                  <i class="fas fa-heart m-1 me-md-2"></i>
                  <p class="d-none d-md-block mb-0">Wishlist</p>
                </a>
              </c:when>
              <c:otherwise>
                <a href="wishlist.jsp" class="me-1 border rounded py-1 px-3 nav-link d-flex align-items-center">
                  <i class="fas fa-heart m-1 me-md-2"></i>
                  <p class="d-none d-md-block mb-0">Wishlist</p>
                </a>
              </c:otherwise>
            </c:choose>

            <!-- Cart Button: Redirect to login if not logged in -->
            <c:choose>
              <c:when test="${empty sessionScope.user}">
                <a href="cart" class="border rounded py-1 px-3 nav-link d-flex align-items-center">
                  <i class="fas fa-shopping-cart m-1 me-md-2"></i>
                  <p class="d-none d-md-block mb-0">My cart</p>
                </a>
              </c:when>
              <c:otherwise>
                <a href="cart" class="border rounded py-1 px-3 nav-link d-flex align-items-center">
                  <i class="fas fa-shopping-cart m-1 me-md-2"></i>
                  <p class="d-none d-md-block mb-0">My cart</p>
                </a>
              </c:otherwise>
            </c:choose>
          </div>
        </div>
        <!-- Center elements -->

        <!-- Right elements -->
        <div class="col-lg-5 col-md-12 col-12">
        <form action = "/products" method="get">
          <div class="input-group">
            <input type="search" id="form1" class="form-control" placeholder="Search" name="search" />
            <button type="submit" class="btn btn-primary">
              <i class="fas fa-search"></i>
            </button>
          </div>
          </form>
        </div>
        <!-- Right elements -->
      </div>
    </div>
  </div>
  <!-- Jumbotron -->

  <!-- Navbar -->
  <nav class="navbar navbar-expand-lg navbar-light bg-white">
    <div class="container">
      <!-- Toggle button -->
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarLeftAlignExample" aria-controls="navbarLeftAlignExample" aria-expanded="false" aria-label="Toggle navigation">
        <i class="fas fa-bars"></i>
      </button>
    </div>
  </nav>
  <!-- Navbar End -->
</header>
</body>
</html>
