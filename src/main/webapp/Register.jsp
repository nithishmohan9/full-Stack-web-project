<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register Food Delivery</title>
    <link rel="stylesheet" href="styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        body {
            font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
            margin: 0;
            padding: 0;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .register-container {
            background: white;
            border-radius: 16px;
            box-shadow: 0 20px 40px rgba(0,0,0,0.1);
            padding: 3rem;
            width: 100%;
            max-width: 420px;
            margin: 2rem;
        }

        .register-header {
            text-align: center;
            margin-bottom: 2.5rem;
        }

        .logo {
            font-size: 2.5rem;
            font-weight: 700;
            color: #e23744;
            margin-bottom: 0.5rem;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 0.5rem;
        }

        .register-header h2 {
            font-size: 1.5rem;
            color: #333;
            margin: 0;
            font-weight: 600;
        }

        .register-header p {
            color: #666;
            margin: 0.5rem 0 0 0;
            font-size: 0.95rem;
        }

        .form-group {
            margin-bottom: 1.5rem;
        }

        .form-group label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: 500;
            color: #333;
            font-size: 0.95rem;
        }

        .input-icon {
            position: relative;
        }

        .input-icon i {
            position: absolute;
            left: 1rem;
            top: 50%;
            transform: translateY(-50%);
            color: #666;
            font-size: 1rem;
        }

        .form-group input {
            width: 100%;
            padding: 1rem 1.25rem;
            border: 2px solid #e1e5e9;
            border-radius: 12px;
            font-size: 1rem;
            transition: all 0.3s ease;
            background: #f8f9fa;
        }

        .form-group input:focus {
            outline: none;
            border-color: #e23744;
            background: white;
            box-shadow: 0 0 0 4px rgba(226, 55, 68, 0.1);
        }

        .input-icon input {
            padding-left: 3rem;
            padding-right: 3rem;
        }

        .toggle-password {
            position: absolute;
            right: 1rem;
            top: 50%;
            transform: translateY(-50%);
            background: none;
            border: none;
            color: #666;
            cursor: pointer;
            font-size: 1rem;
            padding: 0.25rem;
            border-radius: 4px;
            transition: all 0.2s ease;
        }

        .toggle-password:hover {
            color: #e23744;
            background: rgba(226, 55, 68, 0.1);
        }

        .form-row {
            display: flex;
            gap: 1rem;
        }

        .form-row .form-group {
            flex: 1;
        }

        .register-btn {
            width: 100%;
            padding: 1rem;
            background: #e23744;
            color: white;
            border: none;
            border-radius: 12px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            margin-top: 1rem;
        }

        .register-btn:hover {
            background: #d12b37;
            transform: translateY(-2px);
            box-shadow: 0 8px 20px rgba(226, 55, 68, 0.3);
        }

        .register-btn:active {
            transform: translateY(0);
        }

        .form-footer {
            text-align: center;
            margin-top: 2rem;
            padding-top: 1.5rem;
            border-top: 1px solid #e1e5e9;
        }

        .form-footer p {
            color: #666;
            font-size: 0.9rem;
            margin: 0;
        }

        .form-footer a {
            color: #e23744;
            text-decoration: none;
            font-weight: 500;
        }

        .form-footer a:hover {
            text-decoration: underline;
        }

        @media (max-width: 480px) {
            .register-container {
                margin: 1rem;
                padding: 2rem;
            }
            
            .form-row {
                flex-direction: column;
                gap: 0;
            }
            
            .logo {
                font-size: 2rem;
            }
            
            .register-header h2 {
                font-size: 1.25rem;
            }
        }
    </style>
</head>
<body>
    <div class="register-container">
        <div class="register-header">
            <div class="logo">
                <i class="fas fa-bolt"></i>
                Register
            </div>
            <h2>Create Account</h2>
            <p>Start your food journey with us</p>
        </div>

        <form action="Register_servlet" method="POST" onsubmit="return validateForm()">
            <div class="form-row">
                <div class="form-group">
                    <label for="fullName">Full Name</label>
                    <div class="input-icon">
                        <i class="fas fa-user"></i>
                        <input type="text" id="name" name="name" placeholder="Full Name" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="username">Username</label>
                    <div class="input-icon">
                        <i class="fas fa-at"></i>
                        <input type="text" id="username" name="username" placeholder="Username" required>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label for="email">Email Address</label>
                <div class="input-icon">
                    <i class="fas fa-envelope"></i>
                    <input type="email" id="email" name="email" placeholder="Email Address" required>
                </div>
            </div>

            <div class="form-group">
                <label for="phone">Phone Number</label>
                <div class="input-icon">
                    <i class="fas fa-phone"></i>
                    <input type="tel" id="phone" name="phone" placeholder="Phone Number" required>
                </div>
            </div>

            <div class="form-group">
                <label for="address">Address</label>
                <div class="input-icon">
                    <i class="fas fa-map-marker-alt"></i>
                    <input type="text" id="address" name="address" placeholder="Address" required>
                </div>
            </div>
             <input type="hidden" name="role" value="customer">
            <div class="form-group">
                <label for="password">Password</label>
                <div class="input-icon">
                    <i class="fas fa-lock"></i>
                    <input type="password" id="password" name="password" placeholder="Password" required>
                    <button type="button" class="toggle-password" onclick="togglePasswordVisibility('password')">
                        <i class="fas fa-eye" id="password-eye"></i>
                    </button>
                </div>
            </div>

            <div class="form-group">
                <label for="confirmPassword">Confirm Password</label>
                <div class="input-icon">
                    <i class="fas fa-lock"></i>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Confirm Password" required>
                    <button type="button" class="toggle-password" onclick="togglePasswordVisibility('confirmPassword')">
                        <i class="fas fa-eye" id="confirmPassword-eye"></i>
                    </button>
                </div>
            </div>

            <button type="submit" class="register-btn">
                Create Account
            </button>
        </form>

        <div class="form-footer">
            <p>Already have an account? <a href="login.jsp">Sign In</a></p>
        </div>
    </div>

    <script>
        function togglePasswordVisibility(fieldId) {
            const passwordField = document.getElementById(fieldId);
            const eyeIcon = document.getElementById(fieldId + '-eye');
            
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                eyeIcon.classList.remove('fa-eye');
                eyeIcon.classList.add('fa-eye-slash');
            } else {
                passwordField.type = 'password';
                eyeIcon.classList.remove('fa-eye-slash');
                eyeIcon.classList.add('fa-eye');
            }
        }

        function validateForm() {
            const password = document.getElementById('password').value;
            const confirmPassword = document.getElementById('confirmPassword').value;
            
            // Check if passwords match
            if (password !== confirmPassword) {
                alert('Passwords do not match. Please make sure both passwords are the same.');
                return false;
            }
            
            // Check if confirm password is filled
            if (confirmPassword.trim() === '') {
                alert('Please confirm your password.');
                return false;
            }
            
            // Check if password is filled
            if (password.trim() === '') {
                alert('Please enter a password.');
                return false;
            }
            
            return true;
        }
    </script>
</body>
</html>
