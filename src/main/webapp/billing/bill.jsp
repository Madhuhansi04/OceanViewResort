<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bill - Ocean View Resort</title>
    <style>
        * { margin: 0; padding: 0; box-sizing: border-box; }
        body { font-family: Arial, sans-serif; background: #f5f6fa; }
        .navbar {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white; padding: 15px 30px;
            display: flex; justify-content: space-between;
            align-items: center;
        }
        .navbar h1 { font-size: 20px; }
        .navbar a { color: white; text-decoration: none;
            margin-left: 10px; padding: 8px 15px;
            background: rgba(255,255,255,0.2);
            border-radius: 5px; font-size: 14px; }
        .navbar a:hover { background: rgba(255,255,255,0.35); }
        .container { padding: 30px; max-width: 650px;
            margin: 0 auto; }
        .bill-card {
            background: white; border-radius: 10px;
            box-shadow: 0 2px 15px rgba(0,0,0,0.1);
            overflow: hidden;
        }
        .bill-header {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white; padding: 30px; text-align: center;
        }
        .bill-header h2 { font-size: 26px; margin-bottom: 5px; }
        .bill-header p { opacity: 0.85; font-size: 14px; }
        .bill-header .res-number {
            background: rgba(255,255,255,0.2);
            padding: 8px 20px; border-radius: 20px;
            display: inline-block; margin-top: 12px;
            font-weight: bold; font-size: 15px;
        }
        .bill-body { padding: 30px; }
        .info-section {
            background: #f8f9ff; padding: 20px;
            border-radius: 8px; margin-bottom: 20px;
        }
        .info-section h3 {
            color: #667eea; font-size: 14px;
            text-transform: uppercase; margin-bottom: 12px;
            letter-spacing: 1px;
        }
        .info-row {
            display: flex; justify-content: space-between;
            margin-bottom: 10px; font-size: 14px;
        }
        .info-row .label { color: #666; }
        .info-row .value { font-weight: bold; color: #333; }
        .divider {
            border: none; border-top: 2px dashed #eee;
            margin: 20px 0;
        }
        .charges-section { margin-bottom: 20px; }
        .charge-row {
            display: flex; justify-content: space-between;
            padding: 12px 0; border-bottom: 1px solid #f0f0f0;
            font-size: 15px;
        }
        .charge-row .charge-label { color: #555; }
        .charge-row .charge-value { font-weight: bold; }
        .total-row {
            display: flex; justify-content: space-between;
            padding: 15px 20px; margin-top: 15px;
            background: linear-gradient(135deg, #667eea, #764ba2);
            border-radius: 8px; color: white;
        }
        .total-row span:first-child {
            font-size: 18px; font-weight: bold;
        }
        .total-row span:last-child {
            font-size: 22px; font-weight: bold;
        }
        .status-badge {
            display: inline-block; padding: 6px 15px;
            border-radius: 20px; font-size: 13px;
            font-weight: bold; margin-top: 15px;
        }
        .status-pending {
            background: #fef3c7; color: #92400e;
        }
        .status-paid {
            background: #dcfce7; color: #166534;
        }
        .actions {
            display: flex; gap: 10px;
            justify-content: center;
            padding: 20px 30px 30px;
            border-top: 1px solid #eee;
        }
        .btn { padding: 12px 30px; border-radius: 8px;
            font-size: 15px; font-weight: bold;
            cursor: pointer; border: none;
            text-decoration: none;
            display: inline-block; }
        .btn-print {
            background: linear-gradient(135deg, #2ecc71, #27ae60);
            color: white;
        }
        .btn-back {
            background: #f3f4f6; color: #374151;
        }
        .btn:hover { opacity: 0.9; }
        .not-found {
            text-align: center; padding: 50px;
            color: #666;
        }
        @media print {
            .navbar, .actions { display: none; }
            body { background: white; }
            .bill-card {
                box-shadow: none;
            }
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>🌊 Ocean View Resort</h1>
    <div>
        <a href="${pageContext.request.contextPath}/DashboardServlet">
            🏠 Dashboard</a>
        <a href="${pageContext.request.contextPath}/ReservationServlet">
            📋 Reservations</a>
        <a href="${pageContext.request.contextPath}/LogoutServlet">
            🚪 Logout</a>
    </div>
</div>

<div class="container">
    <div class="bill-card">

        <c:if test="${not empty bill}">
            <div class="bill-header">
                <h2>🌊 Ocean View Resort</h2>
                <p>Beachside Hotel, Galle, Sri Lanka</p>
                <div class="res-number">
                        ${bill.reservation.reservationNumber}
                </div>
            </div>

            <div class="bill-body">
                <div class="info-section">
                    <h3>👤 Guest Details</h3>
                    <div class="info-row">
                        <span class="label">Guest Name</span>
                        <span class="value">
                                ${bill.reservation.guest.name}</span>
                    </div>
                    <div class="info-row">
                        <span class="label">Contact</span>
                        <span class="value">
                                ${bill.reservation.guest.contactNumber}</span>
                    </div>
                    <div class="info-row">
                        <span class="label">Address</span>
                        <span class="value">
                                ${bill.reservation.guest.address}</span>
                    </div>
                    <div class="info-row">
                        <span class="label">NIC</span>
                        <span class="value">
                                ${bill.reservation.guest.nic}</span>
                    </div>
                </div>
                <div class="info-section">
                    <h3>🏨 Room Details</h3>
                    <div class="info-row">
                        <span class="label">Room Number</span>
                        <span class="value">
                                ${bill.reservation.room.roomNumber}</span>
                    </div>
                    <div class="info-row">
                        <span class="label">Room Type</span>
                        <span class="value">
                                ${bill.reservation.room.roomType.typeName}</span>
                    </div>
                    <div class="info-row">
                        <span class="label">Check-In</span>
                        <span class="value">
                            <fmt:formatDate
                                    value="${bill.reservation.checkInDate}"
                                    pattern="yyyy-MM-dd"/></span>
                    </div>
                    <div class="info-row">
                        <span class="label">Check-Out</span>
                        <span class="value">
                            <fmt:formatDate
                                    value="${bill.reservation.checkOutDate}"
                                    pattern="yyyy-MM-dd"/></span>
                    </div>
                    <div class="info-row">
                        <span class="label">Duration</span>
                        <span class="value">
                            ${bill.nights} Night(s)</span>
                    </div>
                </div>

                <hr class="divider"/>

                <div class="charges-section">
                    <div class="charge-row">
                        <span class="charge-label">
                            Room Charge
                            (${bill.nights} nights)</span>
                        <span class="charge-value">
                            Rs. <fmt:formatNumber
                                value="${bill.roomCharge}"
                                pattern="#,##0.00"/></span>
                    </div>
                    <div class="charge-row">
                        <span class="charge-label">
                            Tax (10%)</span>
                        <span class="charge-value">
                            Rs. <fmt:formatNumber
                                value="${bill.taxAmount}"
                                pattern="#,##0.00"/></span>
                    </div>
                </div>

                <div class="total-row">
                    <span>TOTAL AMOUNT</span>
                    <span>Rs. <fmt:formatNumber
                            value="${bill.totalAmount}"
                            pattern="#,##0.00"/></span>
                </div>

                <div style="text-align:center;">
                    <span class="status-badge status-${bill.paymentStatus.toLowerCase()}">
                        💳 Payment: ${bill.paymentStatus}
                    </span>
                </div>
            </div>

            <div class="actions">
                <button onclick="window.print()"
                        class="btn btn-print">
                    🖨️ Print Bill</button>
                <a href="${pageContext.request.contextPath}/ReservationServlet"
                   class="btn btn-back">← Back</a>
            </div>
        </c:if>

        <c:if test="${empty bill}">
            <div class="not-found">
                <h3>❌ Bill Not Found!</h3>
                <p>Please try again.</p>
                <br>
                <a href="${pageContext.request.contextPath}/ReservationServlet"
                   class="btn btn-back">← Back to Reservations</a>
            </div>
        </c:if>

    </div>
</div>
</body>
</html>