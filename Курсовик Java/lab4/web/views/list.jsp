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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список_животных</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Мое приложение</h1>
</div>
<ul id="navbar">
    <li><a href='/list'>Каталог</a></li>
    <li><a href='/add'>Новости</a></li>
    <li><a href="#">Контакты</a></li>
    <li><a href="#">О нас</a></li>
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
                                        ") >" +s2.toString() + "</a></div>");
                        if (is %3==0) out.println("</div>");
                    is++;}
                }
                %>

            </ul>
        </nav>
    </div>

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
        width: 100px;
    }
    #navbar a:hover {
        border-radius: 20px 5px;
        background-color: #0066FF;
    }
    #slideout {
        position: fixed;
        top: 40px;
        left: 0;
        height: 500Px;
        -webkit-transition-duration: 0.3s;
        -moz-transition-duration: 0.3s;
        -o-transition-duration: 0.3s;
        transition-duration: 0.3s;
        overflow-x: auto;
        width: 250px;

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
    width: 250px;
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
</style>
<style>
    INPUT[type="number"] {
        background-color: navy;
        color: #ffe;
    }
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
                out.println( "<div class=\"w3-container w3-right-align\">" + a.getPrice()*a.getNumbers()+"</div>");
                out.println( "<div class=\"w3-container w3-left-align\">" + a.getName()+"</div>");
                out.println( "<div class=\"w3-container w3-right-align\">" + "Количество:"+a.getNumbers()+"</div>");
                out.println(
                        "<div class=\"w3-right-align\"><form method=\"post\"> " +
                                "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button4\"" +
                                " value="+i+">Удалить"+
                                "</button></form></div></div>");
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
        <div class="w3-container w3-orange">
            <h2>Товары</h2>
        </div>
        <%
           List<Tovar> names = (List<Tovar>) request.getAttribute("Animals");
           List<Integer> id=(List<Integer>) request.getAttribute("list");
           if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                int i=0;
                for (Tovar s : names) {
                    out.println("<li class=\"w3-container w3-light-green\">");
                    out.println( "<div class=\"w3-container w3-right-align\">"
                            + + s.getPrice()+"</div>");
                    out.println( "<div class=\"w3-container w3-left-align\">" +
                            "<img src=\"../images/" +s.getKart() +"\" height=\"100\" width=\"100\" alt=\"none\">"
                            + "<a class='href' href='/about?INFO1.java="+
                             s.getID() + "'" +
                            ") >"+s.getName()+"</a></div>");
                    //request.setAttribute("s",s.toString());
                    out.println("<div class=\"w3-container w3-light-green w3-right-align w3-padding\">");
                    out.println(
                            "<form method=\"post\"> " +
                                    "<button class=\"w3-btn w3-yellow w3-round-large right-align \" type=\"submit\" name=\"button2\"" +
                            " value=\"25\">-"+
                            "</button>"+
                                    "<input type=\"hidden\" name=\"name1\" value="+i+">"+
                            "<input type=\"number\" name=\"mynumber\" min=\"1\" step=\"1\" value=\"1\">"+
                            "<button class=\"w3-btn w3-yellow w3-round-large right-align \" type=\"submit\" name=\"button3\"" +
                             " value=\"0\">+"+ "</button>"
                    );
                    out.println(
                    "<button class=\"w3-btn w3-red w3-round-large right-align \" type=\"submit\" name=\"button1\"" +
                            " value="+s.toString()+">Добавить в корзину"+
                    "</button></form></div> </li>");
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

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Назад</button>
</div>
</body>
</html>
