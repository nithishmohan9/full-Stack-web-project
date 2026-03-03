<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,com.daomodel.Menu,java.util.List,com.daomodel.Restaurant,com.daomodel.User" %>
<%
    // Optional: show restaurant name if you stored it in session like in checkout.jsp
    List<Menu> menu = (List<Menu>)request.getAttribute("Menu_details");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Menu - SpiceSprint Food Delivery</title>
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
                    <input type="text" placeholder="Search dishes..." id="menuSearch">
                </div>
                <nav class="nav-menu">
                    <a href="getAllRestaurant" class="nav-link">Restaurants</a>
                    <a href="cart.jsp" class="nav-link">
                        <i class="fas fa-shopping-cart"></i>
                        Cart
                    </a>
                    <a href="login.jsp" class="nav-link">Logout</a>
                </nav>
            </div>
        </div>
    </header>
  
    <!-- Menu Section -->
    <section class="menu-section">
        <div class="container">
            <div class="menu-header">
                <h2>Best Menu for You</h2>
                <p>Freshly curated dishes from our kitchen.</p>
            </div>
        <%
        if (menu != null && !menu.isEmpty()) {
        %>
            <div class="menu-grid" id="menuGrid">
            <%
            for(Menu m : menu){
             %>
                <!-- Menu Item 1 -->
                <div class="menu-item">
                    <div class="menu-item-image">
                        <img src="<%= m.getImagePath() %>"  alt="<%= m.getItem_name() %>">
                    </div>
                    <div class="menu-item-info">
                        <h3><%= m.getItem_name() %></h3>
                        <p class="description"><%= m.getDescription() %></p>
                        <div class="menu-item-footer">
                            <span class="price">Rs. <%= m.getPrice() %></span>
                            <form action="cart_servlet" method="POST">
                    			<input type="hidden" name="menu_id" value="<%= m.getMenu_id() %>">
                    			<input type="hidden" name="itemName" value="<%= m.getItem_name() %>">
                   			 	<input type="hidden" name="restaurant_id" value="<%= m.getRestaurant_id() %>">
                    			<input type="hidden" name="price" value="<%= m.getPrice() %>">
                    			<input type="hidden" name="quantity" value="1">
                    			<input type="hidden" name="action" value="add">
                    			<button type="submit" class="add-to-cart-btn" data-item="<%= m.getItem_name() %>" data-price="<%= m.getPrice() %>"<i class="fas fa-plus">Add to cart</i></button>
                			</form>
                        </div>
                    </div>
                </div>
                <%
            } 
        %>
            </div>
        <%
        } else {
    %>
        <div class="menu-grid">
            <div class="empty-msg">No menu items available for this restaurant.</div>
        </div>
    <%
        }
    %>
        </div>
    </section>

    <!-- Floating Cart Button -->
    <div class="floating-cart">
        <a href="cart.jsp" class="cart-button">
            <i class="fas fa-shopping-cart"></i>
        </a>
    </div>

    <script src="script.js"></script>
</body>
</html>
