<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 29.03.2019
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Kategor" %>
<%@ page import="app.entities.Tovar" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Мое приложение</h1>
</div>
<style>
INPUT[type="number"] {
background-color: navy;
color: #ffe;
}
</style>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-orange">
            <h2>О товаре</h2>
        </div>
        <style>
            .col-4 a {
                display: block; /* Ссылка как блочный элемент */
                text-align: center; /* Выравнивание по центру */
                height: 100%; /* Высота на весь слой */

            }
        </style>
<%
    Tovar names = (Tovar) request.getAttribute("Tovar");
    if (names != null ) {
out.println("<ul class=\"w3-ul\">");
    out.println("<li class=\"w3-container w3-light-green\">");
        out.println( "<div class=\"w3-container w3-left-align\">");

    out.println( "<div class=\"w3-container\">" +
    "<img src=\"../images/" +names.getKart() +"\" height=\"260\" width=\"260\" alt=\"none\">"
    + "<br/>"+names.getName());
        out.println( "<br/>Цена:"+names.getPrice());
        out.println( "<br/>Страна:"+names.getCountry());
        out.println( "<br/>Описание:<br /> "+names.getDescription());
        out.println("<br/>Вес:"+names.getWeith());
        out.println("</div>");
    //request.setAttribute("s",s.toString());
    out.println("<div class=\"w3-container w3-right-align w3-light-green w3-padding\">");
    out.println(
    "<form method=\"post\"> " +
        "<button class=\"w3-btn w3-yellow w3-round-large \" type=\"submit\" name=\"button2\"" +
        " value=\"25\">-"+
        "</button>"+
        "<input type=\"hidden\" name=\"name1\" value="+names.getID()+">"+
        "<input type=\"number\" name=\"mynumber\" min=\"1\" step=\"1\" value=\"1\">"+
        "<button class=\"w3-btn w3-yellow w3-round-large \" type=\"submit\" name=\"button3\"" +
        " value=\"0\">+"+ "</button>"
        );
        out.println(
        "<button class=\"w3-btn w3-red w3-round-large \" type=\"submit\" name=\"button1\"" +
        " value="+names.getID()+">Добавить в корзину"+
        "</button></form></div> </li>");
        out.println( "</div>");

    };
    out.println("</ul>");

%>
</div>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/list'">Назад</button>
</div>
</body>
</html>
