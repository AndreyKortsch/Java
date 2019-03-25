<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 14.11.2018
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавить животное</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Мое приложение</h1>
</div>

<div class="w3-container w3-padding">
    <%
        if (request.getAttribute("name") != null) {
            out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n" +
                    "   <h5>Animal '" + request.getAttribute("name") + "' added!</h5>\n" +
                    "</div>");
        }
    %>
    <div class="w3-card-4">
        <div class="w3-container w3-center w3-green">
            <h2>Добавить животное</h2>
        </div>
        <form method="post" class="w3-selection w3-light-grey w3-padding">
            <label>Номер вольера:
                <input type="text" name="number_voler" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Имя:
                <input type="text" name="name" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Номер вида:
                <input type="text" name="number_vid" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <label>Дата приезда:
                <input type="text" name="date" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%"><br />
            </label>
            <button type="submit" class="w3-btn w3-green w3-round-large w3-margin-bottom">Принять</button>
        </form>
    </div>
<%
    if (request.getAttribute("An") != null) {
        out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n" +
                "   <h5>Animal '" + request.getAttribute("An").toString() + "' added!</h5>\n" +
                "</div>");
    }
%>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Back to main</button>
</div>
</body>
</html>