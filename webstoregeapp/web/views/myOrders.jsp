<%@ page import="app.model.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="app.model.Item" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="app.model.Address" %><%--
  Created by IntelliJ IDEA.
  User: andrz
  Date: 12.01.2020
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Moje zamowienia</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <style>
        #myDIV {
            text-align: center;
            margin-top: 20px;
        }

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
        <button class="dropbtn">Mój profil
            <i class="fa fa-caret-down"></i>
        </button>
        <div class="dropdown-content">
            <a href="http://localhost:8080/myOrders">Moje zamówienia</a>
            <a href="http://localhost:8080/settings">Ustawienia</a>
            <a href="http://localhost:8080/">Wyloguj</a>
        </div>
    </div>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-Gray">
            <h2>Moje zamówienia</h2>
        </div>
        <table class="ex1" style="width:100%">
            <tr>
                <th width="10%">Nr zamówienia</th>
                <th width="18">Towar</th>
                <th width="16%">Cena</th>
                <th width="16%">Data złożenia</th>
                <th width="28%">Adres dostawy</th>
                <th width="12%"></th>
            </tr>
            <%

                List<Order> orderList = (ArrayList<Order>) request.getAttribute("orderList");
                HashMap<Integer, List<Item>> orderedItems = (HashMap<Integer, List<Item>>) request.getAttribute("itemList");

                Address address = (Address) request.getAttribute("address");


                if (orderList != null && !orderList.isEmpty()) {

                    for (Order o : orderList) {

                        out.println("<tr class=\"row\"><div class=\"line\">");
                        out.println("<td class=\"unselectable\">" + o.getId() + "</td>");
                        out.println("<td class=\"unselectable\"></td>");
                        out.println("<td class=\"unselectable\"></td>");
                        out.println("<td class=\"unselectable\">" + o.getDateOfOrder() + "</td>");
                        out.println("<td class=\"unselectable\">"+ address.toString() + "</td>");
                        out.println("<td class=\"unselectable\">" +
                                "<div class=\"divstatus\"></br>" +
                                "<form method=\"post\">" +
                                "<button type=\"submit\" name=\"submit\" value="+"s"+ o.getId()+" class=\"status\">Status</button>" +
                                "</form>" +
                                "<form method=\"post\">" +
                                "<button id=\"deleteButton\" class=\"status\" type=\"submit\" name=\"submit\" value="+"d"+ o.getId()+ ">Usuń</button>"+
                                "</div></form></td>");
                        out.println("</tr>");

                        List<Item> itemList = orderedItems.get(o.getId());

                        for (Item item: itemList) {
                            out.println("<tr class=\"w3-ul\">");
                            out.println("<td class=\"unselectable\"></td>");
                            out.println("<td class=\"unselectable\">"+item.getName()+"</td>");
                            out.println("<td class=\"unselectable\">" +item.getCost()+ " zł</td>");
                            out.println("<td class=\"unselectable\"></td>");
                            out.println("<td class=\"unselectable\"></td>");
                            out.println("<td class=\"unselectable\"></td>");
                            out.println("</tr>");
                        }

                    }

                } else {
                    out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                            +
                            "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                            "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">?</span>\n" +
                            "   <h5>There are no orders yet!</h5>\n" +
                            "</div>");
                }
            %>

        </table>
    </div>
</div>


</body>
</html>

