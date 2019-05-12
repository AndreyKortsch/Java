<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 14.04.2019
  Time: 19:34
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
<script type="text/javascript">
    function PrintElem(elem,id)
    {
        Popup($(elem).html(),id);
    }
    function Popup(data,id)
    {
        var mywindow = window.open('', id, 'height=1000,width=1000');
        mywindow.document.write('<html><head><title>my div</title>');
        mywindow.document.write('</head><body >');
        mywindow.document.write(data);
        mywindow.document.write('</body></html>');
        mywindow.document.close(); // necessary for IE >= 10
        mywindow.focus(); // necessary for IE >= 10
        mywindow.print();
        mywindow.close();
        return true;
    }
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
    .pagebreak {
        page-break-after: always;
    }
</style>
<style type="text/css" media="print">
    button {display: none; }
</style>
<%
    AutorizationClient clients2 = (AutorizationClient) request.getAttribute("Clients2");
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
    <h2>Заказы</h2>
</div>
<div class="w3-container w3-right-align w3-green">
    </br>
    <div class="w3-left-align w3-green"><label>Введите телефон для поиска</label></div>
    <input id="search" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" size="20" />
    <p></p>
</div>

<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <%
            List<Client> clients = (List<Client>) request.getAttribute("Clients");
            List<Zakaz> zakaz= (List<Zakaz>) request.getAttribute("Zakazs");
            List<Tovar> names = (List<Tovar>) request.getAttribute("Selected");
            String cat = (String) request.getAttribute("Kat");
            if (clients != null && !clients.isEmpty()) {

                out.println("<ul id=\"search-items\" class=\"w3-ul\">");
                int iA = 0;
                for (Client s : clients) {

                    int i=0;
                    out.println("<div id="+iA+">");
                    out.println("<li>");


                    out.println("<div class=\"w3-container w3-orange\">" +
                            "<h2>" + "Почта:" + s.getEmail() + "</h2>" +
                            "<h2>" + "Телефон:"+s.getTelefon() +"</h2>" +
                            "</div>");
                    out.println(
                            "<div class=\"w3-light-green w3-yellow w3-padding\">" );
                    out.println(
                            "<button class=\"w3-btn w3-red w3-round-large w3-right-align \" onclick=\"PrintElem('#"+iA+"',"+iA+")\">Печать" +
                                    "</button>" );

                    out.println("</div>");

                    for (Zakaz si : zakaz) {
                        if (s.getID() == si.getClientID()) {
                            out.println("<div class=\"w3-container w3-light-green\">");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Номер заказа:" + si.getID() + "</div>");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Дата:" + si.getDate() + "</div>");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Сумма заказа:" + si.getItog() + "</div>");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Ряд:" + si.getRjad() + "</div>");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Стойка:" + si.getStojka() + "</div>");
                            out.println("<div class=\"w3-container w3-left-align\">"
                                    + "Ярус:" + si.getJarus() + "</div>");
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
                            out.println(
                                        "<button class=\"w3-btn w3-red w3-round-large w3-right-align \" type=\"submit\" name=\"button3\"" +
                                                " value=" + si.getID() + ">Отметить выполнение" +
                                                "</button>" );
                            out.println("</div></form></div></div>");
                            i++;
                    }}

                    iA++;

                    out.println("</div>");
                    out.println("</li>");
                }
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
