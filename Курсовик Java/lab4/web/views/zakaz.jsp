<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 16.03.2019
  Time: 22:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Tovar" %>
<html>
<head>
    <title>Список_животных</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body>
<style>
    table {
        width: 100%; /* Ширина таблицы в процентах */
    }
    .col1 {
        width: 100px; /* Ширина ячейки */
    }
</style>
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Корзина</h1>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-orange">
            <h2>Ваш заказ</h2>
        </div>

        <%
            List<Tovar> names = (List<Tovar>) request.getAttribute("Selected");
           boolean click = (Boolean) request.getAttribute("er");
            if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                out.println("<table class=\"w3-container w3-yellow w3-justify\">");
                out.println("<thead class=\"w3-justify \">"+
                "<tr>"+
            "<th>Номер позиции</th>"+
            "<th>Название</th>"+
             "<th>Цена</th>"+
            "<th>Количество</th>"+
            "<th>Сумма</th>"+
            "</tr>"+
            "</thead>");
                out.println("</li>");
                int i=0;
                int sum=0;
                for (Tovar s : names) {


                    //request.setAttribute("s",s.toString());
                    out.println("<tr>");
                    out.println(
                            "<td>"+(i+1)+ "</td>");
                    out.println(
                            "<td>"+s.getName()+"</td>"
                    );
                    out.println(
                            "<td>"+s.getPrice()+ "</td>");
                    out.println(
                            "<td>"+s.getNumbers()+ "</td>");
                    out.println(
                            "<td>"+s.getPrice()*s.getNumbers()+ "</td>");
                    i++;
                    sum+=s.getPrice()*s.getNumbers();
                    out.println("</tr>");

                }
                out.println("</table>");
                out.println("<div class=\"w3-container w3-green w3-right-align\">"+"Итого:"+sum+"</div>");

            }
            out.println(
                    "<div class=\"w3-hover-sand w3-orange w3-right-align\">" +
                            "<button class=\"w3-btn w3-blue w3-round-large  \" type=\"submit\" name=\"button1\"" +
                            " onclick=\"location.href='/list'\">Назад"+
                            "</button>" +
                            "<form method=\"post\">"+
                    "<button class=\"w3-btn w3-blue w3-round-large \" type=\"submit\" name=\"button2\"" +
                            " >Оформить заказ"+
                            "</button></form> "+
                            "</div>");
            if (request.getAttribute("name") != null) {
           out.println("<div class=\"w3-card-4\">"+
        "<div class=\"w3-container w3-center w3-green\">"+
            "<h2>Введите ваши данные</h2>"+
        "</div>"+
        "<form method=\"post\" class=\"w3-selection w3-left-align w3-light-grey w3-padding\">"+
            "<label>Телефон:"+
                "<input type=\"tel\" name=\"number_voler\" required " +
                           "pattern=\"7[0-9]{10}\" "+
                "class=\"w3-input w3-animate-input w3-border w3-round-large\" style=\"width: 30%\"><br />"+
            "</label>"+
            "<label>Почта:"+
                "<input type=\"email\" name=\"name\" required class=\"w3-input w3-animate-input w3-border w3-round-large\" style=\"width: 30%\"><br />"+
            "</label>"+
                   "<input type=\"checkbox\" name=\"option\" value=\"a1\">Прислать копию заказа на email<Br><Br>"+
            "<button type=\"submit\" class=\"w3-btn w3-green w3-round-large w3-margin-bottom\" " +
                "name=\"button5\" '\">Принять</button>"+
        "</form></div>"
);
            }

        %>
    </div>
</div>
</body>
</html>
