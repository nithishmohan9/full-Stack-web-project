<%@ page import="java.util.*" %>
<%@ page import="com.daomodel.cart_item" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%
    // Retrieve order summary (sent from checkout servlet)
    Integer orderId = (Integer) session.getAttribute("last_order_id");
    Double subtotal = (Double) session.getAttribute("last_order_subtotal");
    Double gst = (Double) session.getAttribute("last_order_gst");
    Double delivery = (Double) session.getAttribute("last_order_delivery");
    Double total = (Double) session.getAttribute("last_order_total");
    Map<Integer, cart_item> items = (Map<Integer, cart_item>) session.getAttribute("last_order_items");

    if (orderId == null || items == null || items.isEmpty()) {
    	%>
    	    <script>
    	        alert("Invoice already generated and sent to your mail id, please check it.");
    	        // optional: redirect back to order success / home page
    	        window.location.href = "order_success.jsp"; // change to your page
    	    </script>
    	<%
    	    return;
    	}


    // Create a safe filename
    String filename = "invoice_" + orderId + ".html";

    // Force browser download
    response.setContentType("text/html; charset=UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

    // -----------------------------------------------------
    // UPI QR Generation
    // -----------------------------------------------------
    String upiId = "sanjay@oksbi";   // <--- PUT YOUR UPI ID HERE
    String payeeName = "Food Delivery";
    String amount = String.format("%.2f", total);

    String upiURL = "upi://pay?pa=" + upiId +
                    "&pn=" + payeeName +
                    "&am=" + amount +
                    "&cu=INR" +
                    "&tn=Order%20Payment%20Invoice%20" + orderId;

    // Convert to QR code via Google Chart API
    String qrURL = "https://chart.googleapis.com/chart?chs=300x300&cht=qr&chl="
                    + java.net.URLEncoder.encode(upiURL, "UTF-8");

    // Remove session data (one-time invoice)
    session.removeAttribute("last_order_id");
    session.removeAttribute("last_order_items");
    session.removeAttribute("last_order_subtotal");
    session.removeAttribute("last_order_gst");
    session.removeAttribute("last_order_delivery");
    session.removeAttribute("last_order_total");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8"/>
  <title>SpiceSprint⚡ Invoice - <%= orderId %></title>

  <style>
    body { font-family: Arial, sans-serif; color:#222; padding:20px; }
    .invoice { max-width:900px; margin:0 auto; border:1px solid #eee; padding:25px; }
    .header { display:flex; justify-content:space-between; align-items:center; }
    .brand { font-size:26px; font-weight:900; color:#0a8f38; }
    .meta { text-align:right; font-size:14px; }

    table { width:100%; border-collapse:collapse; margin-top:20px; }
    th, td { padding:10px; border:1px solid #eee; }
    th { background:#f8f8f8; font-weight:600; }

    .right { text-align:right; }
    .total-box { margin-top:25px; font-size:16px; }
    .total-box table { width:100%; }
    .total-box td { padding:8px; }

    .qr-section { margin-top:30px; text-align:center; }
    .qr-section h3 { margin-bottom:10px; }
  </style>
</head>

<body>
  <div class="invoice">

    <!-- HEADER -->
    <div class="header">
      <div class="brand">SpiceSprint ⚡</div>
      <div class="meta">
        <div><strong>Invoice #: </strong> <%= orderId %></div>
        <div>Date: <%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm").format(new java.util.Date()) %></div>
      </div>
    </div>

    <!-- ITEMS TABLE -->
    <h3 style="margin-top:25px;">Order Summary</h3>

    <table>
      <thead>
        <tr>
          <th>#</th>
          <th>Item</th>
          <th class="right">Unit Price</th>
          <th class="right">Qty</th>
          <th class="right">Total</th>
        </tr>
      </thead>
      <tbody>
      <%
        int index = 1;
        for (cart_item it : items.values()) {
            double line = it.getPrice() * it.getQuantity();
      %>
        <tr>
          <td><%= index++ %></td>
          <td><%= it.getItem_name() %></td>
          <td class="right">Rs. <%= it.getPrice() %></td>
          <td class="right"><%= it.getQuantity() %></td>
          <td class="right">Rs. <%= String.format("%.2f", line) %></td>
        </tr>
      <% } %>
      </tbody>
    </table>

    <!-- TOTALS -->
    <div class="total-box">
      <table>
        <tr><td>Subtotal</td><td class="right">Rs. <%= subtotal %></td></tr>
        <tr><td>GST (5%)</td><td class="right">Rs. <%= gst %></td></tr>
        <tr><td>Delivery</td><td class="right">Rs. <%= delivery %></td></tr>
        <tr style="font-weight:700; font-size:18px;">
          <td>Total Payable</td>
          <td class="right">Rs. <%= total %></td>
        </tr>
      </table>
    </div>

    <!-- QR PAYMENT -->
    <div class="qr-section">
      <h3>Scan & Pay</h3>
      <p>Use any UPI app (Google Pay / PhonePe / PayTM)</p>
      <img src="<%= qrURL %>" alt="UPI QR Code"/>
      <p style="margin-top:10px; font-size:14px;">UPI ID: <strong><%= upiId %></strong></p>
    </div>

    <p class="small" style="margin-top:25px; text-align:center; color:#777;">
      This is a system-generated invoice. No signature required.
    </p>

  </div>
</body>
</html>
