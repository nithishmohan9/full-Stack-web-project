<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.daomodel.Restaurant,com.daomodel.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SpiceSprint - Food Delivery App</title>
    <link rel="stylesheet" href="styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
    <!-- Header -->
    <header class="header">
        <div class="container">
            <div class="nav-wrapper">
                <div class="logo">
                 <span>SpiceSprint</span>
                </div>
                <div class="search-bar">
                    <i class="fas fa-search"></i>
                    <input type="text" placeholder="Search restaurants..." id="searchInput">
                </div>
                <nav class="nav-menu">
                    <a href="getAllRestaurant" class="nav-link active">Restaurants</a>
                    <a href="cart.jsp" class="nav-link">
                        <i class="fas fa-shopping-cart"></i>
                        Cart
                    </a>
                    <a href="login.jsp" class="nav-link">Logout</a>
                </nav>
            </div>
        </div>
    </header>

    <!-- Main Content -->
    <main class="main-content">
        <div class="container">
            <div class="section-header">
                <h2><i class="fas fa-utensils"></i> Available Restaurants</h2>
            </div>
            
            <div class="restaurants-grid" id="restaurantsGrid">
                <!-- Restaurant Cards -->


                <%
        List<Restaurant> restaurantList = (List<Restaurant>) request.getAttribute("Restaurant_list");

        if (restaurantList != null && !restaurantList.isEmpty()) {
            for (Restaurant r : restaurantList) {

                String img = r.getimage_path();
                String finalImage;

                // HANDLE IMAGE SOURCE LOGIC
                if (img == null || img.trim().isEmpty()) {
                    // DB has no path -> use default
                    finalImage = request.getContextPath() + "/images/default.jpg";

                } else if (img.startsWith("http://") || img.startsWith("https://")) {
                    // DB has full external URL -> use directly
                    finalImage = img.trim();

                } else {
                    // DB contains relative path like 'images/default.jpg'
                    finalImage = request.getContextPath() + "/" + img.trim();
                }
    %>
    
    			<a href="menu?restaurant_id=<%= r.getrestaurant_id() %>">
    				<div class="restaurant-card" data-restaurant="vidyarthi-bhavan">
                    <div class="restaurant-image">
                        <img src="<%= finalImage %>" alt="Vidyarthi Bhavan">
                    </div>
                    <div class="restaurant-info">
                        <h3><%= r.getname() %></h3>
                        <p class="cuisine"><%= r.getcusine_type() %></p>
                        <div class="restaurant-meta">
                            <div class="rating">
                                <i class="fas fa-star"></i>
                                <span><%= r.getrating() %></span>
                            </div>
                            <div class="delivery-time">
                                <i class="fas fa-clock"></i>
                                <span><%= r.getestimated_time_arival() %></span>
                            </div>
                        </div>
                    </div>
                </div>
    			</a>

                
<%
            }
        } else {
    %>

        <p class="empty-msg">No restaurants available.</p>

    <%
        }
    %>
            </div>
        </div>
    </main>

    <script src="script.js"></script>
</body>
</html>
