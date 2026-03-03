<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>New Reservation - Ocean View Resort</title>
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
        .container { padding: 30px; max-width: 750px;
            margin: 0 auto; }
        .card { background: white; padding: 35px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08); }
        .card-header { margin-bottom: 25px;
            border-bottom: 2px solid #667eea;
            padding-bottom: 15px; }
        .card-header h2 { color: #333; font-size: 22px; }
        .card-header p { color: #666; font-size: 14px;
            margin-top: 5px; }
        .section-title {
            font-size: 15px; font-weight: bold;
            color: #667eea; margin: 25px 0 15px;
            display: flex; align-items: center; gap: 8px;
        }
        .form-row { display: flex; gap: 20px; }
        .form-row .form-group { flex: 1; }
        .form-group { margin-bottom: 18px; }
        .form-group label {
            display: block; margin-bottom: 6px;
            color: #444; font-weight: bold;
            font-size: 13px;
        }
        .required { color: #e74c3c; }
        .form-group input,
        .form-group select {
            width: 100%; padding: 12px;
            border: 2px solid #ddd; border-radius: 8px;
            font-size: 14px; transition: border-color 0.3s;
        }
        .form-group input:focus,
        .form-group select:focus {
            outline: none; border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102,126,234,0.1);
        }
        .form-group small {
            color: #999; font-size: 12px; margin-top: 4px;
            display: block;
        }
        .error-msg {
            background: #fee2e2; color: #991b1b;
            padding: 12px 15px; border-radius: 8px;
            margin-bottom: 20px; font-size: 14px;
            border-left: 4px solid #e74c3c;
        }
        .btn-submit {
            width: 100%; padding: 14px;
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white; border: none; border-radius: 8px;
            font-size: 16px; cursor: pointer;
            font-weight: bold; margin-top: 10px;
            transition: opacity 0.3s;
        }
        .btn-submit:hover { opacity: 0.9; }
        .btn-cancel {
            width: 100%; padding: 12px;
            background: #f3f4f6; color: #374151;
            border: none; border-radius: 8px;
            font-size: 14px; cursor: pointer;
            margin-top: 10px; text-decoration: none;
            display: block; text-align: center;
        }
        .divider {
            border: none; border-top: 1px dashed #ddd;
            margin: 20px 0;
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
        <a href="${pageContext.request.contextPath}/ReportServlet">
            📊 Reports</a>
        <a href="${pageContext.request.contextPath}/HelpServlet">
            ❓ Help</a>
        <a href="${pageContext.request.contextPath}/LogoutServlet">
            🚪 Logout</a>
    </div>
</div>

<div class="container">
    <div class="card">
        <div class="card-header">
            <h2>➕ New Reservation</h2>
            <p>Fill in the details below to create a new reservation</p>
        </div>

        <c:if test="${not empty error}">
            <div class="error-msg">⚠️ ${error}</div>
        </c:if>

        <form action="${pageContext.request.contextPath}/ReservationServlet"
              method="post">

            <p class="section-title">👤 Guest Information</p>

            <div class="form-group">
                <label>Full Name <span class="required">*</span></label>
                <input type="text" name="guestName"
                       placeholder="Enter guest full name"/>
                <small>Minimum 3 characters required</small>
            </div>

            <div class="form-group">
                <label>Address <span class="required">*</span></label>
                <input type="text" name="address"
                       placeholder="No. 12, Galle Road, Galle"/>
                <small>Guest's permanent address</small>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Contact Number <span class="required">*</span></label>
                    <input type="text" name="contact"
                           placeholder="0771234567"/>
                    <small>10 digit mobile number</small>
                </div>
                <div class="form-group">
                    <label>NIC Number</label>
                    <input type="text" name="nic"
                           placeholder="e.g. 123456789V"/>
                </div>
            </div>

            <div class="form-group">
                <label>Email Address</label>
                <input type="email" name="email"
                       placeholder="guest@example.com"/>
                <small>Optional — for booking confirmation</small>
            </div>

            <hr class="divider"/>

            <p class="section-title">🏨 Booking Details</p>

            <div class="form-group">
                <label>Filter by Room Type</label>
                <select id="roomTypeFilter"
                        onchange="filterRooms()"
                        style="width:100%; padding:12px;
                   border:2px solid #ddd;
                   border-radius:8px; font-size:14px;">
                    <option value="">-- All Room Types --</option>
                    <option value="Standard">Standard
                        (Rs. 5,000/night)</option>
                    <option value="Deluxe">Deluxe
                        (Rs. 8,000/night)</option>
                    <option value="Suite">Suite
                        (Rs. 15,000/night)</option>
                </select>
            </div>

            <div class="form-group">
                <label>Select Room <span class="required">*</span></label>
                <select name="roomId" id="roomSelect">
                    <option value="">-- Select Available Room --</option>
                    <c:forEach var="room" items="${availableRooms}">
                        <option value="${room.id}"
                                data-type="${room.roomType.typeName}">
                            Room ${room.roomNumber} |
                                ${room.roomType.typeName} |
                            Rs. ${room.roomType.pricePerNight}/night |
                            Floor ${room.floor}
                        </option>
                    </c:forEach>
                </select>
                <small>Only available rooms are shown</small>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label>Check-In Date <span class="required">*</span></label>
                    <input type="date" name="checkIn"/>
                </div>
                <div class="form-group">
                    <label>Check-Out Date <span class="required">*</span></label>
                    <input type="date" name="checkOut"/>
                </div>
            </div>

            <button type="submit" class="btn-submit">
                ✅ Create Reservation
            </button>
            <a href="${pageContext.request.contextPath}/ReservationServlet"
               class="btn-cancel">Cancel</a>
        </form>
    </div>
</div>

<script>
    function filterRooms() {
        var filter = document.getElementById("roomTypeFilter")
            .value;
        var select = document.getElementById("roomSelect");
        var options = select.getElementsByTagName("option");

        for (var i = 1; i < options.length; i++) {
            var type = options[i].getAttribute("data-type");
            if (filter === "" || type === filter) {
                options[i].style.display = "";
            } else {
                options[i].style.display = "none";
            }
        }
        select.value = "";
    }
</script>
</body>
</html>