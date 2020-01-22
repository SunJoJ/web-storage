<%@ page import="app.model.User" %><%--
  Created by IntelliJ IDEA.
  User: andrz
  Date: 12.01.2020
  Time: 23:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StorageWebApp</title>
    <style>
        body {
            font-family: Arial, Helvetica, sans-serif;
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

    </style>
</head>

<body class="w3-light-grey">
<div class="navbar">
    <div class="welcome">
        <% User currentUser = (User) (session.getAttribute("currentSessionUser"));%>
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


</body>
</html>
