<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reservations - Ocean View Resort</title>
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
        .container { padding: 30px; }
        .card { background: white; padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.08); }
        .card-header {
            display: flex; justify-content: space-between;
            align-items: center; margin-bottom: 20px;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px;
        }
        .card-header h2 { color: #333; }
        .search-bar {
            margin-bottom: 20px;
        }
        .search-bar input {
            width: 100%; padding: 12px;
            border: 2px solid #ddd; border-radius: 8px;
            font-size: 14px;
        }
        .search-bar input:focus {
            outline: none; border-color: #667eea;
        }
        .btn { padding: 10px 20px; border-radius: 5px;
            text-decoration: none; font-size: 14px;
            display: inline-block; font-weight: bold; }
        .btn-primary { background: #667eea; color: white; }
        .btn-success { background: #2ecc71; color: white; }
        .btn-danger { background: #e74c3c; color: white; }
        table { width: 100%; border-collapse: collapse; }
        th { background: #667eea; color: white;
            padding: 12px; text-align: left; }
        td { padding: 12px; border-bottom: 1px solid #eee;
            font-size: 14px; }
        tr:hover { background: #f9f9f9; }
        .badge { padding: 5px 12px; border-radius: 20px;
            font-size: 12px; font-weight: bold; }
        .badge-confirmed { background: #dbeafe; color: #1d4ed8; }
        .badge-checked_in { background: #dcfce7; color: #166534; }
        .badge-checked_out { background: #f3f4f6; color: #374151; }
        .badge-cancelled { background: #fee2e2; color: #991b1b; }
        .empty-msg { text-align: center; color: #999;
            padding: 40px; font-size: 16px; }
        .success-msg {
            background: #dcfce7; color: #166534;
            padding: 12px; border-radius: 8px;
            margin-bottom: 20px; font-weight: bold;
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>🌊 Ocean View Resort</h1>
    <div>
        <a href="${pageContext.request.contextPath}/DashboardServlet">
            🏠 Dashboard</a>
        <a href="${pageContext.request.contextPath}/ReservationServlet?action=add">
            ➕ New Reservation</a>
        <a href="${pageContext.request.contextPath}/ReportServlet">
            📊 Reports</a>
        <a href="${pageContext.request.contextPath}/HelpServlet">
            ❓ Help</a>
        <a href="${pageContext.request.contextPath}/LogoutServlet">
            🚪 Logout</a>
    </div>
</div>

<div class="container">

    <c:if test="${not empty sessionScope.success}">
        <div class="success-msg">
            ✅ ${sessionScope.success}
        </div>
        <c:remove var="success" scope="session"/>
    </c:if>

    <div class="card">
        <div class="card-header">
            <h2>📋 All Reservations</h2>
            <a href="${pageContext.request.contextPath}/ReservationServlet?action=add"
               class="btn btn-primary">➕ New Reservation</a>
        </div>

        <%-- Search by Reservation Number --%>
        <div style="background:#f8f9ff; padding:20px;
            border-radius:8px; margin-bottom:20px;
            border: 1px solid #667eea;">
            <h3 style="color:#667eea; margin-bottom:15px;">
                🔍 Search Reservation by Number</h3>
            <form action="${pageContext.request.contextPath}/ReservationServlet"
                  method="get" style="display:flex; gap:10px;">
                <input type="text" name="search"
                       placeholder="Enter Reservation Number (e.g. RES-XXXXXXXX)"
                       style="flex:1; padding:12px; border:2px solid #ddd;
                      border-radius:8px; font-size:14px;"/>
                <button type="submit"
                        style="padding:12px 25px; background:#667eea;
                       color:white; border:none; border-radius:8px;
                       font-weight:bold; cursor:pointer;">
                    Search</button>
            </form>

            <c:if test="${not empty searchError}">
                <div style="background:#fee2e2; color:#991b1b;
                    padding:10px; border-radius:5px; margin-top:10px;">
                    ⚠️ ${searchError}
                </div>
            </c:if>

            <c:if test="${not empty searchResult}">
                <div style="margin-top:15px; background:white;
                    padding:15px; border-radius:8px;
                    border:1px solid #2ecc71;">
                    <h4 style="color:#2ecc71; margin-bottom:10px;">
                        ✅ Reservation Found!</h4>
                    <table style="width:100%; border-collapse:collapse;">
                        <tr style="background:#f8f9ff;">
                            <th style="padding:10px; text-align:left;
                               color:#667eea;">Field</th>
                            <th style="padding:10px; text-align:left;
                               color:#667eea;">Details</th>
                        </tr>
                        <tr>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                <strong>Res. Number</strong></td>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                    ${searchResult.reservationNumber}</td>
                        </tr>
                        <tr>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                <strong>Guest Name</strong></td>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                    ${searchResult.guest.name}</td>
                        </tr>
                        <tr>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                <strong>Contact</strong></td>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                    ${searchResult.guest.contactNumber}</td>
                        </tr>
                        <tr>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                <strong>Room</strong></td>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                    ${searchResult.room.roomNumber} -
                                    ${searchResult.room.roomType.typeName}</td>
                        </tr>
                        <tr>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                <strong>Check In</strong></td>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                <fmt:formatDate value="${searchResult.checkInDate}"
                                                pattern="yyyy-MM-dd"/></td>
                        </tr>
                        <tr>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                <strong>Check Out</strong></td>
                            <td style="padding:10px; border-bottom:1px solid #eee;">
                                <fmt:formatDate value="${searchResult.checkOutDate}"
                                                pattern="yyyy-MM-dd"/></td>
                        </tr>
                        <tr>
                            <td style="padding:10px;"><strong>Status</strong></td>
                            <td style="padding:10px;">
                                    ${searchResult.status}</td>
                        </tr>
                    </table>
                    <div style="margin-top:10px;">
                        <a href="${pageContext.request.contextPath}/BillServlet?reservationId=${searchResult.id}"
                           style="padding:10px 20px; background:#2ecc71;
                          color:white; border-radius:5px;
                          text-decoration:none; font-weight:bold;">
                            💰 View Bill</a>
                    </div>
                </div>
            </c:if>
        </div>


        <div class="search-bar">
            <input type="text" id="searchInput"
                   placeholder="🔍 Search by guest name or reservation number..."
                   onkeyup="searchTable()"/>
        </div>

        <c:choose>
            <c:when test="${empty reservations}">
                <div class="empty-msg">
                    📭 No reservations found.<br>
                    <a href="${pageContext.request.contextPath}/ReservationServlet?action=add">
                        Create first reservation!</a>
                </div>
            </c:when>
            <c:otherwise>
                <table id="reservationTable">
                    <tr>
                        <th>#</th>
                        <th>Res. Number</th>
                        <th>Guest Name</th>
                        <th>Contact</th>
                        <th>Address</th>
                        <th>Room</th>
                        <th>Check In</th>
                        <th>Check Out</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="res" items="${reservations}"
                               varStatus="loop">
                        <tr>
                            <td>${loop.count}</td>
                            <td><strong>${res.reservationNumber}</strong></td>
                            <td>${res.guest.name}</td>
                            <td>${res.guest.contactNumber}</td>
                            <td>${res.guest.address}</td>
                            <td>${res.room.roomNumber}</td>
                            <td><fmt:formatDate value="${res.checkInDate}"
                                                pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${res.checkOutDate}"
                                                pattern="yyyy-MM-dd"/></td>
                            <td>Rs. <fmt:formatNumber
                                    value="${res.totalAmount}"
                                    pattern="#,##0.00"/></td>
                            <td>
                            <span class="badge badge-${res.status.toLowerCase()}">
                                    ${res.status}
                            </span>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${res.status eq 'CONFIRMED'}">
                                        <a href="${pageContext.request.contextPath}/BillServlet?reservationId=${res.id}"
                                           class="btn btn-success">💰 Bill</a>
                                        <a href="${pageContext.request.contextPath}/ReservationServlet?action=checkin&id=${res.id}"
                                           class="btn btn-primary"
                                           onclick="return confirm('Check in this guest?')">
                                            🏨 Check-in</a>
                                        <a href="${pageContext.request.contextPath}/ReservationServlet?action=cancel&id=${res.id}"
                                           class="btn btn-danger"
                                           onclick="return confirm('Cancel this reservation?')">
                                            ❌ Cancel</a>
                                    </c:when>
                                    <c:when test="${res.status eq 'CHECKED_IN'}">
                                        <a href="${pageContext.request.contextPath}/BillServlet?reservationId=${res.id}"
                                           class="btn btn-success">💰 Bill</a>
                                        <a href="${pageContext.request.contextPath}/ReservationServlet?action=checkout&id=${res.id}"
                                           class="btn btn-primary"
                                           onclick="return confirm('Check out this guest?')">
                                            🚪 Check-out</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${pageContext.request.contextPath}/BillServlet?reservationId=${res.id}"
                                           class="btn btn-success">💰 Bill</a>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<script>
    function searchTable() {
        var input = document.getElementById("searchInput").value
            .toLowerCase();
        var table = document.getElementById("reservationTable");
        var rows = table.getElementsByTagName("tr");

        for (var i = 1; i < rows.length; i++) {
            var guestName = rows[i].cells[2].textContent.toLowerCase();
            var resNumber = rows[i].cells[1].textContent.toLowerCase();
            if (guestName.includes(input) ||
                resNumber.includes(input)) {
                rows[i].style.display = "";
            } else {
                rows[i].style.display = "none";
            }
        }
    }
</script>
</body>
</html>