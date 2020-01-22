<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="app.model.*" %>
<%@ page import="app.utils.OrdersDAO" %><%--
  Created by IntelliJ IDEA.
  User: andrz
  Date: 18.01.2020
  Time: 0:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web storage app</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
        }

        th {
            text-align: left;
        }

        tr.row {
            border: solid;
            border-width: 2px 0;
            border-bottom: none;
        }

        .navbar {
            overflow: hidden;
            background-color: #333;
            min-height: 50px;
        }

        .navbar a {
            float: left;
            font-size: 16px;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }

        .dropdown {
            float: right;
            overflow: hidden;
        }

        .dropdown .dropbtn {
            font-size: 16px;
            border: none;
            outline: none;
            color: white;
            padding: 14px 40px;
            background-color: inherit;
            font-family: inherit;
            margin: 0;
        }

        .navbar a:hover, .dropdown:hover .dropbtn {
            background-color: red;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown-content a {
            float: none;
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .dropdown-content a:hover {
            background-color: #ddd;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }

        table.ex1 {
            empty-cells: hide;
            border-collapse: collapse;
        }

        button.status {
            background-color: Transparent;
            background-repeat:no-repeat;
            border: none;
            cursor:pointer;
            overflow: hidden;
            outline:none;
            text-align: center;
            margin: 0 auto;
        }

        div.divstatus {
            text-align: center;

        }

        .unselectable {
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

    </style>
</head>
<body class="w3-light-grey">
<div class="navbar">
    <div class="dropdown">
        <a href="http://localhost:8080/manageOrders" class="w3-bar-item w3-button">Wstecz</a>
    </div>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-Gray">
            <h2>Obsługa zamówienia</h2>
        </div>
        <table class="ex1" style="width:100%">
            <tr>
                <th width="10%">Nr zamówienia</th>
                <th width="15">Towar</th>
                <th width="20%">Położenie</th>
                <th width="15%">Dane klienta</th>
                <th width="10">Adres dostawy</th>
                <th width="10">Uwagi</th>
                <th width="20%"></th>
            </tr>
            <%
                Order order = (Order) request.getAttribute("order");
                Address address = (Address) request.getAttribute("address");
                List<Item> orderItems = (List<Item>) request.getAttribute("itemList");
                User user = (User) request.getAttribute("user");
                List<Location> locationList = (List<Location>) request.getAttribute("locationList");


                out.println("<tr class=\"row\"><div class=\"line\">");
                out.println("<td class=\"unselectable\">" + order.getId()+ "</td>");
                out.println("<td class=\"unselectable\"></td>");
                out.println("<td class=\"unselectable\"></td>");
                out.println("<td class=\"unselectable\">"+user.getName()+ " " +user.getSurname()+"</td>");
                out.println("<td class=\"unselectable\">"+ address.toString() +"</td>");
                out.println("<td class=\"unselectable\"></td>");
                out.println("<td class=\"unselectable\">" +
                            "<div class=\"divstatus\"></br>" +
                            "<form method=\"post\">" +
                            "<button type=\"submit\" name=\"submit\" value="+"g" + order.getId()+">Generuj raport</button>" +
                            "</form>" +
                            "<button onclick=\"myFunction()\">Zatwierdź</button>" +
                            "</div>");
                out.println("</tr>");

                for(int i = 0; i < orderItems.size(); i++){
                    out.println("<tr class=\"w3-ul\">");
                    out.println("<td class=\"unselectable\"></td>");
                    out.println("<td class=\"unselectable\">" + orderItems.get(i).getName() + "</td>");
                    out.println("<td class=\"unselectable\">" + locationList.get(i).toString() + "</td>");
                    out.println("<td class=\"unselectable\"></td>");
                    out.println("<td class=\"unselectable\"></td>");
                    out.println("<td class=\"unselectable\"></td>");
                    out.println("<td class=\"unselectable\"></td>");
                    out.println("</tr>");
                }
            %>
        </table>

    </div>
    <p id="demo"></p>
</div>



<script>
    function myFunction() {
        var txt;
        if (confirm("Czy na pewno chcesz zatwierdzić zamówienie?")) {
            <%
            if(order.getStatus().equals("Oczekuje na obsluzenie")){
                OrdersDAO.updateOrderById(order.getId());
            %> txt  = "Zamówienie zostało zatwierdzone"; <%
            } else {
            %> txt  = "Zamówienie nie może być zatwierdzone"; <%
            }
            %>
        } else {
            txt = " ";
        }
        document.getElementById("demo").innerHTML = txt;
    }
</script>

</body>
</html>
</html>
