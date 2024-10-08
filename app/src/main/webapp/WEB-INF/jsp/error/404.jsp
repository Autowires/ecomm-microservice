<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page Not Found</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .error-page {
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container error-page">
        <div >
            <h1 class="display-1 mb-4">404</h1>
            <p class="lead">Page Not Found: The requested resource could not be found.</p>
            <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Go to Home</a>
        </div>
    </div>
</body>
</html>