<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 22.04.2019
  Time: 14:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Новые заказы</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Мое приложение</h1>
</div>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    $(function(){
        $('input#search').on('input', function(){
            var str = $(this).val().toLowerCase();
            $('ul#search-items li').each(function(){
                if ($(this).text().toLowerCase().indexOf(str,$(this).text().toLowerCase().indexOf('Телефон:')+1) >=0)
                    $(this).show();
                else
                    $(this).hide();

            });

        });
    });
</script>
<style>
    .col-3 {
        height: 30px;
        width: 33.333%; /* Можно задать любую другую ширину блока */
        background: springgreen;
        margin: .5rem;
    }
    .col-3 a {
        display: block; /* Ссылка как блочный элемент */
        text-align: center; /* Выравнивание по центру */
        height: 100%; /* Высота на весь слой */

    }

    .row {
        display: flex;
        flex-flow: row nowrap;
        align-items: center;
        align-content: center;
        justify-content: space-between;
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
    a {
        text-decoration: none; /* Отменяем подчеркивание у ссылки */
    }
    table {
        width: 100%; /* Ширина таблицы в процентах */
    }
    .col1 {
        width: 100px; /* Ширина ячейки */
    }

</style>
<%
    AutorizationClient clients2 = (AutorizationClient) request.getAttribute("Clients");
%>
<ul id="navbar">
    <li><a href='/list'>Каталог</a></li>
    <% if (clients2.getRole().equals("завсклада"))
        out.println(
                "<li><a href='/edit'>Настройки</a></li>"+
                        "<li><a href='/dvischenie'>Приход/расход</a></li>"+
                        "<li><a href='/bidacha'>Выдача</a></li>");%>
    <% if (clients2.getRole().equals("кладовщик"))
        out.println(
                "<li><a href='/zakazpostavshik'>Заказать товар</a></li>"+
                        "<li><a href='/sborkazakaz'>Комплектовка</a></li>"  );%>
    <% if (clients2.getRole().equals("менеджер"))
        out.println(
                "<li><a href='/active'>Заказы</a></li>");%>
    <% if (clients2.getRole().equals("грузчик"))
        out.println(
                "<li><a href='/sborka?INFO=0'>Сборка</a></li>");%>
    <% if (clients2.getRole().equals("авторизированный пользователь"))
        out.println("<li><a href=\"/lischniykabinet\">Личный кабинет</a></li>");%>

</ul>
<div class="w3-container w3-orange w3-center">
    <h2>Личные данные</h2>
</div>
<div class="w3-container w3-left-align w3-green">
    <%
        AutorizationClient clients = (AutorizationClient) request.getAttribute("Clients");

        if (clients != null) {
            out.println("<ul id=\"search-items\" class=\"w3-ul\">");


            out.println("<li>");
            out.println("<div class=\"w3-container w3-green\">" +
                    "<h2>" + "Логин:" + clients.getLogin() + "</h2>" +
                    "<h2>" + "Пароль:"+clients.getPassword() +"</h2>" +
                    "<h2>" + "Почта:" + clients.getEmail() + "</h2>" +
                    "<h2>" + "Телефон:"+clients.getTelefon() +"</h2>" +
                    "</div>");
            }
            %>
</div>
<div class="w3-container w3-yellow w3-opacity w3-right-align">
    <nav class="tablet-desktop-nav">
        <ul class="main-menu">



            <div class="row">

                <div class="col-3 w3-center">
                    <a class='href' href='/lischniykabinet?INFO.java=new'> Активные  </a></div>
                <div class="col-3 w3-center">
                    <a class='href' href='/lischniykabinet?INFO.java=wait'
                    > Завершеннные  </a></div>
                <div class="col-3 w3-center">
                    <a class='href' href='/lischniykabinet?INFO.java=end'
                    > Отмененные  </a></div>
            </div>



        </ul>
    </nav>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <div class="w3-container w3-orange w3-center">
            <h2>Заказы</h2>
        </div>
        <%

            List<Zakaz> zakaz= (List<Zakaz>) request.getAttribute("Zakazs");
            List<Tovar> names = (List<Tovar>) request.getAttribute("Selected");
            String cat = (String) request.getAttribute("Kat");
            if (clients != null) {

                    int i = 0;
                    for (Zakaz si : zakaz) {
                        if (clients.getID() == si.getClientID()) {
                            out.println("<div class=\"w3-container w3-light-green\">");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Номер заказа:" + si.getID() + "</div>");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Дата:" + si.getDate() + "</div>");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Сумма заказа:" + si.getItog() + "</div>");
                            if (cat.equals("создан"))
                                out.println("<div class=\"w3-container w3-left-align\">"
                                        + "Статус заказа:" + si.getStatus() + "</div>");
                            //request.setAttribute("s",s.toString());
                            out.println("<div class=\"w3-container w3-light-green w3-right-align w3-padding\">");
                            out.println("<form method=\"post\">" +
                                    "<input type=\"hidden\" name=\"name1\" value=" + i + ">");
                            out.println("<table class=\" w3-container w3-padding w3-yellow w3-justify\">");
                            out.println("<thead class=\"w3-justify \">" +
                                    "<tr>" +
                                    "<th>Номер позиции</th>" +
                                    "<th>Название</th>" +
                                    "<th>Цена</th>" +
                                    "<th>Количество</th>" +
                                    "<th>Сумма</th>" +
                                    "</tr>" +
                                    "</thead>");
                            int j = 0;
                            int sum = 0;
                            for (Tovar s2 : names) {

                                if (s2.getZakazID() == si.getID()) {
                                    //request.setAttribute("s",s.toString());
                                    out.println("<tr>");
                                    out.println(
                                            "<td>" + (j + 1) + "</td>");
                                    out.println(
                                            "<td>" + s2.getName() + "</td>"
                                    );
                                    out.println(
                                            "<td>" + s2.getPrice() + "</td>");
                                    out.println(
                                            "<td>" + s2.getNumbers() + "</td>");
                                    out.println(
                                            "<td>" + s2.getPrice() * s2.getNumbers() + "</td>");
                                    j++;
                                    sum += s2.getPrice() * s2.getNumbers();
                                    out.println("</tr>");
                                }

                            }
                            out.println("</table>");
                            out.println("<div class=\"w3-container w3-green w3-right-align\">" + "Итого:" + sum + "</div>");

                            out.println(
                                    "<div class=\"w3-yellow w3-right-align w3-padding\">" );
                            out.println("</div></form></div></div>");
                            i++;
                        }

                    }
                    out.println("</li>");

                out.println("</ul>");
            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                    "   <h5>В этой категории пока нет заказов!</h5>\n" +
                    "</div>");

        %>

    </div>
</div>
</body>
</html>