<%--
  Created by IntelliJ IDEA.
  User: Андрей
  Date: 29.04.2019
  Time: 20:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="app.entities.Kategor" %>
<%@ page import="app.entities.Tovar" %>
<%@ page import="app.entities.Postavshik" %>
<%@ page import="app.entities.AutorizationClient" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Каталог</title>
    <link rel="stylesheet" href="styles/w3.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.0.min.js"></script>
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
<script type="text/javascript">
    (function($){$.fn.selectbox=function(){$(this).each(function(){var select=$(this);if(select.prev('span.selectbox').length<1){function doSelect(){var option=select.find('option');var optionSelected=option.filter(':selected');var optionText=option.filter(':first').text();if(optionSelected.length)optionText=optionSelected.text();var ddlist='';for(i=0;i<option.length;i++){var selected='';var disabled=' class="disabled"';if(option.eq(i).is(':selected'))selected=' class="selected sel"';if(option.eq(i).is(':disabled'))selected=disabled;ddlist+='<li'+selected+'>'+option.eq(i).text()+'</li>';}var selectbox=$('<span class="selectbox" style="display:inline-block;position:relative">'+'<div class="select" style="float:left;position:relative;z-index:10000"><div class="text">'+optionText+'</div>'+'<b class="trigger"><i class="arrow"></i></b>'+'</div>'+'<div class="dropdown" style="position:absolute;z-index:9999;overflow:auto;overflow-x:hidden;list-style:none">'+'<ul>'+ddlist+'</ul>'+'</div>'+'</span>');select.before(selectbox).css({position:'absolute',top:-9999});var divSelect=selectbox.find('div.select');var divText=selectbox.find('div.text');var dropdown=selectbox.find('div.dropdown');var li=dropdown.find('li');var selectHeight=selectbox.outerHeight();if(dropdown.css('left')=='auto')dropdown.css({left:0});if(dropdown.css('top')=='auto')dropdown.css({top:selectHeight});var liHeight=li.outerHeight();var position=dropdown.css('top');dropdown.hide();divSelect.click(function(){var topOffset=selectbox.offset().top;var bottomOffset=$(window).height()-selectHeight-(topOffset-$(window).scrollTop());if(bottomOffset<0||bottomOffset<liHeight*6){dropdown.height('auto').css({top:'auto',bottom:position});if(dropdown.outerHeight()>topOffset-$(window).scrollTop()-20){dropdown.height(Math.floor((topOffset-$(window).scrollTop()-20)/liHeight)*liHeight);}}else if(bottomOffset>liHeight*6){dropdown.height('auto').css({bottom:'auto',top:position});if(dropdown.outerHeight()>bottomOffset-20){dropdown.height(Math.floor((bottomOffset-20)/liHeight)*liHeight);}}$('span.selectbox').css({zIndex:1}).removeClass('focused');selectbox.css({zIndex:2});if(dropdown.is(':hidden')){$('div.dropdown:visible').hide();dropdown.show();}else{dropdown.hide();}return false;});li.hover(function(){$(this).siblings().removeClass('selected');});var selectedText=li.filter('.selected').text();li.filter(':not(.disabled)').click(function(){var liText=$(this).text();if(selectedText!=liText){$(this).addClass('selected sel').siblings().removeClass('selected sel');option.removeAttr('selected').eq($(this).index()).attr('selected',true);selectedText=liText;divText.text(liText);select.change();}dropdown.hide();});dropdown.mouseout(function(){dropdown.find('li.sel').addClass('selected');});select.focus(function(){$('span.selectbox').removeClass('focused');selectbox.addClass('focused');}).keyup(function(){divText.text(option.filter(':selected').text());li.removeClass('selected sel').eq(option.filter(':selected').index()).addClass('selected sel');});$(document).on('click',function(e){if(!$(e.target).parents().hasClass('selectbox')){dropdown.hide().find('li.sel').addClass('selected');selectbox.removeClass('focused');}});}doSelect();select.on('refresh',function(){select.prev().remove();doSelect();})}});}})(jQuery)
    jQuery(function(){
        jQuery('select').selectbox();
    });
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
    <h2>Поиск</h2>
</div>
<div class="w3-container w3-right-align w3-green">
    </br>
    <div class="w3-left-align w3-green">
    <form method="get">
          <label>Введите максимальное количество товара для поиска</label>
     <div class="w3-left-align w3-green">
    <input type="number" name="mynumbers"id="i" min="1" step="1" class="w3-input w3-border w3-round-large" style="width: 30%" size="20" />
     <button class="w3-btn w3-blue w3-round-large"  type="submit" id="button3" name="button3"
         value="+s.toString()+">Найти</button></div></form>
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
        width: 300px;
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

    }
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button {-webkit-appearance: none;
        margin:0;}
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
</style>
<div class="fix">
    <div id="slideout">
        <label>Корзина</label>
        <div id="slideout_inner">
            <div class="w3-card-2">
                <div class="w3-container w3-yellow">
                    <%

                        List <Postavshik> as=(List<Postavshik>) request.getAttribute("Anim");
                        if (as != null && !as.isEmpty()) {
                            out.println("<div class=\"w3-hover-sand w3-center\">" +"Моя корзина" + "</div>");
                            int i=0;
                            for (Postavshik a: as) {

                                out.println("<div class=\"w3-hover-sand\">" );
                                out.println("<div class=\"w3-container w3-green\">" );
                                out.println( "<div class=\"w3-container w3-justify\">" +"Организация:"+ a.getOrganizationname()+"</div>");
                                out.println( "<div class=\"w3-container w3-justify\">" + "Почта:"+a.getEmail()+"</div>");
                                out.println( "<div class=\"w3-container w3-justify\">" + "Адрес:"+a.getAdress()+"</div>");
                                out.println("</div>");
                                for (Tovar b: a.getListTovar()) {
                                    out.println( "<div class=\"w3-container w3-justify\">" + "Название товара:"+b.getName()+"</div>");
                                    out.println( "<div class=\"w3-container w3-justify\">" + "Количество:"+b.getNumbers()+"</div>");
                                }
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
                                         "<form method=\"post\"><button class=\"w3-btn w3-blue w3-round-large right-align \" type=\"submit\" name=\"button5\"" +
                                         ">Отправить заказ"+
                                          "</button></form>"+
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
            out.println("<div class=\"w3-container w3-center w3-green\" id = \"elem\" > Товар успешно добавлен в заказ</div >"+
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
           List<Tovar> names = (List<Tovar>) request.getAttribute("Animals");
              if (names != null && !names.isEmpty()) {
                out.println("<ul class=\"w3-ul\">");
                int i=0;
                for (Tovar s : names) {
                    out.println("<li class=\"w3-container w3-light-green\">");
                    out.println( "<a class=\"\" href='/about?INFO1.java="+
                            s.getID()+"&d=1"+ "'>" );
                    out.println( "<div class=\"w3-container w3-right-align\">"
                            + s.getPrice()+"</div>");
                    out.println( "<div class=\"w3-container w3-left-align\">" +
                            "<img src=\"../images/" +s.getKart() +"\" height=\"100\" width=\"100\" alt=\"none\">"
                            +"  "+s.getName()+"</div>");
                    out.println( "</a>");
                    out.println("<div class=\"w3-container w3-light-green w3-right-align w3-padding\">");
                    out.println( "<div class=\"w3-container w3-right-align\">"+
                            "Зарезервировано:"+ s.getNumbers2()+"</div>");
                    out.println( "<div class=\"w3-container w3-right-align\">"+
                         "На складе:"+ s.getNumbers()+"</div>");

                    out.println("<form method=\"post\">");
                    out.println("<select name=\"myselect\">");
                    int k=0;
                    for (Postavshik a:s.getListPostavshik()) {
                        out.println("<option value=" + a.getID() + ">" + a.toString() + "</option>");
                        k++;
                    }
                    out.println("</select>"+
                            "<button class=\"w3-btn w3-yellow w3-round-large right-align \" type=\"button\" onClick=\"change("+i+",1,10, -1);\" name=\"button2\"" +
                            " value=\"-\">-"+
                            "</button>"+
                            "<input type=\"hidden\" name=\"name1\" value="+i+">"+
                            "<input type=\"number\" name=\"mynumber\"id=\""+i+"\" min=\"1\" step=\"1\" value=\"1\">"+
                            "<button class=\"w3-btn w3-yellow w3-round-large right-align \" type=\"button\" onClick=\"change("+i+",1,1000,1);\" name=\"button3\"" +
                            " value=\"+\">+"+ "</button>"
                    );

                    out.println(
                            "<button class=\"w3-btn w3-red w3-round-large right-align  type=\"submit\" id=\"button1\" name=\"button1\" " +
                                    "value="+s.getID()+">Добавить в заказ"+
                                    "</button></form> </div></li>");
                    i++;
                }
                out.println("</ul>");

            } else out.println("<div class=\"w3-panel w3-red w3-display-container w3-card-4 w3-round\">\n"
                    +
                    "   <span onclick=\"this.parentElement.style.display='none'\"\n" +
                    "   class=\"w3-button w3-margin-right w3-display-right w3-round-large w3-hover-red w3-border w3-border-red w3-hover-border-grey\">×</span>\n" +
                    "   <h5>Сейчас пока нет товаров!</h5>\n" +
                    "</div>");

        %>
    </div>
</div>

<div class="w3-container w3-grey w3-opacity w3-right-align w3-padding">
    <button class="w3-btn w3-round-large" onclick="location.href='/'">Назад</button>
</div>
</body>
</html>

