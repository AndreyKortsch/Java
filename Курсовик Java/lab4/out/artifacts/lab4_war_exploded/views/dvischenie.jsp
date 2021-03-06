<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 02.05.2019
  Time: 10:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.*" %>
<html>
<head>
    <title>Накладные</title>
    <link rel="stylesheet" href="styles/w3.css">
    <!-- Подключение jQuery плагина Masked Input -->
</head>
<body>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<script type="text/javascript">
    (function($){$.fn.selectbox=function(){$(this).each(function(){var select=$(this);if(select.prev('span.selectbox').length<1){function doSelect(){var option=select.find('option');var optionSelected=option.filter(':selected');var optionText=option.filter(':first').text();if(optionSelected.length)optionText=optionSelected.text();var ddlist='';for(i=0;i<option.length;i++){var selected='';var disabled=' class="disabled"';if(option.eq(i).is(':selected'))selected=' class="selected sel"';if(option.eq(i).is(':disabled'))selected=disabled;ddlist+='<li'+selected+'>'+option.eq(i).text()+'</li>';}var selectbox=$('<span class="selectbox" style="display:inline-block;position:relative">'+'<div class="select" style="float:left;position:relative;z-index:10000"><div class="text">'+optionText+'</div>'+'<b class="trigger"><i class="arrow"></i></b>'+'</div>'+'<div class="dropdown" style="position:absolute;z-index:9999;overflow:auto;overflow-x:hidden;list-style:none">'+'<ul>'+ddlist+'</ul>'+'</div>'+'</span>');select.before(selectbox).css({position:'absolute',top:-9999});var divSelect=selectbox.find('div.select');var divText=selectbox.find('div.text');var dropdown=selectbox.find('div.dropdown');var li=dropdown.find('li');var selectHeight=selectbox.outerHeight();if(dropdown.css('left')=='auto')dropdown.css({left:0});if(dropdown.css('top')=='auto')dropdown.css({top:selectHeight});var liHeight=li.outerHeight();var position=dropdown.css('top');dropdown.hide();divSelect.click(function(){var topOffset=selectbox.offset().top;var bottomOffset=$(window).height()-selectHeight-(topOffset-$(window).scrollTop());if(bottomOffset<0||bottomOffset<liHeight*6){dropdown.height('auto').css({top:'auto',bottom:position});if(dropdown.outerHeight()>topOffset-$(window).scrollTop()-20){dropdown.height(Math.floor((topOffset-$(window).scrollTop()-20)/liHeight)*liHeight);}}else if(bottomOffset>liHeight*6){dropdown.height('auto').css({bottom:'auto',top:position});if(dropdown.outerHeight()>bottomOffset-20){dropdown.height(Math.floor((bottomOffset-20)/liHeight)*liHeight);}}$('span.selectbox').css({zIndex:1}).removeClass('focused');selectbox.css({zIndex:2});if(dropdown.is(':hidden')){$('div.dropdown:visible').hide();dropdown.show();}else{dropdown.hide();}return false;});li.hover(function(){$(this).siblings().removeClass('selected');});var selectedText=li.filter('.selected').text();li.filter(':not(.disabled)').click(function(){var liText=$(this).text();if(selectedText!=liText){$(this).addClass('selected sel').siblings().removeClass('selected sel');option.removeAttr('selected').eq($(this).index()).attr('selected',true);selectedText=liText;divText.text(liText);select.change();}dropdown.hide();});dropdown.mouseout(function(){dropdown.find('li.sel').addClass('selected');});select.focus(function(){$('span.selectbox').removeClass('focused');selectbox.addClass('focused');}).keyup(function(){divText.text(option.filter(':selected').text());li.removeClass('selected sel').eq(option.filter(':selected').index()).addClass('selected sel');});$(document).on('click',function(e){if(!$(e.target).parents().hasClass('selectbox')){dropdown.hide().find('li.sel').addClass('selected');selectbox.removeClass('focused');}});}doSelect();select.on('refresh',function(){select.prev().remove();doSelect();})}});}})(jQuery)
    jQuery(function(){
        jQuery('select').selectbox();
    });
</script>
<style>
    *{
        padding: 0;
        margin: 0;
    }

    .selectbox {
        vertical-align: middle;
        cursor: pointer;
        margin: 4px 0;


    }
    .selectbox .select {
        width: 300px;
        height: 30px;
        line-height: 30px;
        padding: 0 45px 0 10px;
        font-size: 16px;
        font-family: calibri, arial, verdana, tahoma;
        color: #000;
        background: #FF7700;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;

    }
    .selectbox .select:hover {
        background-color: #E6E6E6;
        background-position: 0 -10px;

    }
    .selectbox .select:active {
        background: #f5f5f5;
        box-shadow: inset 0 1px 3px rgba(0,0,0,0.15);


    }
    .selectbox.focused .select {
        border: 1px solid #5794BF;
    }
    .selectbox .select .text {
        display: block;
        width: 100%;
        white-space: nowrap;
        text-overflow: ellipsis;
        overflow: hidden;

    }
    .selectbox .trigger {
        position: absolute;	top: 0;	right: 0;
        width: 34px;

    }
    .selectbox .trigger .arrow {
        position: absolute; top: 14px;	right: 12px;
        border-left: 7px solid transparent;
        border-right: 7px solid transparent;
        border-top: 7px solid #000;
        width: 0;
        height: 0;
        overflow: hidden;
        opacity: 0.3;
        filter: alpha(opacity=30);

    }
    .selectbox:hover .arrow {
        opacity: 1;
        filter: alpha(opacity=100);

    }
    .selectbox .dropdown {
        top: 33px;
        width: 240px;
        margin: 0;
        padding: 4px 0;
        background: #FFF;
        box-shadow: 0 2px 10px rgba(0,0,0,0.2);
        font-size: 16px;
        font-family: calibri, arial, verdana, tahoma;
        color: #000;
        -webkit-border-radius: 3px;
        -moz-border-radius: 3px;
        border-radius: 3px;
    }
    .selectbox li {
        padding: 5px 10px 6px;
        color: #231F20;
        list-style: none;

    }
    .selectbox li.selected {
        background: #ccc;
        color: #FFF;

    }

    .selectbox li:hover {
        background: #FF7700;
        color: #FFF;
    }
    .selectbox li.disabled {
        color: #AAA;
    }
    .selectbox li.disabled:hover {
        background: none;
    }
    table {
        width: 100%; /* Ширина таблицы в процентах */
    }
    .col1 {
        width: 100px; /* Ширина ячейки */
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
        width: 150px;

    }
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button {-webkit-appearance: none;
        margin:0;}
    * {
        box-sizing: border-box;
    }

    .col-3 {
        height: 30px;
        width: 50%; /* Можно задать любую другую ширину блока */
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
    a {
        text-decoration: none; /* Отменяем подчеркивание у ссылки */
    }
</style>

<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>Накладные</h1>
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
<div class="w3-container w3-yellow w3-opacity w3-right-align">
    <nav class="tablet-desktop-nav">
        <ul class="main-menu">

            <%


                        out.println("<div class=\"row\">");
                        out.println("<div class=\"col-3 w3-center\"> " +
                                "<a class='href' href='/dvischenie?INFO.java=1'" +
                                ">Приход</a></div>");
                out.println("<div class=\"col-3 w3-center\"> " +
                        "<a class='href' href='/dvischenie?INFO.java=2'" +
                        ">Расход</a></div>");
                        out.println("</div>");

                 %>

        </ul>
    </nav>
</div>
<%
    if (request.getAttribute("naem") != null) {
        out.println("<div class=\"w3-panel w3-green w3-display-container w3-card-4 w3-round\">\n" +
                "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-green w3-border w3-border-green w3-hover-border-grey\">×</span>\n" +
                "   <h5>Не все поля выбраны</h5>\n" +
                "</div>");
    }
%>
<% Integer names3 = (Integer) request.getAttribute("Info");
if (names3==1) {
    out.println("<div class=\"w3-container w3-center w3-margin-bottom w3-padding\">" +
            "<div class=\"w3-card-4\">" +
            "<div class=\"w3-container w3-orange\">" +
            "<h2>Приходная накладная</h2>" +
            "</div>" +
            "<div class=\"w3-container w3-left-align w3-yellow w3-padding\">" +
            "<form method=\"post\">" +
            "<label>Поставщик:</label>" +
            "<select name=\"myselect2\" onchange='this.form.submit()' >");

    List<Postavshik> names1 = (List<Postavshik>) request.getAttribute("Post");
    List<Zakaz> names5 = (List<Zakaz>) request.getAttribute("names5");
    int as = (Integer) request.getAttribute("Am");
    int as2 = (Integer) request.getAttribute("Am2");
    int k1 = 0;
    System.out.println(as);
    for (Postavshik a : names1) {
        if (as == k1) out.println("<option value=" + k1 + " selected>" + a.getOrganizationname() + "</option>");
        else out.println("<option value=" + k1 + ">" + a.getOrganizationname() + "</option>");
        k1++;
    }
    out.println("</select></form>");
    if (names5.size()!=0){

    out.println("<form method=\"post\"><label>Заказ:</label>" +
                "<select name=\"myselect6\" onchange='this.form.submit()' >");

        int k2 = 0;
        System.out.println(as);
        for (Zakaz a : names5) {
            if (as2 == k2) out.println("<option value=" + k2 + " selected>" + a.getID() + "</option>");
            else out.println("<option value=" + k2 + ">" + a.getID() + "</option>");
            k2++;
        }
        out.println("</select><br>");
                }
            out.println("</form>" +
            "</div>");
    List<Tovar> names = (List<Tovar>) request.getAttribute("names6");
    if (names != null  && !names.isEmpty()) {
        out.println("<ul class=\"w3-ul\">");
        out.println("<table class=\"w3-container w3-yellow w3-justify\">");
        out.println("<thead class=\"w3-justify \">" +
                "<tr>" +
                "<th rowspan=\"2\">Номер позиции</th>" +
                "<th rowspan=\"2\">Название</th>" +
                "<th rowspan=\"2\">Количество</th>" +
                "<th colspan=\"3\">Месторасположение</th>" +
                "</tr>" +
                "<tr>" +
                "<th>Ряд</th><th>Стойка</th><th>Ярус</th>" +
                "</tr>" +
                "</thead>");

        List<Tovar> tovar = (List<Tovar>) request.getAttribute("Tovar");
        List<Location> tovars = (List<Location>) request.getAttribute("Animals");
        List<Location> tova = (List<Location>) request.getAttribute("loc");
        int i = 0;


            for (Tovar s : names) {
                out.println("<tr><form method=\"post\">");
                out.println(
                        "<td>" + (i + 1) + "</td>");
                out.println(
                        "<td>" + s.getName() + "</td>"
                );
                out.println(
                        "<td>" + s.getNumbers() + "</td>"
                );
                out.println(
                        "<td colspan=\"3\">");
                out.println(
                        "<select name=\"myselect89\" onchange='this.form.submit()' >" +
                                "<option disabled selected value> Выберите расположение </option>");
                int saa = 0;
                if ((tova != null) && (!tova.isEmpty())) {
                    for (Location asda : tova)
                        if (s.getID() == asda.getTovarID()) {
                            out.println("<option value=" + saa + " selected>" + asda.getRjad() + " " + asda.getStojka() + " " + asda.getJarus() + "</option>");
                        }
                }
                int r = 0;
                boolean f = false;
                for (Location a : tovars) {
                    out.println("<option value=" + saa + ">" + a.getRjad() + " " + a.getStojka() + " " + a.getJarus() + "</option>");
                    saa++;

                }
                out.println("</select>");
                out.println("</td>");
                i++;
                out.println("<input type=\"hidden\" name=\"name1\" value=" + s.getID() + ">");
                out.println("<input type=\"hidden\" name=\"name2\" value=" + s.getNumbers() + ">");
                out.println("</form></tr>");

            }

        out.println("</table>");
        out.println(

                "<div class=\"w3-hover-sand w3-orange w3-right-align\">" +
                        "<form method=\"post\">" +
                        "<button class=\"w3-btn w3-blue w3-round-large \" type=\"submit\" name=\"button12\"" +
                        " >Отправить" +
                        "</button></form></div>");

    }
    out.println("</div>" +
            "</div>");
} else{
    out.println("<div class=\"w3-container w3-center w3-margin-bottom w3-padding\">" +
            "<div class=\"w3-card-4\">" +
            "<div class=\"w3-container w3-orange\">" +
            "<h2>Расходная накладная</h2>" +
            "</div>");

    out.println("<ul class=\"w3-ul\">");
    out.println("<table class=\"w3-container w3-yellow w3-justify\">");
    out.println("<thead class=\"w3-justify \">" +
            "<tr>" +
            "<th rowspan=\"2\">Номер позиции</th>" +
            "<th rowspan=\"2\">Название</th>" +
            "<th rowspan=\"2\">Количество</th>" +
            "<th colspan=\"4\">Месторасположение</th>" +
            "<th rowspan=\"2\">Принять/Удалить</th>" +
            "</tr>" +
            "<tr>" +
            "<th>Ряд</th><th>Стойка</th><th>Ярус</th><th>Количество</th>" +
            "</tr>" +
            "</thead>");
    List<Tovar> names = (List<Tovar>) request.getAttribute("Selected2");
    List<Tovar> tovar = (List<Tovar>) request.getAttribute("Tovar");
    Integer id= (Integer)request.getAttribute("T");
    Integer id1= (Integer)request.getAttribute("Ta");
    int i = 0;
    if (names != null && !names.isEmpty()) {
        out.println("<form method=\"post\">");
        //int i = 0;
        int s1=0;
        int s2=0;
        int sum = 0;
        int s3=0;
        for (Tovar s : names) {
            out.println("<tr>");
            out.println(
                    "<td>" + (i + 1) + "</td>");
            out.println(
                    "<td>" + s.getName() + "</td>"
            );
            out.println(
                    "<td>" + s.getNumbers() + "</td>"
            );
            int suma[] = new int[100];
            suma[0]=0;
            int k = 0;
            int g=0;
            int aas=-1;
            out.println("<td colspan=\"4\">");
            out.println("<table>");
            for (Location sa : s.getListLocation()) {
                if (aas!=0) {
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
                    "<td><div class=\"w3-right-align\"> " +
                            "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button14\"" +
                            " value=" + i + ">Удалить" +
                            "</button></td>");
            i++;
            out.println("</tr></form>");

        }
    }
    out.println("<form method=\"post\">");
    out.println("<tr>");
    out.println(
            "<td>" + (i + 1) + "</td>");
    out.println(
            "<td><select name=\"myselect4\" onchange='this.form.submit()'>");
    int k = 0;
    for (Tovar a : tovar) {
        if  (k==id1) out.println("<option value=" + k + " selected>" + a.getName() + "</option>");
        else out.println("<option value=" + k + ">" + a.getName() + "</option>");
        k++;
    }
    out.println("</select></td>");
    out.println("<td>");
    out.println("<input type=\"number\" name=\"number4\" min=\"1\" step=\"1\" max="+id+" value=\"1\">");
    out.println("</td>");
    out.println(
            "<td colspan=\"4\">");
    out.println("</td>");
    out.println("<td><div class=\"w3-right-align\">" +
            "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button16\"" +
            " value=" + i + ">Принять" +
            "</button></td>");
    i++;

    out.println("</tr>");
    out.println("</form>");
    out.println("</table>");


    out.println(

            "<div class=\"w3-hover-sand w3-orange w3-right-align\">" +
                    "<form method=\"post\">" +
                    "<button class=\"w3-btn w3-blue w3-round-large \" type=\"submit\" name=\"button22\"" +
                    " >Отправить" +
                    "</button></form></div>");


    out.println("</div>" +
            "</div>");
}
    %>
</body>
</html>

