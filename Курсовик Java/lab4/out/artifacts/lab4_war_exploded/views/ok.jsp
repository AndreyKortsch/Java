<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 17.03.2019
  Time: 20:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Заказ</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Уведомление</h1>
</div>>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-orange">
            <h2>
            <%

                    String names = (String) request.getAttribute("result");
                    out.println(names);


            %>
            </h2>
        </div>
    </div>
    </div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">На главную</button>
</div>
</body>
</html>
