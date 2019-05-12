package app.servlets;

import javax.inject.Inject;
import javax.servlet.http.HttpServlet;
import app.model.DAO;
import ejb.classes.SingletonBean;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.Integer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import ejb.classes.AddBookBean;
import ejb.classes.SimpleBean;

public class SborkaZakazServlet extends HttpServlet {
    @EJB
    SimpleBean simpleBean;
    @Inject
    AddBookBean addBookBean;
    @EJB
    SingletonBean singletonBean;
    public int parsetoint(String s)
    {
        int foo;
        try {
            foo = Integer.parseInt(s);
        }
        catch (NumberFormatException e)
        {
            foo = 0;
        }
        return foo;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        req.setAttribute("Clients", simpleBean.getClientList("на сборке"));
        req.setAttribute("Zakazs", simpleBean.getClientZakaz("на сборке"));
        req.setAttribute("Selected", simpleBean.getListTovar("на сборке"));
        req.setAttribute("Selected1", simpleBean.ListLocation());
        req.setAttribute("Clients2", addBookBean.getClient());
        req.setAttribute("Kat","на сбоке");
        RequestDispatcher requestDispatcher;
        if (addBookBean.getClient().getRole().equals("кладовщик")) {
            requestDispatcher = req.getRequestDispatcher("views/sborkazakaz.jsp");
        }
        else requestDispatcher = req.getRequestDispatcher("views/error.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("charset=UTF-8");
        req.setCharacterEncoding("UTF-8");
        DAO dao=new DAO();
        if (req.getParameter("button1") != null) {
            String password = req.getParameter("button1");
            int id = parsetoint(password);
            String pass = req.getParameter("myselect");
            int id1 = parsetoint(pass);
            System.out.println(id);
            simpleBean.updateStatus(id, "собран");
            simpleBean.updateLocationZakaz(id,simpleBean.ListLocation().get(id1));
        }
        //System.out.println(pass);


        doGet(req, resp);
    }



}



