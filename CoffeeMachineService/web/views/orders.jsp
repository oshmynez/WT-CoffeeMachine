<%@ page import="java.util.List" %>
<%@ page import="java.lang.Object" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.Queue" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru" >
<head>
    <meta charset="UTF-8">
    <title>Profile</title>
    <link rel="stylesheet" href="../stylesCSS/orders.css">
</head>

<body>
<header class="headerContainer">
    <div class="profileItem">
        <button class="profile" onclick="location.href='/homePage'" >Coffee</button>
    </div>
    <div class="profileItem">
        <button class="profile" value="Refresh Page" onclick="window.location.reload();" >Coffee</button>
    </div>
    <div class="profileItem">
        <button class="profile" onclick="location.href='/exit'" >Выход</button>
    </div>
    <div class="language">
            <select>
                <option value="1">ru</option>
                <option value="2">en</option>
            </select>
    </div>

</header>
<div class="container" >
    <h1>Orders</h1>
    <form method="post">
        <table>
            <tr>
                <td class="nameField"></td>
                <td class="nameField">TypeCoffee</td>
                <td class="nameField">TypeBeans</td>
                <td class="nameField">Additives</td>
                <td class="nameField">Status</td>
            </tr>

            <%
                Queue<Order> orderArrayList = (Queue<Order>) request.getAttribute("ListOrders");
                if  (orderArrayList != null && orderArrayList.isEmpty()){
                    for (Order order: orderArrayList){
                        out.println("<tr>");
                        out.println("<td><label type=\"text\" class=\"buttonSubmit\">" + order.NameCoffee + "</label>");
                        out.println("<td><label type=\"text\" class=\"buttonSubmit\">" + order.NameBeans + "</label>");
                        out.println("<td><label type=\"text\" class=\"buttonSubmit\">" + order.NameAdditives + "</label>");
                        out.println("<td><label type=\"text\" class=\"buttonSubmit\">" + order.StatusOrder + "</label>");
                        out.println("<tr>");
                    }
                }
                %>

        </table>
    </form>
</div>
<script src='https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script><script  src="../jsfiles/buttonOrder.js"></script>

</body>
</html>

