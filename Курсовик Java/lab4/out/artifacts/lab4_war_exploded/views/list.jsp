<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 14.11.2018
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Kategor" %>
<%@ page import="app.entities.Tovar" %>
<%@ page import="app.entities.AutorizationClient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог</title>
    <link rel="stylesheet" href="styles/w3.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
    <meta http-equiv="content-type" content="text/html"; charset="UTF-8">
</head>
<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Мое приложение</h1>
</div>
<script type="text/javascript">
    function change(objName, min, max, step) {
        var obj = document.getElementById(objName);
        var tmp = +obj.value + step;
        if (tmp<min) tmp=min;
        if (tmp>max) tmp=max;
        obj.value = tmp;
        }
</script>
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
<div class="w3-container w3-orange w3-center">
    <h2>Категории</h2>
</div>
    <div class="w3-container w3-yellow w3-opacity w3-right-align">
        <nav class="tablet-desktop-nav">
            <ul class="main-menu">

                <%
                List<Kategor> id1=(List<Kategor>) request.getAttribute("Kat");
                int is=1;
                if (id1 != null && !id1.isEmpty()) {

                    for (Kategor s2 : id1){

                                if (is %3==1) out.println("<div class=\"row\">");

                    out.println("<div class=\"col-3 w3-center\"> " +
                            "<a class='href' href='/list?INFO.java=" + s2.getID() + "'" +
                                        " >" +s2.toString() + "</a></div>");
                        if (is %3==0) out.println("</div>");
                    is++;}
                }
                %>

            </ul>
        </nav>
    </div>
<div class="w3-container w3-right-align w3-green">
    </br>
    <div class="w3-left-align w3-green">
        <form method="get" accept-charset="UTF-8">
            <label>Введите название товара для поиска</label>
            <div class="w3-left-align w3-green">
                <% String awe= (String) request.getAttribute("text");%>
                <input type="text" name="mytext" class="w3-border w3-round-large"
                       <%out.println(" value=\""+awe+"\"");%> style="width: 30%" size="20" />
                <button class="w3-btn w3-blue w3-round-large"  type="submit" id="button3" name="button3"
                        >Найти</button>
                </div></form>
        <p></p>
    </div>
</div>
<style>
    #elem {display:none;}
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
    #slideout {
        position: fixed;
        top: 40px;
        left: 0;
        height: 50Px;
        -webkit-transition-duration: 0.3s;
        -moz-transition-duration: 0.3s;
        -o-transition-duration: 0.3s;
        transition-duration: 0.3s;
        overflow-x: auto;
        width: 70px;

    }

    #slideout_inner {
        position: fixed;
        top: 40px;
        left: -500px;
        -webkit-transition-duration: 0.3s;
        -moz-transition-duration: 0.3s;
        -o-transition-duration: 0.3s;
        transition-duration: 0.3s;
        height: 500Px;
        overflow-y: auto;
        overflow-x: auto;
        width: 250px;
    }

    #slideout:hover {
        left: 50px;

    }

    #slideout:hover #slideout_inner {
        left: 0;
    }

    a {
        text-decoration: none; /* Отменяем подчеркивание у ссылки */
    }
    .fix {
    position: fixed;
    top: 100px;
    left: 30px;
    size: auto;
    padding: 0px;
    height: 500Px;
    border: 0px solid #000;
    opacity: 1;
    overflow-y: auto;
    overflow-x: auto;
    width: 70px;
        }
* {
    box-sizing: border-box;
}

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
    INPUT[type="number"] {
        background-color: navy;
        color: #ffe;
        width: 200px;
    }
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button {-webkit-appearance: none;
        margin:0;}
    INPUT[type="text"] {
        background-color: navy;
        color: #ffe;
        width: 200px;
    }
    input[type=text]::-webkit-inner-spin-button,
    input[type=text]::-webkit-outer-spin-button {-webkit-appearance: none;
        margin:0;}
</style>
    <div class="fix">
        <div id="slideout">
            <label>Корзина</label>
            <div id="slideout_inner">
    <div class="w3-card-2">
    <div class="w3-container w3-yellow">
    <%

        List <Tovar> as=(List<Tovar>) request.getAttribute("Anim");
        List <Integer> ass=(List<Integer>) request.getAttribute("list");
        if (as != null && !as.isEmpty()) {
            out.println("<div class=\"w3-hover-sand w3-center\">" +"Моя корзина" + "</div>");
            int i=0;
            for (Tovar a : as) {

                out.println("<div class=\"w3-hover-sand\">" );
                out.println( "<div class=\"w3-container w3-left-align\">" + a.getName()+"</div>");
                out.println( "<div class=\"w3-container w3-right-align\">" + "Количество:"+a.getNumbers()+"</div>");
                out.println( "<div class=\"w3-container w3-right-align\">" + "Цена:"+a.getPrice()*a.getNumbers()+"</div>");
                out.println(
                        "<div class=\"w3-right-align\"><form method=\"post\"> " +
                                "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button4\"" +
                                " value="+i+">Удалить"+
                                "</button></form></div>");

                out.println("</div>");
                i++;
            }
            out.println(
                    "<div class=\"w3-hover-sand w3-center\">" +

                            "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button5\"" +
                            " onclick=\"location.href='/zakaz'\">Перейти в корзину"+
                            "</button> "+
                            "</div>");

        }
        else out.println("<div class=\"w3-hover-sand w3-center\">" +"Ваша корзина пуста" + "</div>");
    %>
    </div>
  </div>
</div>
</div>
</div>
<div class="w3-container w3-center w3-margin-bottom w3-padding">
    <div class="w3-card-4">
        <% if (request.getAttribute("name") != null) {
            out.println("<div class=\"w3-container w3-center w3-green\" id = \"elem\" > Товар успешно добавлен в корзину</div >"+
                    "<script type = \"text/javascript\" >"+
                    "$(\"#elem\").show('slow');"+
                    "setTimeout(function() {"+
                    "$(\"#elem\").hide('slow');"+
                    "},2000);"+
                    "</script>");
        }
        %>
        <div class="w3-container w3-orange">
            <h2>Товары</h2>

        </div>
        <%
            Integer sd=(Integer) request.getAttribute("номер");
            Integer sd2=(Integer) request.getAttribute("номер2");
           List<Tovar> names = (List<Tovar>) request.getAttribute("Animals");
           List<Integer> id=(List<Integer>) request.getAttribute("list");
           if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                int i=0;
                for (int i2=sd;i2<sd2;i2++) {
                    out.println("<li class=\"w3-container w3-light-green\">");
                    out.println( "<a class=\"\" href='/about?INFO1.java="+
                            names.get(i2).getID() +"&d=0"+ "'>" );
                    out.println( "<div class=\"w3-container w3-right-align\">"
                            + names.get(i2).getPrice()+"</div>");
                    out.println( "<div class=\"w3-container w3-left-align\">" +
                            "<img src=\"../images/" +names.get(i2).getKart() +"\" height=\"100\" width=\"100\" alt=\"none\">"
                            +"  "+names.get(i2).getName()+"</div>");
                    out.println( "</a>");
                    if (names.get(i2).getNumbers()>names.get(i2).getNumbers2()) {
                        out.println("<div class=\"w3-container w3-light-green w3-right-align w3-padding\">");
                        out.println("<form method=\"post\">" +
                                "<button class=\"w3-btn w3-yellow w3-round-large right-align \" type=\"button\" onClick=\"change(" + i + ",1,"+(names.get(i2).getNumbers()-names.get(i2).getNumbers2())+", -1);\" name=\"button2\"" +
                                " value=\"-\">-" +
                                "</button>" +
                                "<input type=\"hidden\" name=\"name1\" value=" + i + ">" +
                                "<input type=\"number\" name=\"mynumber\"id=\"" + i + "\" min=\"1\" step=\"1\" max="+(names.get(i2).getNumbers()-names.get(i2).getNumbers2())+" value=\"1\">" +
                                "<button class=\"w3-btn w3-yellow w3-round-large right-align \" type=\"button\" onClick=\"change(" + i + ",1,"+(names.get(i2).getNumbers()-names.get(i2).getNumbers2())+",1);\" name=\"button3\"" +
                                " value=\"+\">+" + "</button>"
                        );
                        out.println(
                                "<button class=\"w3-btn w3-red w3-round-large right-align  type=\"submit\" id=\"button1\" name=\"button1\" " +
                                        "value=" + names.get(i2).toString() + ">Добавить в корзину" +
                                        "</button></form> </div>");
                    }
                    else
                    {
                        out.println("<div class=\"w3-container w3-light-green w3-right-align w3-padding\">");
                        out.println("<label>Нет в наличии</label></div>");
                    }
                    out.println("</li>");
                    i++;
                }
                out.println("</ul>");

            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                    "   <h5>В этой категории пока нет товаров!</h5>\n" +
                    "</div>");

        %>
    </div>
</div>
<%
    out.println("<div class=\"w3-container w3-center w3-yellow\"><br>");
    out.println("<form method=\"get\">");

    for (int i=0;i<(names.size() / 3)+1;i++)
            out.println(
            "<button class=\"w3-btn w3-red w3-round-large right-align  type=\"submit\" id=\"button1\" name=\"button38\" " +
                    "onclick=\'this.form.submit\' value=" + i + ">"+(i+1)+"" +
                    "</button> ");
    out.print("</form></div>");
%>
<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Назад</button>
</div>
</body>
</html>
