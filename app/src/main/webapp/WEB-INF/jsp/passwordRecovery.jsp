<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .forgot-password-container {
            max-width: 400px;
            margin: 100px auto;
            padding: 20px;
            background: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .error-message {
            color: red;
            font-size: 0.875em;
        }
        .btn-custom {
            background-color: #007bff;
            color: white;
        }
        .btn-custom:hover {
            background-color: #0056b3;
            color: white;
        }
        .hidden {
            display: none;
        }
    </style>
    <!-- jQuery for AJAX -->
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<div class="forgot-password-container">
    <h2 class="text-center mb-4">Forgot Password</h2>

    <!-- Email Submission Form -->
    <form id="emailForm" action="javascript:void(0);">
        <div class="form-group">
            <label for="email">Email:</label>
            <input id="email" name="email" class="form-control" required>
        </div>
        <p id="emailError" class="error-message text-center"></p>
        <button type="submit" class="btn btn-custom btn-block mt-2">Verify Email</button>
    </form>

    <!-- OTP Verification Form -->
    <div id="otpSection" class="hidden">
        <form id="otpForm" action="javascript:void(0);">
            <div class="form-group mt-3">
                <label for="otp">Enter OTP:</label>
                <input id="otp" name="otp" class="form-control" required>
            </div>
            <p id="otpError" class="error-message text-center"></p>
            <button type="submit" class="btn btn-custom btn-block mt-2">Verify OTP</button>
        </form>
    </div>

    <!-- Password Reset Form -->
    <div id="resetPasswordSection" class="hidden">
        <form id="resetPasswordForm" action="javascript:void(0);">
            <div class="form-group mt-3">
                <label for="newPassword">New Password:</label>
                <input type="password" id="newPassword" name="newPassword" class="form-control" required>
            </div>
            <div class="form-group">
                <label for="confirmPassword">Confirm Password:</label>
                <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
            </div>
            <p id="passwordError" class="error-message text-center"></p>
            <button type="submit" class="btn btn-custom btn-block mt-2">Reset Password</button>
        </form>
    </div>
</div>

<!-- AJAX and jQuery to handle form submissions without page reloads -->
<script>
    $(document).ready(function() {
        // Email submission
        $('#emailForm').on('submit', function(e) {
            e.preventDefault();
            const email = $('#email').val();
            $.ajax({
                url: '/forgotpassword',
                type: 'POST',
                data: { email: email },
                success: function(response) {
                    if (response.success) {
                        $('#emailForm').hide();
                        $('#otpSection').removeClass('hidden'); // Show OTP section only if OTP was sent successfully
                    } else {
                        $('#emailError').text(response.errorMessage); 
                    }
                }
            });
        });

        // OTP submission
        $('#otpForm').on('submit', function(e) {
            e.preventDefault();
            const otp = $('#otp').val();
            const email = $('#email').val();

            $.ajax({
                url: '/forgotpassword/verify-otp',
                type: 'POST',
                contentType: 'application/json',  
                data: JSON.stringify({ otp: otp, email: email }),  
                success: function(response) {
                    if (response.success) {
                        $('#otpSection').hide();
                        $('#resetPasswordSection').removeClass('hidden');
                    } else {
                        $('#otpError').text(response.otpErrorMessage); 
                    }
                }
            });
        });

        $('#resetPasswordForm').on('submit', function(e) {
            e.preventDefault();
            const newPassword = $('#newPassword').val();
            const confirmPassword = $('#confirmPassword').val();
            const email = $('#email').val();
            
            // Password validation for letters and numbers
            const passwordPattern = /^(?=.*[a-zA-Z])(?=.*\d).+$/; // At least one letter and one number
            
            if (!passwordPattern.test(newPassword)) {
                $('#passwordError').text('Password must contain at least one letter and one number.'); 
                return; // Stop submission if the password is invalid
            } else {
                $('#passwordError').text(''); // Clear previous error message
            }

            if (newPassword !== confirmPassword) {
                $('#passwordError').text('Passwords do not match'); 
            } else {
                $.ajax({
                    url: '/updatePassword',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({ Password: newPassword, email: email }),
                    success: function(response) {
                        if (response.success) {
                            window.location.href = '/login'; 
                        } else {
                            $('#passwordError').text('Failed to update the password.');
                        }
                    },
                    error: function(response) {
                        $('#passwordError').text('An error occurred. Please try again.');
                    }
                });
            }
        });
    });
</script>

</body>
</html>
