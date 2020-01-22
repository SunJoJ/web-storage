<%@ page import="app.model.User" %>
<%@ page import="app.model.Package" %>
<%@ page import="app.model.Address" %>
<%@ page import="app.model.Order" %><%--
  Created by IntelliJ IDEA.
  User: andrz
  Date: 13.01.2020
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StorageWebApp</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
            background: #eeeeee;
        }

        th,
        td,
        tr {
            text-align: left;
            width: 100px;
            height: 50px;
        }

        .navbar {
            overflow: hidden;
            background-color: #333;
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
            padding: 14px 36px;
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
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
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

        .navbar .welcome {
            color: white;
            padding: 14px 16px;
            position: absolute;
            float: left;
        }

        .panel {
            display: inline-block;
            background: #ffffff;
            min-height: 500px;
            height: 100px;
            box-shadow:0px 0px 5px 5px #C9C9C9;
            -webkit-box-shadow:2px 2px 5px #C9C9C9;
            -moz-box-shadow:2px 2px 5px 5px #C9C9C9;
            padding: 30px;
            margin-left: auto;
            margin-right: auto;
            display: block;
            width: 70%;

        }

        table.ex1 {
            empty-cells: hide;
            border-collapse: collapse;
            table-layout: fixed;
        }

        button.back {
            padding: 14px 16px;
            float: right;
        }

    </style>
</head>

<body class="w3-light-grey">
<div class="navbar">
    <div class="welcome">
        <% User currentUser = (User) (session.getAttribute("currentSessionUser"));
            Package orderedPackage = (Package) request.getAttribute("orderedPackage");
            Address address = (Address) request.getAttribute("address");
            String orderId = request.getParameter("submit");
            Order order = (Order) request.getAttribute("order");
                                                                                    %>
        Welcome, <%= currentUser.getName()%>
    </div>

    <div class="dropdown">
        <button class="dropbtn">Mój profil
            <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
            <a href="http://localhost:8080/myOrders">Zamówienia</a>
            <a href="http://localhost:8080/webstorage_war_exploded/settings">Ustawienia</a>
            <a href="#">Wyloguj</a>
        </div>
    </div>
</div>
<div class="panel">
    <div>
        <table class="ex1" style="width:50%">
            <tr>
                <td><b>Numer zamówienia </b></td>
                <td><%= orderId.substring(1) %></td>
            </tr>
            <tr>
                <td><b>Adres dostawy </b></td>
                <td><%=address.toString()%></td>
            </tr>
            <tr>
                <td><b>Data złożenia </b></td>
                <td><%=order.getDateOfOrder()%></td>
            </tr>
            <tr>
                <td><b>Data obsłużenia </b></td>
                <td><%=orderedPackage.getDateOfProcess()%></td>
            </tr>
            <tr>
                <td><b>Data wysłania </b></td>
                <td><%=orderedPackage.getDateOfShipment()%></td>
            </tr>
            <tr>
                <td><b>Data dostarczenia </b></td>
                <td><%=orderedPackage.getDateOdDelivery()%></td>
            </tr>
            <tr>
                <td><b>Status </b></td>
                <td><%=order.getStatus()%></td>
            </tr>
        </table>

    </div>
    <button class="back" onclick="location.href='/myOrders'">Wróć</button>
</div>
</body>
