<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Products</title>
   <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
    <!-- FontAwesome for icons -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</head>
<body>
<!--Main Navigation-->
<%@include file="header.jsp"%>
<header>
  <!-- Heading -->
  <div class="bg-primary mb-4">
  <div class="container py-4">
    <div class="d-flex justify-content-between align-items-center">
      <h3 class="text-white mt-2">Products</h3>
      <!-- Breadcrumb and Button -->
      <div>
        <nav class="d-flex mb-2">
          <h6 class="mb-0">
            <a href="/products" class="text-white-50">Home</a>
          </h6>
        </nav>
      </div>
      <button style="background-color: red; border: none; padding: 10px 20px; border-radius: 5px;">
        <a href="/products" style="color: #ffffff; text-decoration: none; font-weight: bold;">Back To All Products</a>
      </button>
      <!-- Breadcrumb and Button -->
    </div>
  </div>
</div>

  <!-- Heading -->
</header>

<!-- sidebar + content -->
<section>
  <div class="container">
    <div class="row">
      <!-- Sidebar -->
      <div class="col-lg-3">
        <div class="card mb-5">
          <div class="accordion" id="accordionExample">
            <div class="accordion-item">
              <h2 class="accordion-header" id="headingOne">
                <button class="accordion-button text-dark bg-light" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                  Categories
                </button>
              </h2>
              <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne">
                <div class="accordion-body">
                  <ul class="list-unstyled">
                    <!-- Dynamic Categories -->
                    <c:forEach var="category" items="${categories}">
                      <li><a href="/products?category=${category}" class="text-dark">${category}</a></li>
                    </c:forEach>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- Sidebar End -->

      <!-- content -->
      <div class="col-lg-9">
        <header class="d-sm-flex align-items-center border-bottom mb-4 pb-3">
          <strong class="d-block py-2">${products.size()} Items found</strong>
        </header>

         <!-- Display All Products -->
        <div class="row">
          <c:forEach var="product" items="${products}">
            <div class="col-lg-4 col-md-6 col-sm-6 d-flex">
              <!-- Link to productinfo.jsp with product ID -->
              <a href="/products/${product.id}" class="text-decoration-none w-100">
                <div class="card w-100 my-2 shadow">
                  <img src="${product.imageUrl}" class="card-img-top" style="aspect-ratio: 1 / 1" />
                  <div class="card-body d-flex flex-column">
                    <h5 class="card-title text-dark"> ₹${product.price}</h5>
                    <h4 class="card-title text-dark">${product.title}</h4>
                    <p class="card-text text-dark">${product.description}</p>
                  </div>
                </div>
              </a>
            </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
</section>

<%@include file="footer.jsp" %>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
