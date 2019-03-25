package app.servlets;

import app.model.DAO;
import ejb.classes.AddBookBean;
import ejb.classes.SimpleBean;
import ejb.classes.SingletonBean;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ZakazServlet extends HttpServlet {
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
        req.setAttribute("Selected", addBookBean.getList());
        System.out.println(addBookBean.getList());
        req.setAttribute("er", addBookBean.getas());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/zakaz.jsp");
        requestDispatcher.forward(req, resp);

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        if (req.getParameter("button2") != null)
        {
            req.setAttribute("name", "кек");
            doGet(req, resp);

        }
        if (req.getParameter("button5") != null){
            req.setAttribute("result", singletonBean.message());

            String pass = req.getParameter("name");
            System.out.println(pass);
            if (req.getParameter("option")!=null)
            simpleBean.sendEmail(pass);
            String pass1 = req.getParameter("number_voler");
            System.out.println(pass1);
            DAO dao=new DAO();
            dao.addPerson(pass1,pass,addBookBean.getList());
            addBookBean.clear();
           RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/ok.jsp");
           requestDispatcher.forward(req, resp);

        }


    }
}

