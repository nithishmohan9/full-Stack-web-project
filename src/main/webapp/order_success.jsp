<%@ page import="java.util.*" %>
<%@ page import="com.daomodel.cart_item" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Integer orderId = (Integer) session.getAttribute("last_order_id");
    Double subtotal = (Double) session.getAttribute("last_order_subtotal");
    Double gst = (Double) session.getAttribute("last_order_gst");
    Double delivery = (Double) session.getAttribute("last_order_delivery");
    Double total = (Double) session.getAttribute("last_order_total");
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
    <title> SpiceSprint-Order Success</title>

    <!-- Optional Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">

    <style>
        * {
            box-sizing: border-box;
        }

        body {
            font-family: "Poppins", Arial, sans-serif;
            background: linear-gradient(135deg, #ffecd2, #fcb69f);
            margin: 0;
            padding: 0;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .wrapper {
            width: 100%;
            padding: 20px;
        }

        .container {
            max-width: 480px;
            margin: 0 auto;
            background: #ffffff;
            padding: 28px 22px 26px;
            border-radius: 16px;
            box-shadow: 0 10px 25px rgba(0,0,0,0.15);
            text-align: center;
            animation: fadeInUp 0.5s ease-out;
        }

        @keyframes fadeInUp {
            from { opacity: 0; transform: translateY(25px); }
            to   { opacity: 1; transform: translateY(0); }
        }

        .success-icon {
            width: 80px;
            height: 80px;
            margin: 0 auto 15px;
            border-radius: 50%;
            border: 4px solid #ff7a1a;
            display: flex;
            justify-content: center;
            align-items: center;
            position: relative;
            animation: popIn 0.4s ease-out 0.1s both;
        }

        @keyframes popIn {
            0%   { transform: scale(0.4); opacity: 0; }
            60%  { transform: scale(1.1); opacity: 1; }
            100% { transform: scale(1); }
        }

        .success-icon::before {
            content: "";
            width: 32px;
            height: 16px;
            border-left: 4px solid #ff7a1a;
            border-bottom: 4px solid #ff7a1a;
            transform: rotate(-45deg);
            position: relative;
            top: 2px;
            animation: tick 0.35s ease-out 0.25s both;
        }

        @keyframes tick {
            0%   { width: 0; height: 0; opacity: 0; }
            50%  { width: 32px; height: 0; opacity: 1; }
            100% { width: 32px; height: 16px; }
        }

        h1 {
            margin: 5px 0 6px;
            font-size: 22px;
            color: #333;
        }

        .subtitle {
            font-size: 14px;
            color: #777;
            margin-bottom: 12px;
        }

        .order-id {
            font-size: 14px;
            margin-bottom: 14px;
        }

        .order-id span {
            font-weight: 600;
            color: #ff7a1a;
            font-size: 16px;
        }

        .summary-card {
            background: #fff7f0;
            border-radius: 12px;
            padding: 14px 16px;
            margin-top: 10px;
            text-align: left;
        }

        .summary-header {
            font-size: 15px;
            font-weight: 600;
            margin-bottom: 10px;
            color: #444;
        }

        ul.summary-list {
            list-style: none;
            padding: 0;
            margin: 0;
        }

        ul.summary-list li {
            font-size: 14px;
            padding: 6px 0;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px dashed #f0c9a8;
        }

        ul.summary-list li:last-child {
            border-bottom: none;
            margin-top: 4px;
            font-size: 15px;
            font-weight: 600;
            color: #222;
        }

        .label {
            color: #555;
        }

        .value {
            color: #333;
        }

        .value.total {
            color: #ff7a1a;
        }

        .btn-row {
            margin-top: 18px;
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            justify-content: center;
        }

        a.button {
            text-decoration: none;
            padding: 10px 20px;
            border-radius: 999px;
            font-size: 14px;
            font-weight: 500;
            border: none;
            outline: none;
            cursor: pointer;
            display: inline-block;
            transition: transform 0.15s ease, box-shadow 0.15s ease, background 0.2s ease;
            white-space: nowrap;
        }

        a.button:active {
            transform: scale(0.97);
        }

        a.button.primary {
            background: #ff7a1a;
            color: #fff;
            box-shadow: 0 6px 14px rgba(255,122,26,0.4);
        }

        a.button.primary:hover {
            background: #e76506;
        }

        a.button.secondary {
            background: #fff;
            color: #ff7a1a;
            border: 1px solid #ffd3ad;
        }

        a.button.secondary:hover {
            background: #fff3e7;
        }

        .note {
            margin-top: 10px;
            font-size: 11px;
            color: #888;
        }

        @media (max-width: 480px) {
            .container {
                padding: 22px 18px;
                border-radius: 14px;
            }

            h1 {
                font-size: 20px;
            }

            .summary-card {
                padding: 12px 12px;
            }
        }
    </style>
</head>

<body>
<div class="wrapper">
    <div class="container">

        <div class="success-icon"></div>

        <h1>Order Placed Successfully!</h1>
        <p class="subtitle">Your food is being prepared. Sit back and relax.</p>

        <p class="order-id">
            Order ID:
            <span><%= orderId != null ? orderId : "N/A" %></span>
        </p>

        <div class="summary-card">
            <div class="summary-header">Order Summary</div>
            <ul class="summary-list">
                <li>
                    <span class="label">Subtotal</span>
                    <span class="value">Rs. <%= subtotal != null ? String.format("%.2f", subtotal) : "0.00" %></span>
                </li>
                <li>
                    <span class="label">GST</span>
                    <span class="value">Rs. <%= gst != null ? String.format("%.2f", gst) : "0.00" %></span>
                </li>
                <li>
                    <span class="label">Delivery</span>
                    <span class="value">Rs. <%= delivery != null ? String.format("%.2f", delivery) : "0.00" %></span>
                </li>
                <li>
                    <span class="label">Total Paid</span>
                    <span class="value total">Rs. <%= total != null ? String.format("%.2f", total) : "0.00" %></span>
                </li>
            </ul>
        </div>

        <div class="btn-row">
            <a href="download_invoice.jsp" class="button primary">Download Invoice</a>
            <a href="getAllRestaurant" class="button secondary">Browse More Restaurants</a>
        </div>

        <p class="note">You will receive order details and tracking info on your registered email / phone.</p>
    </div>
</div>
</body>
</html>
