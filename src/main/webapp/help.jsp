<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Help - Ocean View Resort</title>
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
        .container { padding: 30px; max-width: 900px; margin: 0 auto; }
        .card { background: white; padding: 30px;
            border-radius: 10px; margin-bottom: 20px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1); }
        .card h2 { color: #667eea; margin-bottom: 20px;
            border-bottom: 2px solid #667eea;
            padding-bottom: 10px; }
        .step { background: #f8f9ff; padding: 15px;
            border-radius: 8px; margin-bottom: 15px;
            border-left: 4px solid #667eea; }
        .step h3 { color: #333; margin-bottom: 8px; }
        .step p { color: #666; line-height: 1.6; }
        .badge { background: #667eea; color: white;
            padding: 3px 10px; border-radius: 20px;
            font-size: 12px; margin-right: 8px; }
        table { width: 100%; border-collapse: collapse; }
        th { background: #667eea; color: white; padding: 10px; }
        td { padding: 10px; border-bottom: 1px solid #eee; }
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
        <a href="${pageContext.request.contextPath}/LogoutServlet">
            Logout</a>
    </div>
</div>

<div class="container">
    <div class="card">
        <h2>📖 Help & User Guide</h2>

        <div class="step">
            <h3><span class="badge">1</span> Login</h3>
            <p>Enter username and password to access the system.</p>
        </div>

        <div class="step">
            <h3><span class="badge">2</span> Add New Reservation</h3>
            <p>Click "+ New Reservation". Fill guest details,
                select room, check-in and check-out dates.</p>
        </div>

        <div class="step">
            <h3><span class="badge">3</span> View Reservations</h3>
            <p>Click "All Reservations" to see all bookings.</p>
        </div>

        <div class="step">
            <h3><span class="badge">4</span> Generate Bill</h3>
            <p>Click "Bill" button next to any reservation.
                System calculates room charges + 10% tax.</p>
        </div>

        <div class="step">
            <h3><span class="badge">5</span> Logout</h3>
            <p>Always click "Logout" when finished.</p>
        </div>
    </div>

    <div class="card">
        <h2>🏨 Room Types & Rates</h2>
        <table>
            <tr>
                <th>Room Type</th>
                <th>Price Per Night</th>
            </tr>
            <tr><td>Standard</td><td>Rs. 5,000</td></tr>
            <tr><td>Deluxe</td><td>Rs. 8,000</td></tr>
            <tr><td>Suite</td><td>Rs. 15,000</td></tr>
        </table>
    </div>
</div>
</body>
</html>