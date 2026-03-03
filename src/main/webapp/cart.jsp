<%@page import="com.daoimpl.MenuImpl"%>
<%@page import="com.daomodel.cart_item"%>
<%@page import="com.daoimpl.cartImpl"%>
<%@page import="com.servlet.cart_servlet" %>
<%@page import="com.daomodel.Menu,java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="styles.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<meta charset="UTF-8">
<title>SpiceSprint-cart</title>

<style>
body {
    font-family: 'Inter', -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, sans-serif;
    margin: 0;
    padding: 0;
    background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
    min-height: 100vh;
}

/* HEADER */
.header {
    background: white;
    box-shadow: 0 2px 10px rgba(0,0,0,0.1);
    position: sticky;
    top: 0;
    z-index: 100;
}

.nav-wrapper {
    max-width: 1200px;
    margin: 0 auto;
    padding: 1rem 2rem;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.logo {
    font-size: 1.5rem;
    font-weight: 700;
    color: #e23744;
    display: flex;
    align-items: center;
    gap: 0.5rem;
}

.nav-menu {
    display: flex;
    gap: 2rem;
    align-items: center;
}

.nav-link {
    text-decoration: none;
    color: #333;
    font-weight: 500;
    transition: color 0.3s ease;
}

.nav-link:hover {
    color: #e23744;
}

.nav-link.active {
    color: #e23744;
}

/* MAIN WRAPPER */
.page {
    display: flex;
    justify-content: center;
    padding: 2rem 1rem;
}

.container {
    width: 100%;
    max-width: 1200px;
    display: grid;
    grid-template-columns: 1fr 350px;
    gap: 2rem;
}

/* CART LIST CARD */
.cart-card {
    background: white;
    border-radius: 16px;
    padding: 2rem;
    box-shadow: 0 4px 20px rgba(0,0,0,0.1);
}

.cart-header {
    margin-bottom: 2rem;
}

.cart-header h2 {
    font-size: 1.5rem;
    color: #333;
    margin: 0;
}

/* ITEM CARD */
.item-card {
    display: flex;
    gap: 1.5rem;
    padding: 1.5rem 0;
    border-bottom: 1px solid #e1e5e9;
}

.item-card:last-child {
    border-bottom: none;
}

.left {
    flex: 1;
}

.left h2 {
    margin: 0;
    font-size: 1.1rem;
    font-weight: 600;
    color: #333;
}

.price {
    font-size: 1.2rem;
    margin: 0.5rem 0;
    font-weight: 700;
    color: #e23744;
}

.unit-price {
    font-size: 0.9rem;
    color: #666;
}

.desc {
    color: #666;
    line-height: 1.4;
    font-size: 0.9rem;
    margin-top: 0.5rem;
}

.right {
    width: 110px;
    position: relative;
    text-align: center;
}

.right img {
    width: 100px;
    height: 100px;
    border-radius: 12px;
    object-fit: cover;
}

/* QUANTITY + REMOVE */
.qty-box {
    position: absolute;
    bottom: -10px;
    left: 50%;
    transform: translateX(-50%);
    display: flex;
    background: white;
    border-radius: 999px;
    overflow: hidden;
    border: 2px solid #e1e5e9;
}

.qty-box button {
    width: 32px;
    border: none;
    background: white;
    font-size: 18px;
    cursor: pointer;
    font-weight: bold;
    line-height: 1;
    transition: all 0.3s ease;
}

.qty-box button:hover:not(:disabled) {
    background: #e23744;
    color: white;
}

.qty-box button:disabled {
    opacity: 0.5;
    cursor: not-allowed;
}

.qty-box span {
    padding: 6px 12px;
    font-weight: 600;
    font-size: 14px;
}

.remove-btn {
    margin-top: 12px;
    background: none;
    color: #666;
    padding: 0.5rem;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-size: 13px;
    font-weight: 600;
    transition: all 0.3s ease;
}

.remove-btn:hover {
    background: #fee2e2;
    color: #e23744;
}

/* BILL BOX */
.bill-container {
    background: white;
    border-radius: 16px;
    box-shadow: 0 4px 20px rgba(0,0,0,0.1);
    padding: 2rem;
    position: sticky;
    top: 100px;
    height: fit-content;
}

.bill-container h3 {
    margin: 0 0 1.5rem 0;
    font-size: 1.3rem;
    font-weight: 700;
    color: #333;
}

.bill-row {
    display: flex;
    justify-content: space-between;
    margin: 1rem 0;
    font-size: 0.95rem;
    color: #666;
}

.bill-row span:last-child {
    font-weight: 500;
}

.bill-row.muted {
    color: #9ca3af;
}

.total-pay {
    margin-top: 1.5rem;
    padding-top: 1rem;
    border-top: 2px solid #333;
    display: flex;
    justify-content: space-between;
    font-weight: 700;
    font-size: 1.1rem;
    color: #333;
}

/* EMPTY STATE */
.empty-state {
    text-align: center;
    padding: 4rem 2rem;
    color: #666;
}

.empty-state i {
    font-size: 4rem;
    color: #e1e5e9;
    margin-bottom: 1rem;
}

/* Sticky bottom action bar */
.button-row-sticky {
    position: fixed;
    left: 0;
    right: 0;
    bottom: 0;
    background: white;
    box-shadow: 0 -6px 18px rgba(0, 0, 0, 0.06);
    padding: 1rem;
    z-index: 999;
    display: flex;
    justify-content: center;
}

.button-row-sticky .inner {
    width: 100%;
    max-width: 1200px;
    display: flex;
    gap: 1rem;
}

.button-row-sticky .btn-wrapper {
    flex: 1;
}

.checkout-btn, .addmore-btn {
    width: 100%;
    padding: 1rem;
    font-size: 1rem;
    border-radius: 12px;
    border: none;
    cursor: pointer;
    font-weight: 600;
    transition: all 0.3s ease;
}

.checkout-btn {
    background: #e23744;
    color: white;
}

.checkout-btn:hover {
    background: #d12b37;
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(226, 55, 68, 0.3);
}

.addmore-btn {
    background: white;
    color: #e23744;
    border: 2px solid #e23744;
}

.addmore-btn:hover {
    background: #e23744;
    color: white;
    transform: translateY(-2px);
    box-shadow: 0 8px 20px rgba(226, 55, 68, 0.3);
}

/* Responsive */
@media (max-width: 900px) {
    .container {
        grid-template-columns: 1fr;
    }

    .bill-container {
        position: static;
    }

    .item-card {
        flex-direction: row;
        gap: 1rem;
    }
}

@media (max-width: 640px) {
    .item-card {
        flex-direction: column;
    }

    .left, .right {
        width: 100%;
    }

    .right {
        margin-top: 1rem;
    }

    .header-inner {
        padding: 1rem;
    }
}
</style>
</head>
<body>

<%
    cartImpl cart = (cartImpl) session.getAttribute("cart");
    Integer restaurant_id = (Integer) session.getAttribute("restaurant_id");

    double grandTotal = 0.0;
    int totalItems = 0;

    if (cart != null && cart.getCartItems() != null && !cart.getCartItems().isEmpty()) {
        for (Object obj : cart.getCartItems().values()) {
            cart_item ci = (cart_item) obj;
            grandTotal += ci.getPrice() * ci.getQuantity();
            totalItems += ci.getQuantity();
        }
    }

    // >>> GST & DELIVERY CALCULATION <<<
    double gstRate = 0.05;              // 5%
    double gstAmount = grandTotal * gstRate;
    double deliveryCharge = (grandTotal > 0) ? 30.0 : 0.0;   // 30 only if there is an order
    double totalPay = grandTotal + gstAmount + deliveryCharge;
%>

<!-- HEADER -->
<header class="header">
    <div class="nav-wrapper">
        <div class="logo">
            
            Cart
        </div>
        <nav class="nav-menu">
            <a href="getAllRestaurant" class="nav-link">Restaurants</a>
            <a href="cart.jsp" class="nav-link active">
                <i class="fas fa-shopping-cart"></i>
                Cart
            </a>
            <a href="login.jsp" class="nav-link">Logout</a>
        </nav>
    </div>
</header>


<div class="page">
    <div class="container">

        <!-- LEFT: CART ITEMS -->
        <div class="cart-card">
            <%
                if (cart == null || cart.getCartItems() == null || cart.getCartItems().isEmpty()) {
            %>
                <div class="empty-state">
                    Your cart is empty. Add some tasty food from the restaurants.
                </div>
            <%
                } else {
                    for (Object obj : cart.getCartItems().values()) {
                        cart_item item = (cart_item) obj;
            %>
                <div class="item-card">
                    <div class="left">
                        <h2><%= item.getItem_name() %></h2>

                        <!-- TOTAL PRICE BASED ON QUANTITY -->
                        <div class="price">
                            ₹<%= item.getPrice() * item.getQuantity() %>
                        </div>
                        <div class="unit-price">
                            ₹<%= item.getPrice() %> x <%= item.getQuantity() %> item<%= (item.getQuantity() > 1 ? "s" : "") %>
                        </div>

                        <div class="desc">
                            Freshly prepared, served hot. Perfect for your meal.
                        </div>

                        <form action="cart_servlet" method="post">
                            <input type="hidden" name="menu_id" value="<%= item.getMenu_id() %>">
                            <input type="hidden" name="restaurant_id" value="<%= item.getRestaurant_id() %>">
                            <input type="hidden" name="action" value="remove">
                            <button type="submit" class="remove-btn">✕ Remove</button>
                        </form>
                    </div>

                    <div class="right">
                     <%
        MenuImpl mImpl = new MenuImpl();
        Menu menuItem = mImpl.getMenu(item.getMenu_id()); 
    %>
                        <img src="<%= menuItem.getImagePath() %>" alt="Food image" />

                        <div class="qty-box">
                            <form action="cart_servlet" method="post">
                                <input type="hidden" name="menu_id" value="<%=item.getMenu_id() %>">
                                <input type="hidden" name="restaurant_id" value="<%= item.getRestaurant_id()%>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="quantity" value="<%=item.getQuantity()-1 %>">
                                <button <%= (item.getQuantity() == 1) ? "disabled" : "" %>>-</button>
                            </form>

                            <span><%= item.getQuantity() %></span>

                            <form action="cart_servlet" method="post">
                                <input type="hidden" name="menu_id" value="<%=item.getMenu_id() %>">
                                <input type="hidden" name="restaurant_id" value="<%= item.getRestaurant_id()%>">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="quantity" value="<%=item.getQuantity()+1 %>">
                                <button>+</button>
                            </form>
                        </div>
                    </div>
                </div>
            <%
                    } // for
                } // else
            %>
        </div>

        <!-- RIGHT: BILL SUMMARY -->
        <div class="bill-container">
            <h3>Bill Summary</h3>

            <div class="bill-row">
                <span>Items (<%= totalItems %>)</span>
                <span>₹<%= String.format("%.2f", grandTotal) %></span>
            </div>

            <div class="bill-row">
                <span>GST (@ 5%)</span>
                <span>₹<%= String.format("%.2f", gstAmount) %></span>
            </div>

            <div class="bill-row">
                <span>Delivery</span>
                <span>₹<%= String.format("%.2f", deliveryCharge) %></span>
            </div>

            <div class="total-pay">
                <span>To Pay</span>
                <span>₹<%= String.format("%.2f", totalPay) %></span>
            </div>
        </div>
    </div>
</div>

<!-- STICKY BOTTOM BUTTON BAR -->
<div class="button-row-sticky" role="region" aria-label="Cart actions">
    <div class="inner">
        <div class="btn-wrapper">
            <form action="checkout.jsp" method="post">
    <input type="hidden" name="restaurant_id" value="<%=session.getAttribute("restaurant_id")%>">
    <button type="submit" class="checkout-btn">
        Proceed to Checkout (₹<%= String.format("%.2f", totalPay) %>)
    </button>
</form>
            
        </div>

        <div class="btn-wrapper">
            <form action="menu" method="get">
                <input type="hidden" name="restaurant_id"
                       value="<%=session.getAttribute("restaurant_id") %>">
                <button class="addmore-btn" type="submit">
                    Add More Items / Restaurants
                </button>
            </form>
        </div>
    </div>
</div>

</body>
</html>
