<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<header class="header">
    <img alt="logo" src="img/zip.png">
    <div class="ml-3">
        Dandallaya Dutha ${username != null ? username : 'Guest'}
    </div>
    <button class="btn btn-light ml-auto" onclick="logout()">Logout</button>
</header>


</body>
</html>