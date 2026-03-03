<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Reports - Ocean View Resort</title>
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
            margin-left: 15px; padding: 8px 15px;
            background: rgba(255,255,255,0.2);
            border-radius: 5px; }
        .container { padding: 30px; }
        .report-tabs {
            display: flex; gap: 10px; margin-bottom: 25px;
        }
        .tab {
            padding: 12px 25px; border-radius: 8px;
            text-decoration: none; font-weight: bold;
            font-size: 14px; color: #667eea;
            background: white;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        .tab.active {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white;
        }
        .card {
            background: white; padding: 25px;
            border-radius: 10px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        .card h2 {
            color: #333; margin-bottom: 20px;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px;
        }
        table { width: 100%; border-collapse: collapse; }
        th { background: #667eea; color: white; padding: 12px;
            text-align: left; }
        td { padding: 12px; border-bottom: 1px solid #eee; }
        tr:hover { background: #f9f9f9; }
        .badge {
            padding: 4px 10px; border-radius: 20px;
            font-size: 12px; font-weight: bold;
        }
        .available { background: #dcfce7; color: #166534; }
        .occupied { background: #fee2e2; color: #991b1b; }
        .summary-box {
            display: flex; gap: 20px; margin-bottom: 25px;
        }
        .summary-card {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: white; padding: 20px; border-radius: 10px;
            flex: 1; text-align: center;
        }
        .summary-card h3 { font-size: 28px; }
        .summary-card p { opacity: 0.9; margin-top: 5px; }
        .print-btn {
            float: right; padding: 10px 20px;
            background: #2ecc71; color: white;
            border: none; border-radius: 5px;
            cursor: pointer; font-weight: bold;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="navbar">
    <h1>🌊 Ocean View Resort</h1>
    <div>
        <a href="${pageContext.request.contextPath}/DashboardServlet">
            Dashboard</a>
        <a href="${pageContext.request.contextPath}/ReservationServlet">
            Reservations</a>
        <a href="${pageContext.request.contextPath}/HelpServlet">
            Help</a>
        <a href="${pageContext.request.contextPath}/LogoutServlet">
            Logout</a>
    </div>
</div>

<div class="container">
    <div class="report-tabs">
        <a href="${pageContext.request.contextPath}/ReportServlet?type=revenue"
           class="tab ${reportType == 'revenue' ? 'active' : ''}">
            📊 Revenue Report</a>
        <a href="${pageContext.request.contextPath}/ReportServlet?type=rooms"
           class="tab ${reportType == 'rooms' ? 'active' : ''}">
            🏨 Room Status Report</a>
        <a href="${pageContext.request.contextPath}/ReportServlet?type=guests"
           class="tab ${reportType == 'guests' ? 'active' : ''}">
            👥 Guest History Report</a>
    </div>

    <%-- Revenue Report --%>
    <c:if test="${reportType == 'revenue'}">
        <div class="card">
            <button class="print-btn" onclick="window.print()">
                🖨️ Print</button>
            <h2>📊 Monthly Revenue Report</h2>
            <table>
                <tr>
                    <th>Year</th>
                    <th>Month</th>
                    <th>Total Reservations</th>
                    <th>Total Revenue</th>
                </tr>
                <c:forEach var="row" items="${revenueData}">
                    <tr>
                        <td>${row.year}</td>
                        <td>${row.month}</td>
                        <td>${row.total_reservations}</td>
                        <td>Rs. <fmt:formatNumber
                                value="${row.total_revenue}"
                                pattern="#,##0.00"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <%-- Room Status Report --%>
    <c:if test="${reportType == 'rooms'}">
        <div class="card">
            <button class="print-btn" onclick="window.print()">
                🖨️ Print</button>
            <h2>🏨 Room Availability Report</h2>
            <table>
                <tr>
                    <th>Room No</th>
                    <th>Type</th>
                    <th>Floor</th>
                    <th>Price/Night</th>
                    <th>Status</th>
                </tr>
                <c:forEach var="row" items="${roomData}">
                    <tr>
                        <td>${row.room_number}</td>
                        <td>${row.type_name}</td>
                        <td>Floor ${row.floor}</td>
                        <td>Rs. <fmt:formatNumber
                                value="${row.price_per_night}"
                                pattern="#,##0.00"/></td>
                        <td>
                        <span class="badge ${row.status == 'AVAILABLE'
                            ? 'available' : 'occupied'}">
                                ${row.status}
                        </span>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>

    <%-- Guest History Report --%>
    <c:if test="${reportType == 'guests'}">
        <div class="card">
            <button class="print-btn" onclick="window.print()">
                🖨️ Print</button>
            <h2>👥 Guest History Report</h2>
            <table>
                <tr>
                    <th>Guest Name</th>
                    <th>Contact</th>
                    <th>NIC</th>
                    <th>Total Visits</th>
                    <th>Total Spent</th>
                </tr>
                <c:forEach var="row" items="${guestData}">
                    <tr>
                        <td>${row.name}</td>
                        <td>${row.contact_number}</td>
                        <td>${row.nic}</td>
                        <td>${row.total_visits}</td>
                        <td>Rs. <fmt:formatNumber
                                value="${row.total_spent}"
                                pattern="#,##0.00"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </c:if>
</div>
</body>
</html>