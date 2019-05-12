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
<%@ page import="app.entities.AutorizationClient" %>
<html>
<head>
    <title>Корзина</title>
    <link rel="stylesheet" href="styles/w3.css">
    <script src="js/jquery.js"></script>
    <!-- Подключение jQuery плагина Masked Input -->
    <script src="js/jquery.maskedinput.min.js"></script>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
    <script src="http://site.ru/jquery.inputmask.min.js"></script>
    <script src="js/jquery.validate.js"></script>
    <script src="jv/dist/jquery.validate.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.slim.min.js\">
</script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js\"></script>
    <script src="js/jquery.validate.js"></script>
    <script>
        jQuery.validator.addMethod("checkMask", function(value, element) {
return /\8\(\d{3}\)\d{3}-\d{2}-\d{2}/g.test(value);
});

$('form').validate({
rules: {
ph: {
checkMask: true
}
},
messages: {
ph: {
checkMask: "Введите полный номер телефона"
}
}
});
$('.js-phone').mask("8(999)999-99-99", {autoclear: false});
</script>
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
input[type=number]::-webkit-inner-spin-button,
input[type=number]::-webkit-outer-spin-button {-webkit-appearance: none;
    margin:0;}
</style>

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Корзина</h1>
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
            "<th></th>"+
            "</tr>"+
            "</thead>");
                out.println("</li><form method=\"post\">");
                int i=0;
                int sum=0;
                for (Tovar s : names) {
                    out.println("<tr>");
                    out.println(
                            "<td>"+(i+1)+ "</td>");
                    out.println(
                            "<td>"+s.getName()+"</td>"
                    );
                    out.println(
                            "<td>"+s.getPrice()+ "</td>");
                    out.println(
                            "<td>");
                    out.println(  "<button class=\"w3-btn w3-green w3-round-large right-align \" type=\"button\" onClick=\"change("+i+",1,10, -1);\" name=\"button2\"" +
                                    " value=\"-\">-"+
                                    "</button>"+
                                    "<input type=\"hidden\" name=\"name1\" value="+i+">"+
                                    "<input type=\"number\" name=\""+i+"\" id=\""+i+"\" min=\"1\" step=\"1\" value=\""+s.getNumbers()+"\">"+
                                    "<button class=\"w3-btn w3-green w3-round-large right-align \" type=\"button\" onClick=\"change("+i+",1,10,1);\" name=\"button3\"" +
                                    " value=\"+\">+"+ "</button>"
                            );
                    out.println( "</td>");
                    out.println(
                            "<td>"+s.getPrice()*s.getNumbers()+ "</td>");

                    out.println(
                            "<td><div class=\"w3-right-align\"><form method=\"post\"> " +
                                    "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button6\"" +
                                    " value="+i+">Удалить"+
                                    "</button></td>");
                    i++;
                    sum+=s.getPrice()*s.getNumbers();
                    out.println("</tr>");

                }
                out.println("</table>");
                out.println("<div class=\"w3-container w3-green w3-right-align\">"+"Итого:"+sum+"</div>");

            }
            out.println(
                    "<div class=\"w3-hover-sand w3-orange w3-right-align\">" +

                            "<button class=\"w3-btn w3-blue w3-round-large \" type=\"submit\" name=\"button10\"" +
                            " >Пересчитать"+
                            "</button></form>"+


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
        "<form method=\"post\" class=\"w3-selection w3-left-align w3-light-grey w3-padding\"" +
                           "onsubmit=\"return ValidPhone()\">"+
            "<label>Телефон:"+
                "<input type=\"text\" id=\"phone\" name=\"number_voler\" required " +
                           "placeholder=\"89991112233\" "+
                "class=\"w3-input w3-animate-input w3-border w3-round-large\" style=\"width: 30%\">");
                if (request.getAttribute("name1") != null) {
                    out.println(
                            "<h5>Неверный ввод телефона!</h5>\n"  );
                }
                  out.println("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.slim.min.js\">"+
                   "</script>"+
    "<script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js\"></script>"+
                   "<script>"+
                   "$(function(){"+
                    //2. Получить элемент, к которому необходимо добавить маску
                    "$(\"#phone\").mask(\"8(999)999-99-99\");"+
                    "});"+
                    "</script>"+
                    "<br />"+
            "</label>"+
            "<label>Почта:"+
                "<input type=\"email\" name=\"name\" placeholder=\"user@mail.com\" required class=\"w3-input w3-animate-input w3-border w3-round-large\" style=\"width: 30%\"><br />"+
            "</label>"+

                   "<input type=\"checkbox\" class=\"w3-round w3-padding w3-border\"name=\"option\" value=\"a1\">Прислать копию заказа на email<Br><Br>"+

                   "<button type=\"submit\" class=\"w3-btn w3-green w3-round-large w3-margin-bottom\" " +
                "name=\"button5\">Принять</button>"+
        "</form></div>"
);
            }

        %>
    </div>
</div>
</body>
</html>
