package app.servlets;

import app.model.DAO;
import ejb.classes.SingletonBean;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Integer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

public class AddServlet extends HttpServlet {
    @EJB
    SingletonBean singletonBean;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        Integer name = Integer.parseInt(req.getParameter("number_voler"));
        String password = req.getParameter("name");
        Integer vid = Integer.parseInt(req.getParameter("number_vid"));
        DateFormat data = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = data.parse(req.getParameter("date"));
            //Animal user = new Animal(name, password,vid,date);
            DAO model = new DAO();
            //model.addPerson(user);
            req.setAttribute("name", password);
            req.setAttribute("An", singletonBean.numbers());
            doGet(req, resp);
        }
        catch (Exception e){
System.out.println(e.getMessage());
        }


    }
}