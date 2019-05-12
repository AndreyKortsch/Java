<%@ page import="app.entities.Kategor" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="app.entities.Tovar" %>
<%@ page import="app.entities.AutorizationClient" %><%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 03.04.2019
  Time: 20:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Настройки</title>
    <link rel="stylesheet" href="styles/w3.css">
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
    INPUT[type="text"] {
        background-color: navy;
        color: #ffe;
        width: 100px;

    }
    input[type=text]::-webkit-inner-spin-button,
    input[type=text]::-webkit-outer-spin-button {-webkit-appearance: none;
        margin:0;}
    * {
        box-sizing: border-box;
    }

    .col-3 {
        height: 30px;
        width: 33.33%; /* Можно задать любую другую ширину блока */
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
    <h1>Настройки</h1>
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
                        "<a class='href' href='/edit?INFO.java=1'" +
                        ">Товары</a></div>");
                out.println("<div class=\"col-3 w3-center\"> " +
                        "<a class='href' href='/edit?INFO.java=2'" +
                        ">Категории</a></div>");
                out.println("<div class=\"col-3 w3-center\"> " +
                        "<a class='href' href='/edit?INFO.java=3'" +
                        ">Поставщики</a></div>");
                out.println("</div>");
            %>
        </ul>
    </nav>
</div>
<%
    Integer names3 = (Integer) request.getAttribute("Info");
    List<String> asa=new ArrayList<String>();
    asa.add("Добавить");
    asa.add("Изменить");
    asa.add("Удалить");
    if (names3==2) {
        List<Kategor> names1 = (List<Kategor>) request.getAttribute("Info2");
        List<Tovar> names2 = (List<Tovar>) request.getAttribute("Tovars");
        List<Tovar> names4 = (List<Tovar>) request.getAttribute("Tovars4");
        int as = (Integer) request.getAttribute("Am");
        int as2 = (Integer) request.getAttribute("Am2");
        int as3 = (Integer) request.getAttribute("Am3");
        out.println("<div class=\"w3-container w3-center w3-margin-bottom w3-padding\">" +
                "<div class=\"w3-card-4\">" +
                "<div class=\"w3-container w3-orange\">" +
                "<h2>Категории</h2>" +
                "</div>" +
                "<div class=\"w3-container w3-left-align w3-yellow w3-padding\">" +
                "<form method=\"post\">" +
                "<label>Действие:</label>" +
                "<select name=\"myselect2\" onchange='this.form.submit()' >");
           for (int i=0;i<=2;i++)
            if (i==as) out.println("<option value="+i+" selected>"+asa.get(i)+"</option>");
                else out.println("<option value="+i+">"+asa.get(i)+"</option>");
           out.println("</select>");
        if (as==1){
        out.println("<br><label>Категория:</label>" +
                "<select name=\"myselect6\" onchange='this.form.submit()' >");

        int k1 = 0;
        System.out.println(as);
        for (Kategor a : names1) {
            if (as2 == k1) out.println("<option value=" + k1 + " selected>" + a.getName() + "</option>");
            else out.println("<option value=" + k1 + ">" + a.getName() + "</option>");
            k1++;
        }
        out.println("</select><br>");
        out.println("<label>Новое название:</label>"+
                "<input type=\"text\" name=\"name2\" value='"+names1.get(as2).getName()+"' style=\"width: 30%\"><br />"+
            "");
        out.println("<ul class=\"w3-ul\"><div class=\"w3-container w3-center w3-orange\">" +
                                "<h2>Удалить товары</h2>" +
                                "</div><table class=\"w3-container w3-yellow w3-justify\">");
            out.println("<thead class=\"w3-justify \">" +
                    "<tr>" +
                    "<th>Номер позиции</th>" +
                    "<th>Название</th>" +
                    "<th>Выбрать категорию</th>"+
                    "</tr>"+
                    "</thead>");
        int asq=0;
        for (Tovar sa:names2){
            out.println("<tr>");
            out.println("<td>"+sa.getID()+"<td>");
            out.println("<td>"+sa.getName()+"<td>");
            out.println("<td><select name="+asq+"  >");
            int k2 = 0;
            System.out.println(as);
            for (Kategor a : names1) {
                if (as3 == k2) out.println("<option value="+ k2 +" selected>" + a.getName() + "</option>");
                else out.println("<option value=" + k2+ ">" + a.getName() + "</option>");
                k2++;
            }
            out.println("</select><td>");
            out.println("</tr>");
            asq++;
        }
            out.println("</table>");
        out.println("<div class=\"w3-container w3-center w3-orange\">" +
                    "<h2>Добавить товары</h2>" +
                    "</div><table class=\"w3-container w3-yellow w3-justify\">");
            out.println("<thead class=\"w3-justify \">" +
                    "<tr>" +
                    "<th>Номер позиции</th>" +
                    "<th>Название</th>" +
                    "<th>Отметить</th>"+
                    "</tr>"+
                    "</thead>");
            for (Tovar sa:names4){
                out.println("<tr>");
                out.println("<td>"+sa.getID()+"<td>");
                out.println("<td>"+sa.getName()+"<td>");
                out.println("<td><input type=\"checkbox\" class=\"w3-round w3-padding w3-border\" name="+asq+" value="+asq+"><td>");
                out.println("</tr>");
                asq++;
            }
            out.println("</table>");
           out.println("</ul>");
            out.println("<div class=\"w3-right-align\">" +
                    "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button16\"" +
                    " >Принять" +
                    "</button></div>");
        }
        if (as==0) {
            List<Tovar> names8 = (List<Tovar>) request.getAttribute("Am5");
            out.println("<br><label>Новое название:</label>"+
                    "<input type=\"text\" name=\"name4\" required style=\"width: 30%\"><br />"+
                    "");
            out.println("<ul class=\"w3-ul\"><div class=\"w3-container w3-center w3-orange\">" +
                    "<h2>Добавить товары</h2>" +
                    "</div><table class=\"w3-container w3-yellow w3-justify\">");
            out.println("<thead class=\"w3-justify \">" +
                    "<tr>" +
                    "<th>Номер позиции</th>" +
                    "<th>Название</th>" +
                    "<th>Отметить</th>"+
                    "</tr>"+
                    "</thead>");
            int asq=0;
            for (Tovar sa:names8){
                out.println("<tr>");
                out.println("<td>"+sa.getID()+"<td>");
                out.println("<td>"+sa.getName()+"<td>");
                out.println("<td><input type=\"checkbox\" class=\"w3-round w3-padding w3-border\" name="+asq+" value="+asq+"><td>");
                out.println("</tr>");
                asq++;
            }
            out.println("</table>");
            out.println("</ul>");
            out.println("<div class=\"w3-right-align\">" +
                    "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button34\"" +
                    " >Принять" +
                    "</button></div>");

        }
        if (as==2) {
            List<Kategor> names8 = (List<Kategor>) request.getAttribute("An");
            out.println("<br><label>Категория:</label>" +
                    "<select name=\"myselect6\" onchange='this.form.submit()' >");

            int k1 = 0;
            System.out.println(as);
            for (Kategor a : names1) {
                if (as2 == k1) out.println("<option value=" + k1 + " selected>" + a.getName() + "</option>");
                else out.println("<option value=" + k1 + ">" + a.getName() + "</option>");
                k1++;
            }
            out.println("</select><br>");
            out.println("<ul class=\"w3-ul\"><div class=\"w3-container w3-center w3-orange\">" +
                    "<h2>Удалить товары</h2>" +
                    "</div><table class=\"w3-container w3-yellow w3-justify\">");
            out.println("<thead class=\"w3-justify \">" +
                    "<tr>" +
                    "<th>Номер позиции</th>" +
                    "<th>Название</th>" +
                    "<th>Отметить</th>"+
                    "</tr>"+
                    "</thead>");
            int asq=0;
            for (Tovar sa:names2){
                out.println("<tr>");
                out.println("<td>"+sa.getID()+"<td>");
                out.println("<td>"+sa.getName()+"<td>");
                out.println("<td><select name="+asq+">");
                int k2 = 0;
                System.out.println(as);
                for (Kategor a : names8) {
                if (k2 == 0) out.println("<option value="+ k2 +" selected>" + a.getName() +"</option>");
                else out.println("<option value=" + k2+ ">" + a.getName() + "</option>");
                k2++;
                }
                out.println("</select><td>");
                out.println("</tr>");
                asq++;
            }
            out.println("</table>");
            out.println("</ul>");
            out.println("<div class=\"w3-right-align\">" +
                    "<button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button35\"" +
                    " >Принять" +
                    "</button></div>");

        }
        out.println("</form>" +
                "</div>" +
                "</div>" +
                "</div>");
    }

%>
</body>
</html>
