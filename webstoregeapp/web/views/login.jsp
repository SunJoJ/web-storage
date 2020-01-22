<%--
  Created by IntelliJ IDEA.
  User: andrz
  Date: 12.01.2020
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <title>StorageWebApp</title>
</head>
<body class="w3-light-grey">
<div class="w3-card-4">
    <div class="w3-container w3-center w3-grey">
        <h2>Logowanie</h2>
    </div>
    <form method="post" class="w3-selection w3-light-grey w3-padding">
        <label>Login:
            <input type="text" name="un" class="w3-input w3-border w3-round-large" style="width: 30%"><br/>
        </label>
        <label>Hasło:
            <input type="password" name="pw" class="w3-input w3-border w3-round-large" style="width: 30%"><br/>
        </label>
        <button type="submit" class="w3-btn w3-grey w3-round-large w3-margin-bottom">Zatwierdź</button>
    </form>
</div>
</body>
</html>
