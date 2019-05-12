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
<%@ page import="app.entities.Location" %>
<%@ page import="app.entities.AutorizationClient" %>
<html>
<head>
    <title>Заказы</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body>
<style>
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
    table {
        width: 100%; /* Ширина таблицы в процентах */
    }
    .col1 {
        width: 100px; /* Ширина ячейки */
    }
</style>
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Заказы</h1>
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
<div class="w3-container w3-center w3-orange">
    <h2>Схема склада</h2>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">

        <div class="w3-container w3-center w3-margin-bottom w3-padding">
        <img src ="../images/plan1.jpg" align="center" height="700" width="100%" alt="none">
        <br/>
        </div>
        <div class="w3-container w3-orange">
            <h2>Принятые заказы</h2>
        </div>
        <%
            List<Tovar> names = (List<Tovar>) request.getAttribute("Selected");
            List<Location> a=(List<Location>) request.getAttribute("Select");
            boolean click = (Boolean) request.getAttribute("er");
            if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                out.println("<table class=\"w3-container w3-yellow w3-justify\">");
                out.println("<thead class=\"w3-justify \">" +
                        "<tr>" +
                        "<th rowspan=\"2\">Номер позиции</th>" +
                        "<th rowspan=\"2\">Название</th>" +
                        "<th rowspan=\"2\">Количество</th>" +

                        "<th colspan=\"4\">Месторасположение</th>" +
                        "<th rowspan=\"2\">Отметить выполнение</th>" +
                        "</tr>" +
                        "<tr>"+
                        "<th>Ряд</th><th>Стойка</th><th>Ярус</th><th>Количество</th>"+
                        "</tr>"+
                        "</thead>");
                int i = 0;
                int s1=0;
                int s2=0;
                int sum = 0;
                int s3=0;
                for (Tovar s : names) {
                    int suma[] = new int[100];
                    suma[0]=0;
                    int k = 0;
                    int g=0;
                    int aas=-1;
                   //request.setAttribute("s",s.toString());
                    out.println("<tr>");
                    out.println(
                            "<td>" + (i + 1) + "</td>");
                    out.println(
                            "<td>" + s.getName() + "</td>"
                    );
                    out.println(
                            "<td>" + s.getNumbers() + "</td>");


                    out.println("<td colspan=\"4\">");
                    out.println("<table>");
                    for (Location sa : a) {
                        if ((sa.getTovarID() == s.getID())&&(aas!=0)) {
                            out.println(
                                    "<tr>");
                            out.println(
                                    "<td>" + sa.getRjad() + "</td>");
                            out.println(
                                    "<td>" + sa.getStojka() + "</td>");
                            out.println(
                                    "<td>" + sa.getJarus() + "</td>");

                            k++;
                            suma[k] = suma[k - 1] + sa.getNumbers();

                            if (suma[k] >= s.getNumbers()) {
                                aas = s.getNumbers() - suma[k - 1];
                                s3=sa.getNumbers()-aas;
                                s2 = sa.getID();
                                out.println(
                                        "<td>" + aas + "</td>");
                                out.println(
                                        "</tr>");
                                break;
                            } else {
                                s1 = sa.getID();
                                out.println(
                                        "<td>" + sa.getNumbers() + "</td>");
                                out.println(
                                        "</tr>");

                            }
                        }
                    }
                    out.println("</td>");
                    out.println("</table>");
                    out.println(
                            "<td><form method=\"post\"><input type=\"hidden\" name=\"name1\" value="+s.getNumbers()+">" +
                                    "<input type=\"hidden\" name=\"name2\" value="+i+">" +
                                    "<input type=\"hidden\" name=\"name3\" value="+s1+">" +
                                    "<input type=\"hidden\" name=\"name4\" value="+s2+">" +
                                    "<input type=\"hidden\" name=\"name5\" value="+s3+">" +
                                    "<button class=\"w3-btn w3-blue w3-round-large  \" type=\"submit\" name=\"button1\"" +
                                    " value="+s.getID()+" >Отметить" +
                                    "</button></form></td>");
                    out.println("</tr>");
                    i++;
                }

                out.println("</table>");
                out.println("</ul>");


            }
            else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                    "   <h5>Сейчас пока нет товаров!</h5>\n" +
                    "</div>");
        %>
    </div>
    <div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
        <button class="w3-btn w3-round-large" onclick="location.href='/list'">Назад</button>
    </div>
</div>
</body>
</html>
