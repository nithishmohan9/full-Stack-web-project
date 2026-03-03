<%@ page import="java.util.*" %>
<%@ page import="com.daoimpl.cartImpl" %>
<%@ page import="com.daomodel.cart_item" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    final double GST_RATE = 0.05;
    final double DELIVERY_CHARGE = 30.0;

    cartImpl cart = (cartImpl) session.getAttribute("cart");

    Integer restaurantId = (Integer) session.getAttribute("restaurant_id");
    String restaurantName = (String) session.getAttribute("restaurant_name");

    String userName = session.getAttribute("user_name") != null ? (String) session.getAttribute("user_name") : "";
    String userPhone = session.getAttribute("user_phone") != null ? (String) session.getAttribute("user_phone") : "";
    String userAddress = session.getAttribute("user_address") != null ? (String) session.getAttribute("user_address") : "";

    double subtotal = 0.0;
    
    // ETA text (from session or default)
    String etaText = (String) session.getAttribute("current_eta");
    if (etaText == null || etaText.trim().isEmpty()) {
        etaText = "30–45 mins";
    }
%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect(request.getContextPath() + "/Login_servlet");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title> SpiceSprint-Checkout</title>

  <!-- Optional Google Font -->
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">

  <style>
    * {
      box-sizing: border-box;
    }

    body {
      font-family: "Poppins", Arial, Helvetica, sans-serif;
      background: linear-gradient(135deg, #ffecd2, #fcb69f);
      margin: 0;
      padding: 24px 12px;
    }

    .checkout-wrapper {
      max-width: 1100px;
      margin: 0 auto;
    }

    .checkout-header {
      color: #fff;
      margin-bottom: 16px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      gap: 12px;
    }

    .checkout-header h1 {
      margin: 0;
      font-size: 26px;
      font-weight: 600;
    }

    .restaurant-tag {
      background: rgba(255, 255, 255, 0.18);
      border-radius: 999px;
      padding: 6px 14px;
      font-size: 13px;
      display: inline-flex;
      align-items: center;
      gap: 6px;
    }

    .restaurant-tag span {
      font-weight: 500;
    }

    .checkout-container {
      background: #ffffff;
      border-radius: 18px;
      box-shadow: 0 10px 26px rgba(0, 0, 0, 0.18);
      padding: 20px 22px;
      display: flex;
      gap: 22px;
      flex-wrap: wrap;
    }

    .left-col {
      flex: 1.4;
      min-width: 260px;
      border-right: 1px solid #f1f1f1;
      padding-right: 18px;
    }

    .right-col {
      flex: 1;
      min-width: 260px;
      padding-left: 4px;
    }

    @media (max-width: 800px) {
      .checkout-container {
        flex-direction: column;
      }
      .left-col {
        border-right: none;
        border-bottom: 1px solid #f1f1f1;
        padding-right: 0;
        padding-bottom: 16px;
        margin-bottom: 8px;
      }
      .right-col {
        padding-left: 0;
      }
    }

    h3.section-title {
      margin: 0 0 8px;
      font-size: 18px;
      font-weight: 600;
      color: #333;
    }

    .section-note {
      color: #999;
      font-size: 13px;
      margin-bottom: 8px;
    }

    /* Items list */
    .items {
      margin-top: 6px;
    }

    .empty-cart {
      padding: 18px;
      border-radius: 10px;
      background: #fff7f0;
      color: #777;
      font-size: 14px;
      text-align: center;
    }

    .item-card {
      display: flex;
      justify-content: space-between;
      gap: 10px;
      padding: 10px 0;
      border-bottom: 1px solid #f3f3f3;
    }

    .item-card:last-child {
      border-bottom: none;
    }

    .item-left {
      flex: 1;
    }

    .item-name {
      font-size: 15px;
      font-weight: 500;
      color: #333;
      margin-bottom: 2px;
    }

    .item-meta {
      font-size: 12px;
      color: #777;
    }

    .item-qty-pill {
      display: inline-block;
      padding: 2px 8px;
      border-radius: 999px;
      background: #ffefe0;
      color: #ff7a1a;
      font-size: 11px;
      margin-right: 6px;
      font-weight: 500;
    }

    .item-right {
      text-align: right;
      font-size: 14px;
      font-weight: 500;
      color: #333;
      min-width: 80px;
    }

    /* Summary box */
    .summary {
      margin-top: 10px;
      padding: 14px 14px 12px;
      background: #fff7f0;
      border-radius: 12px;
    }

    .summary-row {
      display: flex;
      justify-content: space-between;
      margin: 4px 0;
      font-size: 14px;
      color: #555;
    }

    .summary-row.total {
      margin-top: 6px;
      font-size: 16px;
      font-weight: 700;
      color: #222;
    }

    .summary-row.total .value {
      color: #ff7a1a;
    }

    .summary-label {
      font-weight: 400;
    }

    .summary-value {
      font-weight: 500;
    }

    /* Right column form */
    form {
      margin: 0;
    }

    .form-group {
      margin-bottom: 12px;
    }

    label {
      display: block;
      margin-bottom: 6px;
      font-size: 13px;
      font-weight: 600;
      color: #444;
    }

    input[type="text"],
    textarea {
      width: 100%;
      padding: 10px 11px;
      border-radius: 10px;
      border: 1px solid #ddd;
      font-family: "Poppins", Arial, sans-serif;
      font-size: 13px;
      outline: none;
      transition: border 0.18s ease, box-shadow 0.18s ease;
      resize: vertical;
    }

    input[type="text"]:focus,
    textarea:focus {
      border-color: #ff7a1a;
      box-shadow: 0 0 0 2px rgba(255, 122, 26, 0.2);
    }

    .two-col {
      display: flex;
      gap: 10px;
      flex-wrap: wrap;
    }

    .two-col > div {
      flex: 1;
      min-width: 140px;
    }

    /* PAYMENT OPTIONS (new) */
    .payment-options {
      display: flex;
      flex-wrap: wrap;
      gap: 10px;
      margin-top: 4px;
    }

    .pay-option {
      display: flex;
      align-items: center;
      gap: 10px;
      border: 2px solid #d1d5db;
      padding: 10px 16px;
      border-radius: 12px;
      cursor: pointer;
      background: #ffffff;
      transition: 0.25s ease;
      font-size: 13px;
      font-weight: 500;
      color: #374151;
    }

    .pay-option img {
      width: 32px;
      height: 32px;
      object-fit: contain;
    }

    .pay-option input[type="radio"] {
      display: none;
    }

    .pay-option.selected {
      border-color: #0a8f38;
      background: rgba(10, 143, 56, 0.08);
      box-shadow: 0 0 12px rgba(10, 143, 56, 0.45);
      transform: scale(1.05);
      color: #065f2a;
    }

    .small-note {
      font-size: 11px;
      color: #999;
      margin-top: 3px;
    }

    /* Buttons */
    .actions {
      margin-top: 16px;
      display: flex;
      gap: 10px;
      flex-wrap: wrap;
    }

    .btn {
      display: inline-flex;
      align-items: center;
      justify-content: center;
      padding: 10px 18px;
      border-radius: 999px;
      border: none;
      font-size: 14px;
      font-weight: 600;
      cursor: pointer;
      transition: transform 0.15s ease, box-shadow 0.15s ease, background 0.18s ease;
      text-decoration: none;
      white-space: nowrap;
    }

    .btn-primary {
      background: #0a8f38;
      color: #fff;
      box-shadow: 0 7px 16px rgba(10, 143, 56, 0.45);
    }

    .btn-primary:hover {
      background: #087530;
    }

    .btn-secondary {
      background: #ffffff;
      color: #3498db;
      border: 1px solid #d0e7ff;
    }

    .btn-secondary:hover {
      background: #f1f7ff;
    }

    .btn:active {
      transform: scale(0.97);
    }

    .footer-note {
      margin-top: 8px;
      font-size: 11px;
      color: #888;
    }
  </style>
</head>
<body>
  <div class="checkout-wrapper">
    <div class="checkout-header">
      <h1>Checkout</h1>
      <div class="restaurant-tag">
        <span>Restaurant:</span>
        <% if (restaurantName != null && !restaurantName.trim().isEmpty()) { %>
          <strong><%= restaurantName %></strong>
          <% if (restaurantId != null) { %>
            <span style="opacity:0.7;">#<%=restaurantId %></span>
          <% } %>
        <% } else { %>
          <strong><%= restaurantId != null ? "ID: "+restaurantId : "Unknown" %></strong>
        <% } %>
      </div>
    </div>

    <div class="checkout-container">
      <!-- LEFT: ITEMS + SUMMARY -->
      <div class="left-col">
        <h3 class="section-title">Order Items</h3>
        <div class="section-note">Review your items before placing the order.</div>

        <div class="items">
          <% if (cart == null || cart.getCartItems().isEmpty()) { %>
            <div class="empty-cart">Your cart is empty. Add something tasty before checking out.</div>
          <% } else {
               for (cart_item it : cart.getCartItems().values()) {
                   double lineTotal = it.getPrice() * it.getQuantity();
                   subtotal += lineTotal;
          %>
            <div class="item-card">
              <div class="item-left">
                <div class="item-name"><%= it.getItem_name() %></div>
                <div class="item-meta">
                  <span class="item-qty-pill">Qty: <%= it.getQuantity() %></span>
                  <span>Rs. <%= it.getPrice() %> each</span>
                </div>
              </div>
              <div class="item-right">
                Rs. <%= String.format("%.2f", lineTotal) %>
              </div>
            </div>
          <%   } // end for
             } // end else
          %>
        </div>

        <!-- ETA Block -->
        <div style="margin-top:10px; margin-bottom:8px; padding:10px 12px; border-radius:10px; background:#fdf3e8; display:flex; justify-content:space-between; align-items:center;">
          <div>
            <div style="font-size:12px; color:#777;">Estimated delivery time</div>
            <div style="font-size:15px; font-weight:600; color:#ff7a1a;"><%= etaText %></div>
          </div>
          <div style="font-size:11px; color:#999; max-width:140px; text-align:right;">
            Based on restaurant location and current order load.
          </div>
        </div>
        <!-- End ETA Block -->

        <div class="summary">
          <%
            double gst = subtotal * GST_RATE;
            double delivery = (subtotal > 0) ? DELIVERY_CHARGE : 0.0;
            double total = subtotal + gst + delivery;
          %>
          <div class="summary-row">
            <div class="summary-label">Subtotal</div>
            <div class="summary-value">Rs. <%= String.format("%.2f", subtotal) %></div>
          </div>
          <div class="summary-row">
            <div class="summary-label">GST (@ <%= (int)(GST_RATE * 100) %>%)</div>
            <div class="summary-value">Rs. <%= String.format("%.2f", gst) %></div>
          </div>
          <div class="summary-row">
            <div class="summary-label">Delivery</div>
            <div class="summary-value">Rs. <%= String.format("%.2f", delivery) %></div>
          </div>
          <div class="summary-row total">
            <div class="summary-label">Total Payable</div>
            <div class="summary-value value">Rs. <%= String.format("%.2f", total) %></div>
          </div>
        </div>
      </div>

      <!-- RIGHT: DELIVERY & PAYMENT -->
      <div class="right-col">
        <h3 class="section-title">Delivery & Payment</h3>
        <div class="section-note">We’ll use these details to deliver your order.</div>

       <form action="<%=request.getContextPath()%>/checkout_servlet" method="post" id="placeOrderForm">

          <div class="form-group two-col">
    <div>
        <label for="cust_name">Name</label>
        <input type="text" id="cust_name" name="cust_name"
               placeholder="Enter your full name" 
               required />
    </div>

    <div>
        <label for="cust_phone">Phone</label>
        <input type="text" id="cust_phone" name="cust_phone"
               placeholder="Enter phone number"
               minlength="10" maxlength="10"
               required />
    </div>
</div>

<div class="form-group">
    <label for="cust_address">Delivery Address</label>
    <textarea id="cust_address" name="cust_address" rows="4"
              placeholder="Enter delivery address"
              required></textarea>
</div>

          <div class="form-group">
            <label>Payment method</label>
            <div class="payment-options">
              <label class="pay-option">
                <input type="radio" name="payment_method" value="COD" />
                 <img src="<%=request.getContextPath()%>/images/CashOnDelivery.png" alt="Cah On Delivery" />
                <span>Cash on Delivery</span>
              </label>

              <label class="pay-option">
                <input type="radio" name="payment_method" value="GPAY" />
                <img src="<%=request.getContextPath()%>/images/Gpay.jpg" alt="Google Pay" />
                <span>Google Pay</span>
              </label>

              <label class="pay-option">
                <input type="radio" name="payment_method" value="PHONEPE" />
                <img src="<%=request.getContextPath()%>/images/phonepe.jpg" alt="PhonePe" />
                <span>PhonePe</span>
              </label>

              <label class="pay-option">
                <input type="radio" name="payment_method" value="PAYTM" />
                <img src="<%=request.getContextPath()%>/images/Paytm.jpg" alt="Paytm" />
                <span>Paytm</span>
              </label>
            </div>
            <div class="small-note">Choose a payment option. You’ll confirm it at delivery / through your UPI app.</div>
          </div>

          <input type="hidden" name="restaurant_id" value="<%= restaurantId != null ? restaurantId : "" %>" />
          
     
          

          <div class="actions">
         <button class="btn btn-primary" type="button" onclick="submitCheckout()">Place Order</button>
         
         
         
         <div id="redirect-timer"
     style="display:none;text-align:center;margin-top:15px;font-size:16px;font-weight:600;color:#ff5722;">
    Redirecting... <span id="countdown">5</span> sec
</div>
         

            <a href="cart.jsp" class="btn btn-secondary">Back to Cart</a>
          </div>

          <div class="footer-note">
            Note: The server will re-calculate totals and use the session cart to create the order.
          </div>
        </form>
      </div>
    </div>
  </div>

  <!-- JS: highlight selected payment option -->
  <script>
    document.querySelectorAll('.pay-option').forEach(option => {
      option.addEventListener('click', () => {
        document.querySelectorAll('.pay-option').forEach(o => o.classList.remove('selected'));
        option.classList.add('selected');
        const radio = option.querySelector('input[type="radio"]');
        if (radio) {
          radio.checked = true;
        }
      });
    });
    
    
  </script>
  




<script>
function submitCheckout() {
    // check if any payment method is selected
    const selectedPayment = document.querySelector('input[name="payment_method"]:checked');
    if (!selectedPayment) {
        alert("Please select a payment method before placing the order.");
        return; // stop here – no timer, no submit
    }

    // show timer
    const timerBox = document.getElementById("redirect-timer");
    const countdownSpan = document.getElementById("countdown");

    timerBox.style.display = "block";

    let sec = 5;
    countdownSpan.textContent = sec;

    let timer = setInterval(() => {
        sec--;
        countdownSpan.textContent = sec;

        if (sec === 0) {
            clearInterval(timer);
            document.getElementById("placeOrderForm").submit();  // FINAL ORDER GOES TO SERVLET
        }
    }, 1000);
}
</script>

</body>
</html>

