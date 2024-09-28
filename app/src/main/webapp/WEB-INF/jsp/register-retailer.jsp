<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script>
        async function verifyEmail() {
            const email = document.getElementById("email").value;
            const response = await fetch(`/register/verify-email?email=`+encodeURIComponent(email), {
            	method: "POST"
            });
            if (response.ok) {
                const result = await response.text();
                document.getElementById("otp-section").style.display = 'block';
            } else {
                const error = await response.text();
                alert("Error: " + error);
            }
        }

        async function verifyOtp() {
            const email = document.getElementById("email").value;
            const otp = document.getElementById("otp").value;

            const response = await fetch('/register/verify-email', {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, otp })
            });

            const result = await response.text();
            alert(result);
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2>Retailer registration</h2>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger">${errorMessage}</div>
        </c:if>
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success">${successMessage}</div>
        </c:if>

        <form action="/register-retailer" method="post" class="mb-4">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" name="username" required>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" class="form-control" name="email" id="email" required>
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" name="password" required>
            </div>
              <div class="form-group">
                <label for="contactNo">Contact number</label>
                <input type="number" class="form-control" name="contactNo" required>
            </div>
            <div class="form-group">
                <label for="shopName">Shop name</label>
                <input type="text" class="form-control" name="shopName" required>
            </div>
            <div class="form-group">
                <label for="GSTIN">GST Number</label>
                <input type="text" class="form-control" name="GSTIN" required>
            </div>
            <div class="form-group">
                <label for="pannumber">Pan number</label>
                <input type="text" class="form-control" name="pannumber" required>
            </div>
            <div class="form-group">
                <label for="address">Address</label>
                <input type="text" class="form-control" name="address" required>
            </div>
            <button type="submit" class="btn btn-primary">Register</button>
        </form>

        <button class="btn btn-secondary" onclick="verifyEmail()">Verify Email</button>

        <div class="mt-4" id="otp-section" style="display:none;">
            <h3>Verify OTP</h3>
            <div class="form-group">
                <label for="otp">Enter OTP</label>
                <input type="text" id="otp" class="form-control" required>
            </div>
            <button class="btn btn-success" onclick="verifyOtp()">Submit OTP</button>
        </div>
    </div>

</body>
</html>
