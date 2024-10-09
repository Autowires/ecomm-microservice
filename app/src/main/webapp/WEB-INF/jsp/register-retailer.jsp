<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Retailer Registration</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 500px;
            margin-top: 50px;
        }

        .form-group {
            margin-bottom: 1rem;
        }

        .input-group-append .btn {
            margin-left: 5px;
        }

        .invalid-feedback {
            display: none;
        }

        .is-invalid ~ .invalid-feedback {
            display: block;
        }

        #otp-section {
            display: none;
            margin-top: 10px;
        }

        #incorrect-password-message {
            display: none;
        }
    </style>
    <script>
        let otpVerified = false;

        async function verifyEmail() {
            const email = document.getElementById("email").value;
            const response = await fetch(`/register/verify-email?email=` + encodeURIComponent(email), {
                method: "POST"
            });

            const messageElement = document.getElementById("message");
            if (response.ok) {
                document.getElementById("otp-section").style.display = 'block';
                messageElement.textContent = "OTP has been sent to your email. Please check your inbox.";
                messageElement.className = "alert alert-success";
                messageElement.style.display = "block";
            } else {
                const error = await response.text();
                messageElement.textContent = "Error: " + error;
                messageElement.className = "alert alert-danger";
                messageElement.style.display = "block";
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

            const messageElement = document.getElementById("message");
            const result = await response.text();

            if (response.ok) {
                otpVerified = true;
                document.getElementById("otp-section").style.display = 'none'; // Hide OTP section
                document.getElementById("registerButton").disabled = false;
                document.getElementById("verifyButton").disabled = true;
                messageElement.textContent = "OTP verified successfully!";
                messageElement.className = "alert alert-success";
                messageElement.style.display = "block";
            } else {
                messageElement.textContent = "Error: " + result;
                messageElement.className = "alert alert-danger";
                messageElement.style.display = "block";
            }
        }

        function validateForm(event) {
            const form = event.target;
            let isValid = true;

            Array.from(form.elements).forEach(input => {
                if (!input.validity.valid) {
                    input.classList.add('is-invalid');
                    isValid = false;
                } else {
                    input.classList.remove('is-invalid');
                }
            });

            // Client-side password validation
            const password = document.getElementById("password").value;
            const confirmPassword = document.getElementById("confirmPassword").value;
            const passwordMessage = document.getElementById("incorrect-password-message");

            if (password !== confirmPassword) {
                passwordMessage.style.display = "block"; // Show error message
                isValid = false;
            } else {
                passwordMessage.style.display = "none"; // Hide error message
            }

            if (!isValid) {
                event.preventDefault();
            }
        }
    </script>
</head>

<body>
    <div class="container mt-5">
        <h2 class="text-center">Retailer Registration</h2>

        <div id="message" class="alert" style="display:none;"></div>

        <form action="/register-retailer" method="post" class="mb-4" novalidate onsubmit="validateForm(event)">
            <div class="form-group">
                <label for="username">Username</label>
                <input type="text" class="form-control" name="username" required>
                <div class="invalid-feedback">Please provide a username.</div>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <div class="input-group">
                    <input type="email" class="form-control" name="email" id="email" required>
                    <div class="input-group-append">
                        <button type="button" class="btn btn-secondary" id="verifyButton" onclick="verifyEmail()">Verify</button>
                    </div>
                </div>
                <div class="invalid-feedback">Please provide a valid email.</div>
            </div>

            <!-- OTP section -->
            <div id="otp-section">
                <label for="otp">Enter OTP</label>
                <input type="text" id="otp" class="form-control" required>
                <div class="invalid-feedback">Please provide the OTP sent to your email.</div>
                <button class="btn btn-success mt-2" type="button" onclick="verifyOtp()">Submit OTP</button>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" class="form-control" id="password" name="password" required minlength="6">
                <div class="invalid-feedback">Password must be at least 6 characters.</div>
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <input type="password" class="form-control" id="confirmPassword" required>
                <div class="invalid-feedback">Please confirm your password.</div>
                <div id="incorrect-password-message" class="text-danger">Passwords do not match.</div>
            </div>

            <div class="form-group">
                <label for="contactNo">Contact Number</label>
                <input type="tel" class="form-control" name="contactNo" required pattern="\d{10}">
                <div class="invalid-feedback">Please provide a valid 10-digit contact number.</div>
            </div>

            <div class="form-group">
                <label for="GSTIN">GSTIN Number</label>
                <input type="text" class="form-control" id="GSTIN" name="GSTIN" required>
                <div class="invalid-feedback">Please provide a valid GST Number.</div>
            </div>
            <div class="form-group">
                <label for="shopName">shop Name</label>
                <input type="text" class="form-control" id="shopName" name="shopName" required>
                <div class="invalid-feedback">Please provide a valid GST Number.</div>
            </div>

            <div class="form-group">
                <label for="pannumber">Pan Number</label>
                <input type="text" class="form-control" id="pannumber" name="pannumber" required pattern="[A-Z]{5}[0-9]{4}[A-Z]{1}">
                <div class="invalid-feedback">Please provide a valid Pan Number.</div>
            </div>

            <div class="form-group">
                <label for="address">Shop Address</label>
                <textarea class="form-control" id="address" name="address" required></textarea>
                <div class="invalid-feedback">Please provide a shop address.</div>
            </div>

            <button type="submit" class="btn btn-primary" id="registerButton" disabled>Register</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>

</html>
