<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - Ocean View Resort</title>
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
            margin-left: 20px; padding: 8px 15px;
            background: rgba(255,255,255,0.2);
            border-radius: 5px; }
        .navbar a:hover { background: rgba(255,255,255,0.3); }
        .container { padding: 30px; }
        .stats { display: flex; gap: 20px; margin-bottom: 30px; }
        .stat-card {
            background: white; padding: 25px; border-radius: 10px;
            flex: 1; text-align: center;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .stat-card h3 { font-size: 36px; color: #667eea; }
        .stat-card p { color: #666; margin-top: 5px; }
        .section {
            background: white; padding: 25px;
            border-radius: 10px; margin-bottom: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .section h2 { margin-bottom: 20px; color: #333;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px; }
        table { width: 100%; border-collapse: collapse; }
        th { background: #667eea; color: white; padding: 12px;
            text-align: left; }
        td { padding: 12px; border-bottom: 1px solid #eee; }
        tr:hover { background: #f9f9f9; }
        .btn { padding: 8px 15px; border-radius: 5px;
            text-decoration: none; font-size: 13px;
            display: inline-block; }
        .btn-primary { background: #667eea; color: white; }
        .btn-success { background: #2ecc71; color: white; }
        .status-confirmed { color: #3498db; font-weight: bold; }
        .status-checked_in { color: #2ecc71; font-weight: bold; }
        .status-cancelled { color: #e74c3c; font-weight: bold; }
    </style>
</head>
<body>
<div class="navbar">
    <h1>🌊 Ocean View Resort</h1>
    <div>
        <span>Welcome, ${sessionScope.loggedUser.fullName}</span>
        <a href="${pageContext.request.contextPath}/ReservationServlet?action=add">
            + New Reservation</a>
        <a href="${pageContext.request.contextPath}/ReservationServlet">
            All Reservations</a>
        <a href="${pageContext.request.contextPath}/ReportServlet">
            Reports</a>
        <a href="${pageContext.request.contextPath}/HelpServlet">Help</a>
        <a href="${pageContext.request.contextPath}/LogoutServlet">
            Logout</a>
    </div>
</div>

<div class="container">
    <div class="stats">
        <div class="stat-card">
            <h3>${reservations.size()}</h3>
            <p>Total Reservations</p>
        </div>
        <div class="stat-card">
            <h3>${availableRooms.size()}</h3>
            <p>Available Rooms</p>
        </div>
        <div class="stat-card">
            <h3>${allRooms.size()}</h3>
            <p>Total Rooms</p>
        </div>
    </div>

    <div class="section">
        <h2>Recent Reservations</h2>
        <table>
            <tr>
                <th>Res. Number</th>
                <th>Guest</th>
                <th>Room</th>
                <th>Check In</th>
                <th>Check Out</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
            <c:forEach var="res" items="${reservations}"
                       begin="0" end="4">
                <tr>
                    <td>${res.reservationNumber}</td>
                    <td>${res.guest.name}</td>
                    <td>${res.room.roomNumber}</td>
                    <td><fmt:formatDate value="${res.checkInDate}"
                                        pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${res.checkOutDate}"
                                        pattern="yyyy-MM-dd"/></td>
                    <td class="status-${res.status.toLowerCase()}">
                            ${res.status}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/BillServlet?reservationId=${res.id}"
                           class="btn btn-success">Bill</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>