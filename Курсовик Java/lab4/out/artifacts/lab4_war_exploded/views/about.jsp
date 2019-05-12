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
<%@ page import="app.entities.AutorizationClient" %>
<html>
<head>
    <title>О товаре</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Мое приложение</h1>
</div>
<%
    AutorizationClient clients = (AutorizationClient) request.getAttribute("Clients");
%>
<ul id="navbar">
    <li><a href='/list'>Каталог</a></li>
    <% if (clients.getRole().equals("завсклада"))
        out.println(
                "<li><a href='/edit'>Настройки</a></li>"+
                        "<li><a href='/dvischenie'>Приход/расход</a></li>"+
                        "<li><a href='/bidacha'>Выдача</a></li>");%>
    <% if (clients.getRole().equals("кладовщик"))
        out.println(
                "<li><a href='/zakazpostavshik'>Заказать товар</a></li>"+
                        "<li><a href='/sborkazakaz'>Комплектовка</a></li>"  );%>
    <% if (clients.getRole().equals("менеджер"))
        out.println(
                "<li><a href='/active'>Заказы</a></li>");%>
    <% if (clients.getRole().equals("грузчик"))
        out.println(
                "<li><a href='/sborka?INFO=0'>Сборка</a></li>");%>
    <% if (clients.getRole().equals("авторизированный пользователь"))
        out.println("<li><a href=\"/lischniykabinet\">Личный кабинет</a></li>");%>

</ul>
<script type="text/javascript">
    function change(objName, min, max, step) {
        var obj = document.getElementById(objName);
        var tmp = +obj.value + step;
        if (tmp<min) tmp=min;
        if (tmp>max) tmp=max;
        obj.value = tmp;
    }
</script>
<style>
INPUT[type="number"] {
background-color: navy;
color: #ffe;
}
#navbar {
    margin: 0;
    padding: 0;
    list-style-type: none;
    border: 2px solid #0066FF;
    border-radius: 20px 5px;
    width: 100%;
    text-align: center;
    background-color: #33ADFF;
}
#navbar li { display: inline; }
#navbar a {
    color: #fff;
    padding: 5px 10px;
    text-decoration: none;
    font-weight: bold;
    display: inline-block;
    width: 140px;
}
#navbar a:hover {
    border-radius: 20px 5px;
    background-color: #0066FF;
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
            input[type=number]::-webkit-inner-spin-button,
            input[type=number]::-webkit-outer-spin-button {-webkit-appearance: none;
                margin:0;}
        </style>
<%
    Tovar names = (Tovar) request.getAttribute("Tovar");
    Integer d = (Integer) request.getAttribute("qw");
    if (names != null ) {
        out.println("<ul class=\"w3-ul\">");
        out.println("<li class=\"w3-container w3-light-green\">");
        out.println("<div class=\"w3-container w3-left-align\">");

        out.println("<div class=\"w3-container clear:both\"><br/>" +
                "<img src=\"../images/" + names.getKart() + "\"align=\"left\" height=\"260\" width=\"260\" alt=\"none\">"
                + "<p><br/>" + names.getName());
        out.println("<br/>Цена:" + names.getPrice());
        out.println("<br/>Страна:" + names.getCountry());
        out.println("<br/>Описание:<br /> " + names.getDescription());
        out.println("<br/>Вес:" + names.getWeith());
        out.println("</p></div>");
        if (d==0) {
            if (names.getNumbers() > names.getNumbers2()) {
                out.println("<div class=\"w3-container w3-right-align w3-light-green w3-padding\">");
                out.println(
                        "<form method=\"post\"> " +
                                "<button class=\"w3-btn w3-yellow w3-round-large \" type=\"button\" onClick=\"change(0,1," + (names.getNumbers() - names.getNumbers2()) + ", -1);\" name=\"button2\"" +
                                " value=\"25\">-" +
                                "</button>" +
                                "<input type=\"hidden\" name=\"name1\" value=" + names.getID() + ">" +
                                "<input type=\"number\" name=\"mynumber\" id=\"0\" min=\"1\" max=" + (names.getNumbers() - names.getNumbers2()) + "step=\"1\" value=\"1\">" +
                                "<button class=\"w3-btn w3-yellow w3-round-large \"  type=\"button\" onClick=\"change(0,1," + (names.getNumbers() - names.getNumbers2()) + ",1);\" name=\"button3\"" +
                                " value=\"0\">+" + "</button>"
                );
                out.println(
                        "<button class=\"w3-btn w3-red w3-round-large \" type=\"submit\" name=\"button1\"" +
                                " value=" + names.getID() + ">Добавить в корзину" +
                                "</button></form></div>");
            } else {
                out.println("<div class=\"w3-container w3-light-green w3-right-align w3-padding\">");
                out.println("<label>Нет в наличии</label></div>");
            }
        }
        out.println("</li>");
        out.println("</div>");


        out.println("</ul>");
    }
%>
</div>
</div>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/list'">Назад</button>
</div>
</body>
</html>
